package farm.gecdevelopers.com.farm.activity.admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.Adapters.ItemTypesAdapter;
import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
import farm.gecdevelopers.com.farm.models.ItemType_Data;

public class ItemTypesActivity extends AppCompatActivity implements NetworkUtility {
    FloatingActionButton floatingActionButton;
    private Context ctx;
    private ArrayList<ItemType_Data> itemTypesArrayList;
    private RecyclerView recyclerView;
    private ItemTypesAdapter adapter;
    private String type;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_types);

        type = SplashActivity.type;
        ctx = ItemTypesActivity.this;

        floatingActionButton = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.item_types_rv);
        type = SplashActivity.type;

        if (type.equals(ADMIN)) {

            floatingActionButton.setVisibility(View.VISIBLE);

        } else if (type.equals(AUDITOR)) {
            floatingActionButton.setVisibility(View.GONE);
        }
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemTypesActivity.this, AddItems.class);
                startActivity(intent);
            }
        });


        getList();



    }

    public void getList() {
        itemTypesArrayList = new ArrayList<>();

        try {
            JSONArray user = DashBoardActivity.data.getItemsType();
            ItemType_Data item;
            for (int i = 0; i < user.length(); i++) {
                JSONObject eachMan = user.getJSONObject(i);
                String itemid = eachMan.getString("item_id");
                String name = eachMan.getString("item_name");
                String description = eachMan.getString("item_disc");
                String date = eachMan.getString("date");
                String manufacturer = eachMan.getString("item_man");
                item = new ItemType_Data(itemid, name, description, date, manufacturer);

                item.setDate(date);
                item.setDescription(description);
                item.setItemId(itemid);
                item.setManufacturer(manufacturer);
                item.setName(name);
                itemTypesArrayList.add(item);
                int a = itemTypesArrayList.size();
                adapter = new ItemTypesAdapter(ctx, itemTypesArrayList);
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

