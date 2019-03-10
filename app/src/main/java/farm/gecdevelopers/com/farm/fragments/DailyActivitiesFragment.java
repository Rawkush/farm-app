package farm.gecdevelopers.com.farm.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.Adapters.DailyActivitiesAdapter;
import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.SessionManagement;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.activity.manager.RecordDailyActivity;
import farm.gecdevelopers.com.farm.models.DailyActivity_Data;

import static android.content.Context.MODE_PRIVATE;

public class DailyActivitiesFragment extends Fragment implements NetworkUtility {
    private View root;
    private RecyclerView recyclerView;
    private DailyActivitiesAdapter adapter;
    private ArrayList<DailyActivity_Data> activitiesList;
    private Context ctx;
    FloatingActionButton floatingActionButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_daily_activities, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton = root.findViewById(R.id.add_activiy_btn);

        ctx=getActivity();
        recyclerView=root.findViewById(R.id.daily_activity_rv);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),RecordDailyActivity.class);
                startActivity(i);
            }
        });

        getList();

    }
    public void getList() {
        activitiesList = new ArrayList<>();

        try {
            JSONArray dailyActivities = DashBoardActivity.data.getDailyActivities();
            JSONArray plots=DashBoardActivity.data.getPlots();
            JSONArray activities=DashBoardActivity.data.getFarmActivity();


            DailyActivity_Data item;
            SharedPreferences sp = getActivity().getSharedPreferences("FarmPref", MODE_PRIVATE);

            String userIdfromSP=sp.getString(SessionManagement.USERID,"");


            for (int i = 0; i < dailyActivities.length(); i++) {
                JSONObject eachActivity = dailyActivities.getJSONObject(i);

                String activityID=eachActivity.getString("dactivity_id");
                String farmID=eachActivity.getString("farmid");
                String imgName=eachActivity.getString("imgname");
                String docName=eachActivity.getString("docname");
                String videoName=eachActivity.getString("vidname");
                String actID=eachActivity.getString("actid");
                String det=eachActivity.getString("det");
                String cmts=eachActivity.getString("cmts");
                String farmLat=eachActivity.getString("farmlat");
                String farmLong=eachActivity.getString("farmlong");
                String date = eachActivity.getString("date");
                String userID = eachActivity.getString("userid");
                String hect = eachActivity.getString("hect");
                String status = eachActivity.getString("status");
                String reason = eachActivity.getString("reason");


                String plotname="";
                for(int j=0;j<plots.length();j++){
                    JSONObject eachFarm=plots.getJSONObject(j);
                    String farmid=eachFarm.getString("farm_id");
                    if(farmid.equals(farmID)){
                        plotname=eachFarm.getString("farm_name");
                        break;
                    }

                }
                String activity="";
                for(int j=0;j<activities.length();j++){
                    JSONObject eachFarm=activities.getJSONObject(j);
                    String actId=eachFarm.getString("activity_id");
                    if(actId.equals(actID)){
                        activity=eachFarm.getString("activity_name");
                        break;

                    }

                }

                if(userID.equals(userIdfromSP)){
                    item = new DailyActivity_Data( activityID,  plotname,  imgName,
                            docName,  videoName,  det,
                            cmts,  farmLat,  farmLong,
                            date,  hect,  status,
                            userID,  reason,  activity);
                    item.setDailyAcitvityID(activityID);item.setPlotName(plotname);item.setImageName(imgName);
                    item.setDocName(docName);item.setVideoName(videoName);item.setDetails(det);
                    item.setComments(cmts);item.setFarmLatitude(farmLat);item.setFarmLongitude(farmLong);
                    item.setUserId(userID);item.setReason(reason);item.setActid(activity);


                    activitiesList.add(item);




                }
                int a = activitiesList.size();
                adapter = new DailyActivitiesAdapter(ctx, activitiesList);
                LinearLayoutManager llm = new LinearLayoutManager(ctx);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llm);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);


                // Toast.makeText(ctx,"LISt ka size "+managersList.size(),Toast.LENGTH_SHORT).show();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
