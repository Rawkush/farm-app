package farm.gecdevelopers.com.farm.activity.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import farm.gecdevelopers.com.farm.activity.Login;

public class CreateAccount extends AppCompatActivity {
    RadioButton rbType_2,rbType_3;
    EditText edUserName,edPassword, edEmail,edId,edPhone,edName;
    Button btLogin;
    RadioGroup radioGroup;
    final String TAG="My Tag";
    int type=0;
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        initViews();
        queue=  Volley.newRequestQueue(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(i);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    if(R.id.type_2==i){
                        type=2;
                    }else
                        type=3;

                }
            }

        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });


    }


    private void createAccount(){

        if(isFormFilled()){
            final String name= edUserName.getText().toString();
            final String email=edEmail.getText().toString();
            String password=edPassword.getText().toString();
            final String id=edId.getText().toString();
            String type=String.valueOf(this.type);
            String url="http://http://axxentfarms.com/farm/files/pages/examples2/createaccount.php?";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            if(response.equals("200")){
                                Toast.makeText(getApplicationContext(),"Created successfully",Toast.LENGTH_SHORT).show();
                               /* Intent intent = new Intent(Cr.this,CreateAccount.class);

                                startActivity(intent);*/
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
                    param.put("name",name);
                    param.put("client_num",id);
                    param.put("email",email);
                    param.put("number",edPhone.getText().toString());

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
        //TODO perform check or authenticity


    }



    private boolean isFormFilled(){
        //TODO adding error for wrong data and empt data

        if(edUserName.getText().toString().equals("") || edPassword.getText().toString().equals("")
                || edEmail.getText().toString().equals("")||edId.getText().toString().equals("")|| edPhone.getText().toString().equals(""))
            return false;
        if(!isTypeSelected())
            return  false;

        Log.v(TAG,"Form is filled properly");
        return  true;

    }


    private boolean isTypeSelected(){

        return rbType_3.isSelected() || rbType_2.isSelected() ;

    }

    private void initViews() {

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
    }


}
