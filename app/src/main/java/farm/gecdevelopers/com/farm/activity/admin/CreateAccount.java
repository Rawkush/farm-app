package farm.gecdevelopers.com.farm.activity.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class CreateAccount extends AppCompatActivity {
    RadioButton rbType_2,rbType_3;
    EditText edUserName,edPassword, edEmail,edId,edPhone,edName;
    Button btLogin;
    RadioGroup radioGroup;
    final String TAG="My Tag";
    String type;
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        initViews();



    }


    private void createAccount(){

        if(isFormFilled()){
            final String name = edName.getText().toString();
            final String userName = edUserName.getText().toString();
            final String email=edEmail.getText().toString();
            final String password = edPassword.getText().toString();
            final String id=edId.getText().toString();
            final String phone = edPhone.getText().toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.CREATE_ACCOUNT_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Toast.makeText(CreateAccount.this, response, Toast.LENGTH_SHORT).show();

                            Log.e("Response is: ", response);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {



                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String> param= new HashMap<>();
                    param.put("name",name);
                    param.put("client_num",id);
                    param.put("email",email);
                    param.put("number", phone);
                    param.put("username", userName);
                    param.put("password", password);
                    param.put("ustype", type);
                    return param;

                }


            };

            queue.add(stringRequest);


        }

    }



    private boolean isFormFilled(){

        String name = edName.getText().toString();
        String userName = edUserName.getText().toString();
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        String id = edId.getText().toString();
        String phone = edPhone.getText().toString();

        if (TextUtils.isEmpty(name)) {
            edName.setError(getString(R.string.cant_be_empty));
            return false;
        }

        if (TextUtils.isEmpty(userName)) {
            edUserName.setError(getString(R.string.cant_be_empty));
            return false;
        }


        if (TextUtils.isEmpty(email)) {
            edEmail.setError(getString(R.string.cant_be_empty));
            return false;
        }


        if (TextUtils.isEmpty(password)) {
            edPassword.setError(getString(R.string.cant_be_empty));
            return false;
        }


        if (TextUtils.isEmpty(id)) {
            edId.setError(getString(R.string.cant_be_empty));
            return false;
        }


        if (TextUtils.isEmpty(phone)) {
            edPhone.setError(getString(R.string.cant_be_empty));
            return false;
        }



        Log.v(TAG,"Form is filled properly");
        return  true;

    }




    private void initViews() {
        queue = Volley.newRequestQueue(this);
        edName=findViewById(R.id.name);
        rbType_2=findViewById(R.id.type_2);
        rbType_3= findViewById(R.id.type_3);
        edId=findViewById(R.id.id);
        edEmail=findViewById(R.id.email);
        edPhone=findViewById(R.id.phone_number);
        edPassword=findViewById(R.id.password);
        edUserName=findViewById(R.id.username);
        btLogin= findViewById(R.id.button);
        radioGroup=findViewById(R.id.rdGroup);
        type = getIntent().getStringExtra("type");


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });


    }


}
