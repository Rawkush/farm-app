package farm.gecdevelopers.com.farm.activity.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    EditText edLatOne, edLatTwo, edLatThree, edLatFour, edLongOne, geLongTwo, edLongThree, edLongFour;
    Button submit;
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


    }

    private void sendDataToDatabse() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.ADD_ITEM_TYPE_URL,
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

                param.put("loan_t", "selectt farm");
                param.put("loan_type", "select manager");
                param.put("farmdisc", "description");
                param.put("latone", "latone");
                param.put("longone", "longone");
                param.put("lattwo", "longone");
                param.put("longtwo", "longone");
                param.put("latthree", "longone");
                param.put("longthree", "longone");
                param.put("latfour", "longone");
                param.put("longfour", "longone");
                param.put("farmname", "longone");
                param.put("farmsize", "longone");
                return param;


            }

        };


        queue.add(stringRequest);


    }



}
