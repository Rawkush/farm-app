package farm.gecdevelopers.com.farm.activity.admin;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class AddFarmActivity extends AppCompatActivity implements NetworkUtility {


    TextInputEditText edActName, edActDesc;
    Button button;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_farmactivity);

        bindViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToDatabse();
            }
        });

    }

    private void bindViews(){

        Toolbar toolbar = findViewById(R.id.add_farm_app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Farm Activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        edActDesc= findViewById(R.id.desc);
        edActName= findViewById(R.id.actname);
        button= findViewById(R.id.submit);
        queue=  Volley.newRequestQueue(this);

    }

    private void sendDataToDatabse() {

        if (isFormFilled()) {

            final String actvityName = edActName.getText().toString();
            final String description = edActDesc.getText().toString();

            if (isFormFilled()) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, ADD_FARM_ACTIVITY_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                //todo show a dialogbox instead of toast
                                Toast.makeText(AddFarmActivity.this, response, Toast.LENGTH_SHORT).show();
                                Log.i("Response is: ", response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(AddFarmActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> param = new HashMap<>();
                        param.put("actname", actvityName);
                        param.put("actdisc", description);
                        return param;

                    }

                };


                queue.add(stringRequest);


            }
        }
    }


    private boolean isFormFilled() {

        String actvityName = edActName.getText().toString();
        String description = edActDesc.getText().toString();


        if (TextUtils.isEmpty(actvityName)) {
            edActName.setError(getString(R.string.cant_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(description)) {
            edActDesc.setError(getString(R.string.cant_be_empty));
            return false;
        }
        return true;

    }

}
