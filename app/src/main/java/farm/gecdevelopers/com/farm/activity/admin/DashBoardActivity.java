package farm.gecdevelopers.com.farm.activity.admin;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import farm.gecdevelopers.com.farm.R;
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

    static FragmentManager fmanager;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FrameLayout frameLayout;

    private String[] navLabels = {
            "Managers","Auditors","Farms","Plots","Farm Activities","More Options"
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

        //fetchTable();
        //String res=NetworkUtility.hitApi();
    }
    private void bindView() {
        viewPager = findViewById(R.id.frame_);
        tabLayout = findViewById(R.id.tabs);
        frameLayout = findViewById(R.id.frame);


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

/*
        private void fetchTable(){
            final String url="http://axxentfarms.com/farm/files/pages/examples2/fetchtable.php?";


            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.e("Response is: ", response);
                            try {
                                JSONObject table = new JSONObject(response);
                                extractDataFromTable(table);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(DashBoardActivity.this,"Failed to fetch, please retry",Toast.LENGTH_SHORT).show();


                }
            });
            queue.add(stringRequest);

        }
*/

/*
        private void extractDataFromTable(JSONObject table){

            extractDailyActivity(table);
            extractDailyExpenses(table);
            extractDocumentData(table);
        }
*/

    private void extractDailyActivity(JSONObject table){
        try {
            JSONArray array= table.getJSONArray("dailyactivity");

            for(int i=0;i<array.length();i++){


                JSONObject data=array.getJSONObject(i);
                DailyActivity_Data s= new DailyActivity_Data();


                s.setDailyAcitvityID(Long.parseLong(data.getString("dactivity_id")));
                s.setFarmID(Long.parseLong(data.getString("farmid")));
                s.setImageName(data.getString("imgname"));
                s.setDocName(data.getString("docname"));
                s.setVideoName(data.getString("vidname"));
                s.setActid(Long.parseLong(data.getString("actid")));
                s.setDetails(data.getString("det"));
                s.setComments(data.getString("cmts"));
                s.setFarmLatitude(data.getString("farmlat"));
                s.setFarmLongitude(data.getString("farmlong"));
                s.setDateAndTime(data.getString("date"));
                s.setUserId(data.getInt("userid"));
                s.setHect(data.getString("hect"));
                s.setStatus(data.getInt("status"));
                s.setReason(data.getInt("reason"));

                dailyActivity_data.add(s);


            }







        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void extractDailyExpenses(JSONObject table){
        try {
            JSONArray array= table.getJSONArray("dailyexpenses");

            for(int i=0;i<array.length();i++){

                JSONObject data=array.getJSONObject(i);
                DailyExpense_Data s= new DailyExpense_Data();
                s.setId(Long.parseLong(data.getString("id")));
                s.setFarmId(data.getInt("farmid"));
                s.setPurpose(data.getString("purpose"));
                s.setSupplier(data.getString("supplier"));
                s.setDescription(data.getString("description"));
                s.setDateAndTime(data.getString("datetime"));
                s.setUnit(Long.parseLong(data.getString("unit")));
                s.setUnitPrice(Long.parseLong(data.getString("unitprice")));
                s.setTotal(Long.parseLong(data.getString("total")));
                s.setUserId(Long.parseLong(data.getString("userid")));
                dailyExpense_data.add(s);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private  void extractDocumentData(JSONObject table){

        try {
            JSONArray array= table.getJSONArray("document");

            for(int i=0;i<array.length();i++){

                JSONObject data=array.getJSONObject(i);
                Document_Data s= new Document_Data();

                s.setId(Long.parseLong(data.getString("id")));
                s.setUserId(data.getInt("user_id"));
                s.setName(data.getString("name"));
                s.setLink(data.getString("link"));
                document_data.add(s);

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
