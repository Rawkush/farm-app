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

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.mancj.materialsearchbar.SimpleOnSearchActionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.Adapters.PlotAdapter;
import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
import farm.gecdevelopers.com.farm.activity.admin.AddPlot;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.models.PlotData;

public class PlotsList extends Fragment implements NetworkUtility {
    View root;
    private Context ctx;
    private ArrayList<PlotData> plotArrayList;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private String type;
    private MaterialSearchBar searchBar;

    private PlotAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_plots, container, false);
        ctx=getActivity();
        recyclerView=root.findViewById(R.id.man_rv);


        return root;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton = view.findViewById(R.id.fab);
        type = SplashActivity.type;
        searchBar = view.findViewById(R.id.searchBar);

        if (type.equals(ADMIN)) {

            floatingActionButton.setVisibility(View.VISIBLE);

        } else if (type.equals(AUDITOR)) {
            floatingActionButton.setVisibility(View.GONE);
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPlot.class);
                startActivity(intent);
            }
        });

        /*
         * searchbar
         */

        searchBar.setHint("Enter Auditor to search");
        searchBar.setOnSearchActionListener(new SimpleOnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                super.onSearchStateChanged(enabled);

                if (!enabled) {
                    adapter.getFilter().filter("");   // filtering the result

                    //      recyclerView.setAdapter(duplicateAdapter);
                } else {

                }
                //      recyclerView.setAdapter(adapter);

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                super.onSearchConfirmed(text);

                adapter.getFilter().filter(text);   // filtering the result
            }


            @Override
            public void onButtonClicked(int buttonCode) {
                super.onButtonClicked(buttonCode);

            }
        });

        getList();

    }

    public void getList() {
        plotArrayList=new ArrayList<>();


        try {
            JSONArray user = DashBoardActivity.data.getPlots();
            PlotData item;
            for (int i = 0; i < user.length(); i++) {
                JSONObject eachMan = user.getJSONObject(i);
                String desc = eachMan.getString("farm_disc");
                String plotname = eachMan.getString("farm_name");
                String size = eachMan.getString("farm_size");
                String location = eachMan.getString("farm_location");
                String manager = eachMan.getString("farm_manager");
                item = new PlotData(desc, plotname, size, manager, location);
                item.setSize(size);
                item.setDesc(desc);
                item.setPlotname(plotname);
                item.setManager(manager);
                item.setLocation(location);
                plotArrayList.add(item);
                int a = plotArrayList.size();
                adapter = new PlotAdapter(ctx, plotArrayList);
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









