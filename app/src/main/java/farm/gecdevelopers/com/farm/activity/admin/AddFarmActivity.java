package farm.gecdevelopers.com.farm.activity.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class AddFarmActivity extends AppCompatActivity {




    EditText edActName,edActDesc;
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
        edActDesc= findViewById(R.id.desc);
        edActName= findViewById(R.id.actname);
        button= findViewById(R.id.button2);
        queue=  Volley.newRequestQueue(this);

    }

    private void sendDataToDatabse(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.ADD_FARM_ACTIVITY_URL,
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> param= new HashMap<>();
                param.put("actname",edActName.getText().toString());
                param.put("actdisc",edActDesc.getText().toString());
                return param;

            }

        };


        queue.add(stringRequest);


    }

}
