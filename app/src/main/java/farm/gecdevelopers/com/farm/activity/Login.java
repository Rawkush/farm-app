package farm.gecdevelopers.com.farm.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.activity.manager.Manager_DashBoardActivity;

import static farm.gecdevelopers.com.farm.activity.SplashActivity.session;

public class Login extends AppCompatActivity {

    final static int MY_SOCKET_TIMEOUT_MS=30000;
    private EditText mLoginEmail;
    private EditText mLoginPassword;
    private ProgressDialog mLoginProgress;
    private final String ADMIN = "1", MANAGER = "2", AUDITOR = "3";
    boolean doubleBackToExitPressedOnce = false;

    Button button;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initActivityComponents();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mLoginEmail.getText().toString();
                String password = mLoginPassword.getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {

                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("Please wait while we check your credentials.");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();

                    login(email, password);

                }

            }
        });


    }


    private void initActivityComponents(){

        mLoginEmail = findViewById(R.id.login_email);
        mLoginPassword = findViewById(R.id.login_password);
        button = findViewById(R.id.signInBTN);
        queue=  Volley.newRequestQueue(this);
        mLoginProgress = new ProgressDialog(this);

    }




    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    private void login(final String username, final String password){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject data= new JSONObject(response);
                            String login_status= data.getString("login_status");
                            if(login_status.equals("1")){
                                String type= data.getString("type");

                                //0 Toast.makeText(Login.this,"Login successful",Toast.LENGTH_SHORT).show();

                                switch (type) {
                                    case ADMIN: {
                                        session.createLoginSession(username, password, ADMIN);
                                        SplashActivity.type = ADMIN;
                                        Intent intent = new Intent(Login.this, DashBoardActivity.class);
                                        startActivity(intent);
                                        break;
                                    }
                                    case MANAGER: {
                                        session.createLoginSession(username, password, MANAGER);
                                        SplashActivity.type = MANAGER;

                                        Intent intent = new Intent(Login.this, Manager_DashBoardActivity.class);
                                        startActivity(intent);

                                        break;
                                    }
                                    case AUDITOR: {
                                        session.createLoginSession(username, password, AUDITOR);
                                        SplashActivity.type = AUDITOR;
                                        Intent intent = new Intent(Login.this, DashBoardActivity.class);
                                        startActivity(intent);
                                        break;
                                    }
                                }

                                finish();

                            }else{
                                Toast.makeText(Login.this,"Invalid Credential",Toast.LENGTH_SHORT).show();
mLoginProgress.dismiss();

                            }


                        } catch (JSONException e) {
                            Toast.makeText(Login.this,"Login failed",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                        Log.i("Response is: ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mLoginProgress.dismiss();

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    //This indicates that the reuest has either time out or there is no connection
                    Toast.makeText(Login.this,"time out",Toast.LENGTH_SHORT).show();

                } else if (error instanceof AuthFailureError) {
                    //Error indicating that there was an Authentication Failure while performing the request
                } else if (error instanceof ServerError) {
                    Toast.makeText(Login.this,"Server error",Toast.LENGTH_SHORT).show();

                    //Indicates that the server responded with a error response
                } else if (error instanceof NetworkError) {
                    //Indicates that there was network error while performing the request
                    Toast.makeText(Login.this,"Network error",Toast.LENGTH_SHORT).show();

                } else if (error instanceof ParseError) {
                    // Indicates that the server response could not be parsed
                }

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> param= new HashMap<>();
                param.put("email",username);
                param.put("password",password);
                return param;

            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(stringRequest);


    }





}
