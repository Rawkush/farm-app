package farm.gecdevelopers.com.farm.activity.admin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    public static final String MANAGER = "Manager", AUDITOR = "Auditors", FARMS = "Farms", PLOTS = "Plots", FARM_ACTIVITY = "Farm Activities";



    private String[] navLabels = {
            MANAGER, AUDITOR, FARMS, PLOTS, FARM_ACTIVITY, "More Options"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        bindView();

        /* starting connection to fetch data*/

        data = new FetchTable(this);
        data.startConnection();

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount()-1);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            Log.d("value",""+i);
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            TextView tab_label =tab.findViewById(R.id.nav_label);
            tab_label.setText(navLabels[i]);
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
