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

import farm.gecdevelopers.com.farm.Adapters.ManagersAdapter;
import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
import farm.gecdevelopers.com.farm.activity.admin.CreateAccount;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.models.Managers;

public class ManagersList extends Fragment implements NetworkUtility {
    private View root;
    private Context ctx;
    private ArrayList<Managers> managersList;
    private RecyclerView recyclerView;
    private ManagersAdapter adapter;
    FloatingActionButton floatingActionButton;
    private String type;

    private MaterialSearchBar searchBar;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_managers, container, false);


        return root;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton = root.findViewById(R.id.add_manager_btn);
        type=SplashActivity.type;
        searchBar = view.findViewById(R.id.searchBar);

        ctx=getActivity();
        recyclerView=root.findViewById(R.id.man_rv);

        init();
        getList();


        if (type.equals("1")) {

            floatingActionButton.setVisibility(View.VISIBLE);

        } else if (type.equals("3")) {
            floatingActionButton.setVisibility(View.GONE);
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*((DashBoardActivity) getActivity()).switchToSecondFragment(DashBoardActivity.MANAGER);
                ((DashBoardActivity) getActivity()).toggleVisiblity(View.INVISIBLE);
*/
                Intent intent = new Intent(getActivity(), CreateAccount.class);
                intent.putExtra("type", getString(R.string.type_manager));
                startActivity(intent);

            }
        });


    }

private void init(){

    /*
     * searchbar
     */

    searchBar.setHint("Search Manager using name,email...");
    searchBar.setOnSearchActionListener(new SimpleOnSearchActionListener() {
        @Override
        public void onSearchStateChanged(boolean enabled) {
            super.onSearchStateChanged(enabled);

            if (!enabled) {
                adapter.getFilter().filter("");   // filtering the result

                //      recyclerView.setAdapter(duplicateAdapter);
            } else{

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
}


    public void getList() {
        managersList = new ArrayList<>();

        try {
            JSONArray user = DashBoardActivity.data.getAllUsers();
            Managers item;

            for (int i = 0; i < user.length(); i++) {
                JSONObject eachMan = user.getJSONObject(i);
                String type = eachMan.getString("type");
                String name = eachMan.getString("name");
                String uname = eachMan.getString("username");
                String email = eachMan.getString("email");
                String phn = eachMan.getString("phone");
                String manId = eachMan.getString("client_num");
                if (type.equals(MANAGER)) {
                    item = new Managers(manId, name, email, phn, uname);
                    item.setManEmail(email);
                    item.setManId(manId);
                    item.setManName(name);
                    item.setManPhnNo(phn);
                    item.setManUsrname(uname);
                    managersList.add(item);
                }

                int a = managersList.size();
                adapter = new ManagersAdapter(ctx, managersList);
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
