package farm.gecdevelopers.com.farm.activity.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import farm.gecdevelopers.com.farm.FetchTable;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.fragments.AuditorsList;
import farm.gecdevelopers.com.farm.fragments.FarmactivityList;
import farm.gecdevelopers.com.farm.fragments.FarmsList;
import farm.gecdevelopers.com.farm.fragments.ManagersList;
import farm.gecdevelopers.com.farm.fragments.MoreOptions;
import farm.gecdevelopers.com.farm.fragments.PlotsList;

public class DashBoardActivity extends AppCompatActivity {

    public static FetchTable data;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private final int TIME_OUT = 30000;//30 seconds
    CountDownTimer countDownTimer;
    private ProgressDialog dialog;
    public static final String MANAGER = "Manager", AUDITOR = "Auditors", FARMS = "FarmData", PLOTS = "Plots",
            FARM_ACTIVITY = "Farm Activities", MORE_OPTIONS = "More Options";

    private String[] navLabels = {
            MANAGER, AUDITOR, FARMS, PLOTS, FARM_ACTIVITY, MORE_OPTIONS
    };

    private int[] navIcons = {
            R.drawable.managers_inactive,
            R.drawable.auditor_inactive,
            R.drawable.farms_inactive,
            R.drawable.plots_inactive,
            R.drawable.farm_activities_inactive,
            R.drawable.more_options_inactive
    };

    private int[] navIconsActive = {
            R.drawable.managers,
            R.drawable.auditor,
            R.drawable.farms,
            R.drawable.plots,
            R.drawable.farm_activities,
            R.drawable.more_options


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        bindView();
        dialog = new ProgressDialog(this);
        data = new FetchTable(this);
        data.startConnection();
        dialog.setTitle("Fetching Data");
        dialog.setMessage("Please wait while we Load the Data.");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        init();

        countDownTimer = new CountDownTimer(TIME_OUT, 300) {

            @Override
            public void onTick(long millisUntilFinished) {
                if (data.isDataAvailable()) {
                    init();
                    countDownTimer.cancel();
                    dialog.cancel();
                }
            }

            @Override
            public void onFinish() {
                if (!data.isDataAvailable()) {
                    Toast.makeText(DashBoardActivity.this, "Cannot Load the Data", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }

            }
        }.start();  // first param is timer till which to count


    }

    private void init() {

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount() - 1);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View tabView = tab.getCustomView();

                TextView tab_label = tabView.findViewById(R.id.nav_label);
                ImageView tab_icon = tabView.findViewById(R.id.nav_icon);
                tab_label.setTextColor(getResources().getColor(R.color.green_bkg_website));
                tab_icon.setImageResource(navIconsActive[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View tabView = tab.getCustomView();
                TextView tab_label = tabView.findViewById(R.id.nav_label);
                ImageView tab_icon = tabView.findViewById(R.id.nav_icon);
                tab_label.setTextColor(getResources().getColor(R.color.colorGray_arrow));
                tab_icon.setImageResource(navIcons[tab.getPosition()]);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            Log.d("value",""+i);
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);

            TextView tab_label =tab.findViewById(R.id.nav_label);
            ImageView tab_icon = tab.findViewById(R.id.nav_icon);

            tab_label.setText(navLabels[i]);

            if (i == 0) {
                tab_label.setTextColor(getResources().getColor(R.color.green_bkg_website));
                tab_icon.setImageResource(navIconsActive[i]);
            } else {
                tab_icon.setImageResource(navIcons[i]);
            }

            tabLayout.getTabAt(i).setCustomView(tab);
        }
    }


    private void bindView() {
        viewPager = findViewById(R.id.frame_);
        tabLayout = findViewById(R.id.tabs);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ManagersList(), "Managers");
        adapter.addFrag(new AuditorsList(), "Auditors");
        adapter.addFrag(new FarmsList(), "FarmData");
        adapter.addFrag(new PlotsList(), "Plots");
        adapter.addFrag(new FarmactivityList(), "Activities");
        adapter.addFrag(new MoreOptions(), "More Options");
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
            Bundle bundle = new Bundle();
            bundle.putString("user", "admin");
            fragment.setArguments(bundle);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }




}
