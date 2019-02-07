package farm.gecdevelopers.com.farm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.SessionManagement;
import farm.gecdevelopers.com.farm.activity.admin.AddFarmActivity;
import farm.gecdevelopers.com.farm.activity.admin.CreateAccount;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.activity.manager.Manager_DashBoardActivity;

import static farm.gecdevelopers.com.farm.activity.SplashActivity.session;

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
                if(checkForEmptyField())
                    login();
                else
                    Toast.makeText(Login.this,"Fields can't be empty",Toast.LENGTH_SHORT).show();

            }
        });


    }


    private boolean checkForEmptyField(){
        return !edUsername.getText().toString().equals("") && !edPassword.getText().toString().equals("");
    }

    private void login(){

        String url="http://axxentfarms.com/farm/files/pages/app/login.php?";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject data= new JSONObject(response);
                            String login_status= data.getString("login_status");
                            if(login_status.equals("1")){
                                String type= data.getString("type");

                               //0 Toast.makeText(Login.this,"Login successful",Toast.LENGTH_SHORT).show();


                                if(type.equals("1")) {
                                    session.createLoginSession(edUsername.getText().toString(),edPassword.getText().toString(),"1");
                                    SplashActivity.type="1";
                                    //todo directing to dash board
                                    Intent intent = new Intent(Login.this, AddFarmActivity.class);
                                    startActivity(intent);
                                }else if(type.equals("2")){
                                    session.createLoginSession(edUsername.getText().toString(),edPassword.getText().toString(),"2");
                                    SplashActivity.type="2";

                                    Intent intent = new Intent(Login.this, Manager_DashBoardActivity.class);
                                    startActivity(intent);

                                }else if(type.equals("3")){
                                    session.createLoginSession(edUsername.getText().toString(),edPassword.getText().toString(),"3");
                                    SplashActivity.type="3";
                                    Intent intent = new Intent(Login.this, DashBoardActivity.class);
                                    startActivity(intent);
                                }

                                finish();

                            }else
                                Toast.makeText(Login.this,"Invalid Credential",Toast.LENGTH_SHORT).show();


                        } catch (JSONException e) {
                            Toast.makeText(Login.this,"Login failed",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                        Log.i("Response is: ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Login.this,"Login failed",Toast.LENGTH_SHORT).show();


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> param= new HashMap<>();
                param.put("email",edUsername.getText().toString());
                param.put("password",edPassword.getText().toString());
                return param;

            }

        };


            queue.add(stringRequest);


    }





}
