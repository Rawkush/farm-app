package farm.gecdevelopers.com.farm.activity.manager;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import farm.gecdevelopers.com.farm.FetchTable;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
import farm.gecdevelopers.com.farm.fragments.DailyActivities;
import farm.gecdevelopers.com.farm.fragments.DailyExpenses;
import farm.gecdevelopers.com.farm.fragments.MoreOptions;

public class Manager_DashBoardActivity extends AppCompatActivity {
    public static FetchTable data = SplashActivity.data;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public static final String ACTIVITIES = "DailyActivities", EXPENSES = "DailyExpenses",  MORE_OPTIONS = "More Options";



    private String[] navLabels = {ACTIVITIES,EXPENSES, MORE_OPTIONS };

    private int[] navIcons = {
            R.drawable.farm_activities_inactive,
            R.drawable.plots_inactive,
            R.drawable.more_options_inactive
    };

    private int[] navIconsActive = {
            R.drawable.daily_activities,
            R.drawable.daily_expenses,
            R.drawable.more_options


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__dash_board);
        bindView();

        /* starting connection to fetch data*/


        Intent intent = new Intent(this, RecordDailyActivity.class);
        startActivity(intent);


        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount()-1);
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

       /* Intent intent = new Intent(this, AddFarm.class);
        startActivity(intent);*/

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DailyActivities(), ACTIVITIES);
        adapter.addFrag(new DailyExpenses(), EXPENSES);
        adapter.addFrag(new MoreOptions(), "More Options");
        viewPager.setAdapter(adapter);
    }

    private void bindView() {
        viewPager = findViewById(R.id.frame_);
        tabLayout = findViewById(R.id.tabs);
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
