package farm.gecdevelopers.com.farm.fragments;

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

import farm.gecdevelopers.com.farm.Adapters.DailyActivitiesAdapter;
import farm.gecdevelopers.com.farm.Adapters.DailyExpenseAdapter;
import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.SessionManagement;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.activity.manager.Manager_DashBoardActivity;
import farm.gecdevelopers.com.farm.activity.manager.RecordDailyActivity;
import farm.gecdevelopers.com.farm.activity.manager.RecordDailyExpense;
import farm.gecdevelopers.com.farm.models.DailyExpense_Data;

public class DailyExpensesFragment extends Fragment implements NetworkUtility {
    private View root;
    private RecyclerView recyclerView;
    private DailyExpenseAdapter adapter;
    private ArrayList<DailyExpense_Data> expensesData;
    private Context ctx;
    FloatingActionButton floatingActionButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_daily_expenses, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton = root.findViewById(R.id.add_expenses_btn);

        ctx=getActivity();
        recyclerView=root.findViewById(R.id.daily_expenses_rv);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),RecordDailyExpense.class);
                startActivity(i);
            }
        });
        getList();

    }
    public void getList() {
        expensesData = new ArrayList<>();

        try {
            JSONArray dailyExpenses = Manager_DashBoardActivity.data.getDailyExpense();
            JSONArray plots=Manager_DashBoardActivity.data.getPlots();
           // JSONArray activities=DashBoardActivity.data.getFarmActivity();


            DailyExpense_Data item;

            String userIdfromSP=SessionManagement.pref.getString(SessionManagement.USERID,"");



            for (int i = 0; i < dailyExpenses.length(); i++) {
                JSONObject eachActivity = dailyExpenses.getJSONObject(i);


                String id=eachActivity.getString("id");
                String farmID=eachActivity.getString("farmid");
                String purpose=eachActivity.getString("purpose");
                String supplier=eachActivity.getString("supplier");
                String desc=eachActivity.getString("description");
                String unit=eachActivity.getString("unit");
                String unitPrice=eachActivity.getString("unitprice");
                String total=eachActivity.getString("total");
                String userid=eachActivity.getString("userid");
                String datetime=eachActivity.getString("datetime");


                String plotname="";
                for(int j=0;j<plots.length();j++){
                    JSONObject eachFarm=plots.getJSONObject(j);
                    String farmid=eachFarm.getString("farm_id");
                    if(farmid.equals(farmID)){
                        plotname=eachFarm.getString("farm_name");
                        break;
                    }

                }
                /*String activity="";
                for(int j=0;j<activities.length();j++){
                    JSONObject eachFarm=activities.getJSONObject(j);
                    String actId=eachFarm.getString("activity_id");
                    if(actId.equals(actID)){
                        activity=eachFarm.getString("activity_name");
                        break;

                    }

                }*/
                if(userid.equals(userIdfromSP)){
                    item = new DailyExpense_Data(id,unit,unitPrice,total,
                            userid,plotname,purpose,supplier,desc,datetime);
                    item.setId(id);item.setPlotName(plotname);item.setUnit(unit);
                    item.setUnitPrice(unitPrice);item.setUserId(userid);item.setPurpose(purpose);
                    item.setSupplier(supplier);item.setDescription(desc);item.setDateAndTime(datetime);


                    expensesData.add(item);



                }
                int a = expensesData.size();
                adapter = new DailyExpenseAdapter(expensesData,ctx);
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
