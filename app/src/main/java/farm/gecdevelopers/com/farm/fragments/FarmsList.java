package farm.gecdevelopers.com.farm.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.Adapters.FarmsAdapter;
import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
import farm.gecdevelopers.com.farm.activity.admin.AddFarm;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.models.FarmData;

public class FarmsList extends Fragment implements NetworkUtility {

    View root;
    FloatingActionButton floatingActionButton;
    private Context ctx;
    private ArrayList<FarmData> farmsArrayList;
    private RecyclerView recyclerView;
    private FarmsAdapter adapter;
    private String type;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_farms, container, false);
        ctx=getActivity();
        return root;
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = SplashActivity.type;

        floatingActionButton = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.man_rv);
        type = SplashActivity.type;

        if (type.equals(ADMIN)) {

            floatingActionButton.setVisibility(View.VISIBLE);

        } else if (type.equals(AUDITOR)) {
            floatingActionButton.setVisibility(View.GONE);
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddFarm.class);
                startActivity(intent);
            }
        });


        getList();



    }

    public void getList() {
        farmsArrayList = new ArrayList<>();


        try {
            JSONArray user = DashBoardActivity.data.getFarms();
            FarmData item;

            for (int i = 0; i < user.length(); i++) {
                JSONObject eachMan = user.getJSONObject(i);
                String desc = eachMan.getString("farm_d");
                String name = eachMan.getString("farm_n");
                String size = eachMan.getString("farm_s");
                item = new FarmData(desc, name, size);
                item.setSize(size);
                item.setDesc(desc);
                item.setName(name);
                farmsArrayList.add(item);
                int a = farmsArrayList.size();
                adapter = new FarmsAdapter(ctx, farmsArrayList);
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






