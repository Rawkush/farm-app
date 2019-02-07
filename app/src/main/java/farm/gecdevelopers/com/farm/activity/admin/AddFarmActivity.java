package farm.gecdevelopers.com.farm.activity.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.Login;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
import farm.gecdevelopers.com.farm.activity.manager.Manager_DashBoardActivity;

import static farm.gecdevelopers.com.farm.activity.SplashActivity.session;

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
        String url="http://axxentfarms.com/farm/files/pages/app/addact.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        Log.i("Response is: ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AddFarmActivity.this,"Login failed",Toast.LENGTH_SHORT).show();


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
