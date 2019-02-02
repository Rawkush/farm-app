package farm.gecdevelopers.com.farm.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import farm.gecdevelopers.com.farm.Adapters.ManagersAdapter;
import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.models.Managers;

public class ManagersList extends Fragment {
    View root;
    static Context ctx;
    static ArrayList<Managers> managersList;
   static RecyclerView recyclerView;

   static ManagersAdapter adapter;

    public static String type="";

    FloatingActionButton add;




    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_managers, container, false);

        add=root.findViewById(R.id.add_manager_btn);
        type=SplashActivity.type;
        if(type.equals("1"))
        {
           add.setVisibility(View.VISIBLE);
        }
        else if(type.equals("3")){
            add.setVisibility(View.GONE);
        }
        ctx=getActivity();
        recyclerView=root.findViewById(R.id.man_rv);


        getList();

        return root;
    }

     public static void getList(){
        managersList=new ArrayList<>();
         final String url="http://axxentfarms.com/farm/files/pages/examples2/fetchtable.php?";


         StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                 new Response.Listener<String>() {
                     @Override
                     public void onResponse(String response) {
                         try{
                         JSONObject root=new JSONObject(response);
                         JSONArray user=root.optJSONArray("login_user");
                         Managers item;

                         for(int i=0;i<user.length();i++){
                             JSONObject eachMan=user.getJSONObject(i);
                             String type=eachMan.getString("type");
                             String name=eachMan.getString("name");
                             String uname=eachMan.getString("username");
                             String email=eachMan.getString("email");
                             String phn=eachMan.getString("phone");
                             String manId=eachMan.getString("client_num");



                             if(type.equals("2")){
                                 item=new Managers(manId,name,email,phn,uname);
                                 item.setManEmail(email);item.setManId(manId);item.setManName(name);item.setManPhnNo(phn);
                                 item.setManUsrname(uname);

                                 managersList.add(item) ;
                             }



                             int a=managersList.size();
                             adapter = new ManagersAdapter(ctx,managersList);
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
