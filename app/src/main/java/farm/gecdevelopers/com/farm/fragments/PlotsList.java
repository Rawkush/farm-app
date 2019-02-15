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

import farm.gecdevelopers.com.farm.Adapters.PlotAdapter;
import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.models.Plot;

public class PlotsList extends Fragment {
    View root;
    static Context ctx;
    static ArrayList<Plot> plotArrayList;
    static RecyclerView recyclerView;

    static PlotAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_plots, container, false);

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
                JSONArray user=root.getJSONArray("add_farm");
                Plot item;

                for(int i=0;i<user.length();i++){
                    JSONObject eachMan=user.getJSONObject(i);
                    String desc=eachMan.getString("farm_disc");
                    String plotname=eachMan.getString("farm_name");
                    String size=eachMan.getString("farm_size");
                    String location=eachMan.getString("farm_location");
                    String manager=eachMan.getString("farm_manager");


                    item=new Plot(desc,plotname,size,manager,location);
                    item.setSize(size);
                    item.setDesc(desc);
                    item.setPlotname(plotname);
                    item.setManager(manager);
                    item.setLocation(location);



                    plotArrayList.add(item) ;


                    //  Toast.makeText(ctx,"LISt ka size "+managersList.size(),Toast.LENGTH_SHORT).show();

                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void getList(){
        plotArrayList=new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.TABLE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject root=new JSONObject(response);
                            JSONArray user=root.getJSONArray("add_farm");
                            Plot item;

                            for(int i=0;i<user.length();i++){
                                JSONObject eachMan=user.getJSONObject(i);
                                String desc=eachMan.getString("farm_disc");
                                String plotname=eachMan.getString("farm_name");
                                String size=eachMan.getString("farm_size");
                                String location=eachMan.getString("farm_location");
                                String manager=eachMan.getString("farm_manager");





                                item=new Plot(desc,plotname,size,manager,location);
                                item.setSize(size);
                                item.setDesc(desc);
                                item.setPlotname(plotname);
                                item.setManager(manager);
                                item.setLocation(location);


                                plotArrayList.add(item) ;





                                int a=plotArrayList.size();
                                adapter = new PlotAdapter(ctx,plotArrayList);
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









