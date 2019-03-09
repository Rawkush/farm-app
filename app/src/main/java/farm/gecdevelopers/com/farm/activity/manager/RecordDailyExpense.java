package farm.gecdevelopers.com.farm.activity.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.models.PlotData;
import farm.gecdevelopers.com.farm.spinnerAdapter.PlotNameSpinnerAdapter;

public class RecordDailyExpense extends AppCompatActivity {


    EditText edPurpose, edUnit, edUnitPrice, edSupplier, edDescription;
    TextView tvTotalAmount;
    Spinner spnPlot;
    Button btnSubmit;
    RequestQueue queue;
    String plotId = "", unit = "", unitPrice = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_daily_expense);
        bindViews();
        init();

    }


    private void init() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendDataToDatabase();
            }
        });


        edUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    calculateTotal();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edUnitPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    calculateTotal();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        PlotNameSpinnerAdapter plotNameSpinnerAdapter = new PlotNameSpinnerAdapter(this, getPlotList());
        spnPlot.setAdapter(plotNameSpinnerAdapter);
        spnPlot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PlotData fm = (PlotData) parent.getItemAtPosition(position);
                plotId = fm.getFarmId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    private void bindViews() {
        queue = Volley.newRequestQueue(this);

        edPurpose = findViewById(R.id.purpose);

        edUnit = findViewById(R.id.unit);
        edDescription = findViewById(R.id.description);
        edSupplier = findViewById(R.id.supplier_name);
        edUnitPrice = findViewById(R.id.unit_price);
        tvTotalAmount = findViewById(R.id.total_amoutn);
        btnSubmit = findViewById(R.id.submit);
        spnPlot = findViewById(R.id.plot_spinner);


    }

    private void calculateTotal() throws Exception {


        unit = edUnit.getText().toString();
        unitPrice = edUnitPrice.getText().toString();

        if (TextUtils.isEmpty(unit) || TextUtils.isEmpty(unitPrice)) {
            tvTotalAmount.setText("0");
            return;
        }

        int total = (int) Math.round(Double.valueOf(unit) * Double.valueOf(unitPrice));
        tvTotalAmount.setText(String.valueOf(total));


    }

    private boolean isFormFilled() {


        String purpose, description, supplier;
        purpose = edPurpose.getText().toString();
        description = edDescription.getText().toString();
        supplier = edSupplier.getText().toString();

        if (TextUtils.isEmpty(purpose)) {
            edPurpose.setError(getString(R.string.cant_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(description)) {
            edDescription.setError(getString(R.string.cant_be_empty));
            return false;

        }
        if (TextUtils.isEmpty(supplier)) {
            edSupplier.setError(getString(R.string.cant_be_empty));
            return false;

        }
        if (TextUtils.isEmpty(unit)) {

            edUnit.setError(getString(R.string.cant_be_empty));
            return false;

        }
        if (TextUtils.isEmpty(unitPrice)) {
            edUnitPrice.setError(getString(R.string.cant_be_empty));
            return false;
        }


        if (TextUtils.isEmpty(plotId)) {

            Toast.makeText(getApplicationContext(), "select plot", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;


    }

    private void sendDataToDatabase() {

        if (isFormFilled()) {

            final String purpose, description, supplier;
            purpose = edPurpose.getText().toString();
            description = edDescription.getText().toString();
            supplier = edSupplier.getText().toString();
            final String total = tvTotalAmount.getText().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.ADD_DAILY_EXPENSE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //todo show a dialogbox instead of toast
                            Toast.makeText(RecordDailyExpense.this, response, Toast.LENGTH_SHORT).show();


                            Log.i("Response is: ", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(RecordDailyExpense.this, error.getMessage(), Toast.LENGTH_SHORT).show();


                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> param = new HashMap<>();
                    param.put("purpose", purpose);
                    param.put("sname", supplier);
                    param.put("description", description);
                    param.put("unit", unit);
                    param.put("uprice", unitPrice);
                    param.put("total", total);
                    param.put("loan_type", plotId);
                    param.put("user_id", "2");

                    return param;

                }

            };


            queue.add(stringRequest);


        }
    }

    public ArrayList<PlotData> getPlotList() {
        ArrayList<PlotData> plotArrayList = new ArrayList<>();


        try {
            JSONArray jsonArray = DashBoardActivity.data.getPlots();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject eachMan = jsonArray.getJSONObject(i);
                String user = eachMan.getString("farm_manager");
                /* TODO get curent user from shared preference
                if(!user.equals(currentuser))
                   {
                   continue;
                   }

                 */
                String name = eachMan.getString("farm_name");
                String farmId = eachMan.getString("farm_id");
                plotArrayList.add(new PlotData(name, farmId));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return plotArrayList;
    }

}
