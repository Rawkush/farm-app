package farm.gecdevelopers.com.farm.activity.admin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.fragments.AddManagers;
import farm.gecdevelopers.com.farm.fragments.AuditorsList;
import farm.gecdevelopers.com.farm.fragments.FarmactivityList;
import farm.gecdevelopers.com.farm.fragments.FarmsList;
import farm.gecdevelopers.com.farm.fragments.ManagersList;
import farm.gecdevelopers.com.farm.fragments.MoreOptions;
import farm.gecdevelopers.com.farm.fragments.PlotsList;
import farm.gecdevelopers.com.farm.models.DailyActivity_Data;
import farm.gecdevelopers.com.farm.models.DailyExpense_Data;
import farm.gecdevelopers.com.farm.models.Document_Data;

public class DashBoardActivity extends AppCompatActivity {

    public static RequestQueue queue;
    ArrayList<DailyActivity_Data> dailyActivity_data;
    ArrayList<DailyExpense_Data> dailyExpense_data;
    ArrayList<Document_Data> document_data;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static final String MANAGER = "Manager", AUDITOR = "Auditors", FARMS = "Farms", PLOTS = "Plots", FARM_ACTIVITY = "Farm Activities";



    private String[] navLabels = {
            MANAGER, AUDITOR, FARMS, PLOTS, FARM_ACTIVITY, "More Options"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        queue =  Volley.newRequestQueue(this);
        dailyActivity_data= new ArrayList<>();
        dailyExpense_data= new ArrayList<DailyExpense_Data>();
        document_data= new ArrayList<>();

        bindView();



        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount()-1);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            Log.d("value",""+i);
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);

            TextView tab_label =tab.findViewById(R.id.nav_label);

            tab_label.setText(navLabels[i]);

                /*if(i == 0) {
                    tab_label.setTextColor(getResources().getColor(R.color.red_web));
                    tab_icon.setImageResource(navIconsActive[i]);
                } else {
                    tab_icon.setImageResource(navIcons[i]);
                }
*/
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
        adapter.addFrag(new FarmsList(),"Farms");
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
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    public void switchToSecondFragment(String TAG) {

        Fragment fragment = new Fragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (TAG) {

            case MANAGER:
                fragment = new AddManagers();
                transaction.replace(R.id.fragment, fragment).addToBackStack(MANAGER);

                break;

            case PLOTS:

                break;

            case AUDITOR:


            case FARMS:


                break;

            case FARM_ACTIVITY:
                break;
        }


        transaction.commit();
    }

    public void toggleVisiblity(int visibility) {

        switch (visibility) {

            case View.INVISIBLE:
                viewPager.setVisibility(View.INVISIBLE);
                tabLayout.setVisibility(View.INVISIBLE);

                break;

            case View.VISIBLE:
                viewPager.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.VISIBLE);

        }

    }


    @Override
    public void onBackPressed() {


        super.onBackPressed();


    }





}
