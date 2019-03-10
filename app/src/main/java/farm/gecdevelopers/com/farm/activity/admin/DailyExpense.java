package farm.gecdevelopers.com.farm.activity.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.Adapters.DailyExpenseAdapter;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.manager.Manager_DashBoardActivity;
import farm.gecdevelopers.com.farm.models.DailyExpense_Data;

public class DailyExpense extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DailyExpenseAdapter adapter;
    private ArrayList<DailyExpense_Data> expensesData;
    private Context ctx;


    @SuppressLint("RestrictedApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expense);
        ctx = DailyExpense.this;
        recyclerView = findViewById(R.id.daily_expenses_rv);

        getList();

    }

    public void getList() {
        expensesData = new ArrayList<>();

        try {
            JSONArray dailyExpenses = Manager_DashBoardActivity.data.getDailyExpense();
            JSONArray plots = Manager_DashBoardActivity.data.getPlots();
            // JSONArray activities=DashBoardActivity.data.getFarmActivity();


            DailyExpense_Data item;


            for (int i = 0; i < dailyExpenses.length(); i++) {
                JSONObject eachActivity = dailyExpenses.getJSONObject(i);

                String id = eachActivity.getString("id");
                String farmID = eachActivity.getString("farmid");
                String purpose = eachActivity.getString("purpose");
                String supplier = eachActivity.getString("supplier");
                String desc = eachActivity.getString("description");
                String unit = eachActivity.getString("unit");
                String unitPrice = eachActivity.getString("unitprice");
                String total = eachActivity.getString("total");
                String userid = eachActivity.getString("userid");
                String datetime = eachActivity.getString("datetime");


                String plotname = "";
                for (int j = 0; j < plots.length(); j++) {
                    JSONObject eachFarm = plots.getJSONObject(j);
                    String farmid = eachFarm.getString("farm_id");
                    if (farmid.equals(farmID)) {
                        plotname = eachFarm.getString("farm_name");
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

                item = new DailyExpense_Data(id, unit, unitPrice, total,
                        userid, plotname, purpose, supplier, desc, datetime);
                item.setId(id);
                item.setPlotName(plotname);
                item.setUnit(unit);
                item.setUnitPrice(unitPrice);
                item.setUserId(userid);
                item.setPurpose(purpose);
                item.setSupplier(supplier);
                item.setDescription(desc);
                item.setDateAndTime(datetime);


                    expensesData.add(item);


                int a = expensesData.size();
                adapter = new DailyExpenseAdapter(expensesData, ctx);
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