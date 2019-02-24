package farm.gecdevelopers.com.farm.activity.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.Adapters.DailyExpenseAdapter;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
import farm.gecdevelopers.com.farm.models.DailyExpense_Data;

public class DailyExpense extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    private Context ctx;
    private ArrayList<DailyExpense_Data> itemTypesArrayList;
    private RecyclerView recyclerView;
    private DailyExpenseAdapter adapter;
    private String type;

    @SuppressLint("RestrictedApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expense);
        type = SplashActivity.type;
        ctx = DailyExpense.this;
        recyclerView = findViewById(R.id.expense);
        type = SplashActivity.type;


        getList();


    }


    public void getList() {
        itemTypesArrayList = new ArrayList<>();

        try {
            JSONArray user = DashBoardActivity.data.getDailyExpense();
            DailyExpense_Data item;
            for (int i = 0; i < user.length(); i++) {
                JSONObject eachMan = user.getJSONObject(i);
                String itemid = eachMan.getString("id");
                String purpose = eachMan.getString("purpose");
                String supplier = eachMan.getString("supplier");
                String description = eachMan.getString("description");
                String unit = eachMan.getString("unit");
                String unitPrice = eachMan.getString("unitprice");
                String total = eachMan.getString("total");
                String date = eachMan.getString("datetime");
                String userid = eachMan.getString("userid");
                item = new DailyExpense_Data();

                item.setDateAndTime(date);
                item.setDescription(description);
                item.setId(itemid);
                item.setPurpose(purpose);
                item.setSupplier(supplier);
                item.setUnit(unit);
                item.setUnitPrice(unitPrice);
                item.setTotal(total);
                item.setUserId(userid);
                itemTypesArrayList.add(item);
                int a = itemTypesArrayList.size();
                adapter = new DailyExpenseAdapter(itemTypesArrayList, ctx);
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
