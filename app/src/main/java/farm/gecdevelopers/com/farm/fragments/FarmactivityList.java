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
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.Adapters.FarmActivityAdapter;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
import farm.gecdevelopers.com.farm.activity.admin.AddFarmActivity;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.models.FarmActivity;

public class FarmactivityList extends Fragment {
    View root;
    private Context ctx;
    private ArrayList<FarmActivity> farmActivityArrayList;
    private RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    private String type;
    private FarmActivityAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_activity, container, false);


        return root;
    }

    @SuppressLint("RestrictedApi")

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ctx = getActivity();
        floatingActionButton = view.findViewById(R.id.fab);
        type = SplashActivity.type;

        if (type.equals("1")) {

            floatingActionButton.setVisibility(View.VISIBLE);

        } else if (type.equals("3")) {
            floatingActionButton.setVisibility(View.GONE);
        }


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddFarmActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = root.findViewById(R.id.man_rv);

        getList();


    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    private void getList() {
        farmActivityArrayList = new ArrayList<>();


        try {
            JSONArray user = DashBoardActivity.data.getFarmActivity();
            FarmActivity item;


            for (int i = 0; i < user.length(); i++) {
                JSONObject eachMan = user.getJSONObject(i);
                String desc = eachMan.getString("activity_disc");
                String activity = eachMan.getString("activity_name");

                item = new FarmActivity(desc, activity);
                item.setDesc(desc);
                item.setActivity(activity);
                farmActivityArrayList.add(item);

                int a = farmActivityArrayList.size();
                adapter = new FarmActivityAdapter(ctx, farmActivityArrayList);
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










