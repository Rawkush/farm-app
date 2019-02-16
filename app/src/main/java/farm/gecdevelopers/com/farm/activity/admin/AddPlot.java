package farm.gecdevelopers.com.farm.activity.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;

public class AddPlot extends AppCompatActivity {

    RequestQueue queue;
    EditText edLatOne, edLatTwo, edLatThree, edLatFour, edLongOne, edLongTwo, edLongThree, edLongFour;
    Button btnSubmit;
    EditText edSize, edDescription, edPlotName, edManager, edFarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plot);
        bindViews();
    }

    private void bindViews() {

        queue = Volley.newRequestQueue(this);
        edDescription = findViewById(R.id.description);
        edFarm = findViewById(R.id.farm);
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
        edManager = findViewById(R.id.manager);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToDatabse();
            }
        });

    }

    private void sendDataToDatabse() {
        final String latitude1, latitude2, latitude3, latitude4, longitude1, longitude2, longitude3, longitude4, size, description, plotname, farm, manager;

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
        farm = edFarm.getText().toString();
        manager = edManager.getText().toString();

        if (isFormFilled()) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.ADD_PLOT_URL,
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


        String latitude1, latitude2, latitude3, latitude4, longitude1, longitude2, longitude3, longitude4, size, description, plotname, farm, manager;

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
        farm = edFarm.getText().toString();
        manager = edManager.getText().toString();


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
            edFarm.setError(getString(R.string.cant_be_empty));
            return false;

        }

        if (TextUtils.isEmpty(manager)) {
            edManager.setError(getString(R.string.cant_be_empty));
            return false;

        }
        if (TextUtils.isEmpty(plotname)) {
            edPlotName.setError(getString(R.string.cant_be_empty));
            return false;

        }


        return true;


    }

}
