package farm.gecdevelopers.com.farm.activity.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.models.Farms;
import farm.gecdevelopers.com.farm.models.LoginUser;
import farm.gecdevelopers.com.farm.spinnerAdapter.FarmSpinnerAdapter;
import farm.gecdevelopers.com.farm.spinnerAdapter.ManagerSpinnerAdapter;

public class AddPlot extends AppCompatActivity implements NetworkUtility {

    RequestQueue queue;
    EditText edLatOne, edLatTwo, edLatThree, edLatFour, edLongOne, edLongTwo, edLongThree, edLongFour;
    Button btnSubmit;
    EditText edSize, edDescription, edPlotName;
    Spinner spnFarm, spnManager;
    String farm = "", manager = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plot);
        bindViews();
        init();


    }

    private void init() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToDatabse();
            }
        });
        FarmSpinnerAdapter farmSpinnerAdapter;
        farmSpinnerAdapter = new FarmSpinnerAdapter(this, getFarmList());
        spnFarm.setAdapter(farmSpinnerAdapter);
        spnFarm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Farms fm = (Farms) parent.getItemAtPosition(position);
                farm = fm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ManagerSpinnerAdapter managerSpinnerAdapter = new ManagerSpinnerAdapter(this, getManagerList());
        spnManager.setAdapter(managerSpinnerAdapter);
        spnManager.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LoginUser user = (LoginUser) parent.getItemAtPosition(position);
                manager = user.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void bindViews() {

        spnManager = findViewById(R.id.manager_spinner);
        spnFarm = findViewById(R.id.farm_spinner);
        queue = Volley.newRequestQueue(this);
        edDescription = findViewById(R.id.description);
        edLatFour = findViewById(R.id.latitude4);
        edLatOne = findViewById(R.id.latitude1);
        edLatThree = findViewById(R.id.latitude3);
        edLatTwo = findViewById(R.id.latitude2);
        edLongFour = findViewById(R.id.longitude4);
        edLongOne = (EditText) findViewById(R.id.longitude1);
        edLongTwo = findViewById(R.id.longitude2);
        edLongThree = findViewById(R.id.longitude3);
        btnSubmit = findViewById(R.id.submit);
        edSize = findViewById(R.id.size);
        edPlotName = findViewById(R.id.plot_name);


    }

    private void sendDataToDatabse() {
        final String latitude1, latitude2, latitude3, latitude4, longitude1, longitude2, longitude3, longitude4, size, description, plotname;

        latitude1 = edLatOne.getText().toString();
        latitude2 = edLatTwo.getText().toString();
        latitude3 = edLatThree.getText().toString();
        latitude4 = edLatFour.getText().toString();
        longitude1 = edLongOne.getText().toString();
        longitude2 = edLongTwo.getText().toString();
        longitude3 = edLongThree.getText().toString();
        longitude4 = edLongFour.getText().toString();
        size = edSize.getText().toString();
        description = edDescription.getText().toString();
        plotname = edPlotName.getText().toString();

        if (isFormFilled()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, ADD_PLOT_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(AddPlot.this, response, Toast.LENGTH_SHORT).show();
                            Log.i("Response is: ", response);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(AddPlot.this, error.getMessage(), Toast.LENGTH_SHORT).show();


                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> param = new HashMap<>();

                    param.put("loan_t", farm);
                    param.put("loan_type", manager);
                    param.put("farmdisc", description);
                    param.put("latone", latitude1);
                    param.put("longone", longitude1);
                    param.put("lattwo", latitude2);
                    param.put("longtwo", longitude2);
                    param.put("latthree", latitude3);
                    param.put("longthree", longitude3);
                    param.put("latfour", latitude4);
                    param.put("longfour", longitude4);
                    param.put("farmname", plotname);
                    param.put("farmsize", size);
                    return param;


                }

            };


            queue.add(stringRequest);


        }

    }

    private boolean isFormFilled() {


        String latitude1, latitude2, latitude3, latitude4, longitude1, longitude2, longitude3, longitude4, size, description, plotname;

        latitude1 = edLatOne.getText().toString();
        latitude2 = edLatTwo.getText().toString();
        latitude3 = edLatThree.getText().toString();
        latitude4 = edLatFour.getText().toString();

        longitude1 = edLongOne.getText().toString();
        longitude2 = edLongTwo.getText().toString();
        longitude3 = edLongThree.getText().toString();
        longitude4 = edLongFour.getText().toString();

        size = edSize.getText().toString();
        description = edDescription.getText().toString();
        plotname = edPlotName.getText().toString();

        if (TextUtils.isEmpty(latitude1)) {
            edLatOne.setError(getString(R.string.cant_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(latitude2)) {
            edLatTwo.setError(getString(R.string.cant_be_empty));
            return false;

        }
        if (TextUtils.isEmpty(latitude3)) {
            edLatThree.setError(getString(R.string.cant_be_empty));
            return false;

        }
        if (TextUtils.isEmpty(latitude4)) {
            edLatFour.setError(getString(R.string.cant_be_empty));
            return false;

        }
        if (TextUtils.isEmpty(longitude1)) {
            edLongOne.setError(getString(R.string.cant_be_empty));
            return false;
        }

        if (TextUtils.isEmpty(longitude2)) {
            edLongTwo.setError(getString(R.string.cant_be_empty));
            return false;
        }

        if (TextUtils.isEmpty(longitude3)) {
            edLongThree.setError(getString(R.string.cant_be_empty));
            return false;

        }

        if (TextUtils.isEmpty(longitude4)) {
            edLongFour.setError(getString(R.string.cant_be_empty));
            return false;

        }

        if (TextUtils.isEmpty(size)) {
            edSize.setError(getString(R.string.cant_be_empty));
            return false;

        }

        if (TextUtils.isEmpty(description)) {
            edDescription.setError(getString(R.string.cant_be_empty));
            return false;

        }
        if (TextUtils.isEmpty(farm)) {
            Toast.makeText(this, "select farm", Toast.LENGTH_SHORT).show();
            return false;

        }

        if (TextUtils.isEmpty(manager)) {
            Toast.makeText(this, "select Manager", Toast.LENGTH_SHORT).show();
            return false;

        }
        if (TextUtils.isEmpty(plotname)) {
            edPlotName.setError(getString(R.string.cant_be_empty));
            return false;

        }


        return true;


    }


    public ArrayList<Farms> getFarmList() {
        ArrayList<Farms> farmsArrayList = new ArrayList<>();


        try {
            JSONArray user = DashBoardActivity.data.getFarms();
            for (int i = 0; i < user.length(); i++) {
                JSONObject eachMan = user.getJSONObject(i);
                String name = eachMan.getString("farm_n");
                String id = eachMan.getString("id");
                farmsArrayList.add(new Farms(name, id));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return farmsArrayList;
    }


    public ArrayList<LoginUser> getManagerList() {
        ArrayList<LoginUser> managerList = new ArrayList<>();


        try {
            JSONArray user = DashBoardActivity.data.getAllUsers();
            for (int i = 0; i < user.length(); i++) {
                JSONObject eachMan = user.getJSONObject(i);

                String type = eachMan.getString("type");
                if (!type.equals(MANAGER))
                    continue;

                String name = eachMan.getString("name");
                String id = eachMan.getString("id");

                managerList.add(new LoginUser(name, id));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return managerList;
    }



}
