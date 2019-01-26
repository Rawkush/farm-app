package farm.gecdevelopers.com.farm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.admin.CreateAccount;

public class Login extends AppCompatActivity {

    EditText edUsername,edPassword;
    Button button;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edPassword=findViewById(R.id.password);
        edUsername=findViewById(R.id.username);
        button=findViewById(R.id.button);
        queue=  Volley.newRequestQueue(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


    }


    private void login(){

        String url="http://axxentfarms.com/farm/files/pages/examples2/login.php?";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if(response=="200"){

                            Toast.makeText(getApplicationContext(),"Logged in successfully",Toast.LENGTH_SHORT).show();

                            //TODOcheck if logged in or not

                            Intent intent = new Intent(Login.this,CreateAccount.class);
                            startActivity(intent);
                        }
                        Log.e("Response is: ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  mTextView.setText("That didn't work!");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> param= new HashMap<>();
                param.put("email",edUsername.getText().toString());
                param.put("password",edPassword.getText().toString());
                param.put("login","123");
                return param;

            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };


            queue.add(stringRequest);


    }





}
