package farm.gecdevelopers.com.farm.activity.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class AddFarm extends AppCompatActivity {


    Button btnSubmit;
    EditText edLatitude1, edLatitude2, edLatitude3, edLatitude4, edLatitude5, edLatitude6, edLatitude7, edLatitude8, edLatitude9,
            edLatitude10, edLongitude1, edLongitude2, edLongitude3, edLongitude4, edLongitude5, edLongitude6, edLongitude7,
            edLongitude8, edLongitude9, edLongitude10, edSize, edFarmName, edDescription;

    LinearLayout linearLayout;
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_farm);
        bindViews();


    }


    private void bindViews() {
        queue = Volley.newRequestQueue(this);

        linearLayout = (LinearLayout) findViewById(R.id.linear_layout);
        edLatitude1 = findViewById(R.id.latitude1);
        edLatitude2 = findViewById(R.id.latitude2);
        edLatitude3 = findViewById(R.id.latitude3);
        edLatitude4 = findViewById(R.id.latitude4);
        edLatitude5 = findViewById(R.id.latitude5);
        edLatitude6 = findViewById(R.id.latitude6);
        edLatitude7 = findViewById(R.id.latitude7);
        edLatitude8 = findViewById(R.id.latitude8);
        edLatitude9 = findViewById(R.id.latitude9);
        edLatitude10 = findViewById(R.id.latitude10);

        edLongitude1 = findViewById(R.id.longitude1);
        edLongitude2 = findViewById(R.id.longitude2);
        edLongitude3 = findViewById(R.id.longitude3);
        edLongitude4 = findViewById(R.id.longitude4);
        edLongitude5 = findViewById(R.id.longitude5);
        edLongitude6 = findViewById(R.id.longitude6);
        edLongitude7 = findViewById(R.id.longitude7);
        edLongitude8 = findViewById(R.id.longitude8);
        edLongitude9 = findViewById(R.id.longitude9);
        edLongitude10 = findViewById(R.id.longitude10);

        edSize = findViewById(R.id.size);
        edDescription = findViewById(R.id.description);
        edFarmName = findViewById(R.id.farm_name);
        btnSubmit = findViewById(R.id.submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToDatabse();
            }
        });


    }


    private void sendDataToDatabse() {


        if (isFormFilled()) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.ADD_FARM_ACTIVITY_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //todo show a dialogbox instead of toast
                            Toast.makeText(AddFarm.this, response, Toast.LENGTH_SHORT).show();


                            Log.i("Response is: ", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(AddFarm.this, error.getMessage(), Toast.LENGTH_SHORT).show();


                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> param = new HashMap<>();

                    final int childCount = linearLayout.getChildCount();

                    for (int i = 0; i < childCount; i++) {

                        View v = linearLayout.getChildAt(i);

                        if (v instanceof EditText) {
                            String tag = v.getTag().toString();
                            String value = ((EditText) v).getText().toString();

                            if (TextUtils.isEmpty(value)) {
                                continue;
                            }

                            param.put(tag, value);

                        }


                    }


                    return param;

                }

            };


            queue.add(stringRequest);


        }

    }


    private boolean isFormFilled() {

        String latitude1 = edLatitude1.getText().toString();
        String longitude1 = edLongitude1.getText().toString();
        String farmName = edFarmName.getText().toString();
        String description = edDescription.getText().toString();
        String size = edSize.getText().toString();

        if (TextUtils.isEmpty(description)) {
            edDescription.setError(getString(R.string.cant_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(size)) {
            edSize.setError(getString(R.string.cant_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(farmName)) {
            edFarmName.setError(getString(R.string.cant_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(latitude1)) {
            edLatitude1.setError(getString(R.string.cant_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(longitude1)) {
            edLongitude1.setError(getString(R.string.cant_be_empty));
            return false;
        }


        return true;
    }


}
