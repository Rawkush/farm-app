package farm.gecdevelopers.com.farm.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.Adapters.FAdapter;
import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.models.FarmActivity;

public class FarmactivityList extends Fragment {
    View root;
    static Context ctx;
    static ArrayList<FarmActivity> farmActivityArrayList;
    static RecyclerView recyclerView;

    static FAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_activity, container, false);

        ctx=getActivity();
        recyclerView=root.findViewById(R.id.man_rv);
        String json="";
        /*URL url=NetworkUtility.buildUrl();
        try {
            json=NetworkUtility.getResponseFromHttpUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

//json= NetworkUtility.hitApi();

        //getManList(json);

        getList();

        return root;
    }
    public static void getManList(String jsonfile)  {

        // String res= NetworkUtility.hitApi();

        // Log.d("STING KI MAA KI",""+res);
        try{
            if(jsonfile!=null){
                JSONObject root=new JSONObject(jsonfile);
                JSONArray user=root.getJSONArray("addfarmactivity");
                FarmActivity item;

                for(int i=0;i<user.length();i++){
                    JSONObject eachMan=user.getJSONObject(i);
                    String desc=eachMan.getString("activity_disc");
                    String activity=eachMan.getString("activity_name");

                    item=new FarmActivity(desc,activity);

                    item.setDesc(desc);

                    item.setActivity(activity);



                    farmActivityArrayList.add(item) ;


                    //  Toast.makeText(ctx,"LISt ka size "+managersList.size(),Toast.LENGTH_SHORT).show();

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void getList(){
        farmActivityArrayList=new ArrayList<>();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.TABLE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject root=new JSONObject(response);
                            JSONArray user=root.getJSONArray("addfarmactivity");
                            FarmActivity item;

                            for(int i=0;i<user.length();i++){
                                JSONObject eachMan=user.getJSONObject(i);
                                String desc=eachMan.getString("activity_disc");
                                String activity=eachMan.getString("activity_name");

                                item=new FarmActivity(desc,activity);
                                item.setDesc(desc);
                                item.setActivity(activity);
                                farmActivityArrayList.add(item) ;

                                int a=farmActivityArrayList.size();
                                adapter = new FAdapter(ctx,farmActivityArrayList);
                                LinearLayoutManager llm = new LinearLayoutManager(ctx);
                                llm.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(llm);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(adapter);


                                // Toast.makeText(ctx,"LISt ka size "+managersList.size(),Toast.LENGTH_SHORT).show();

                            }}catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ctx,"Failed to fetch, please retry",Toast.LENGTH_SHORT).show();


            }
        });
        DashBoardActivity.queue.add(stringRequest);
    }
}










