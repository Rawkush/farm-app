package farm.gecdevelopers.com.farm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import farm.gecdevelopers.com.farm.R;

public class Login extends AppCompatActivity {
    RadioButton rbType_1,rbType_2,rbType_3;
    EditText edUserName,edPassword, edEmail,edClientId;
    Button btLogin;
    final String TAG="My Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


    }

private void login(){
        isFormFilled();
        //TODO perform check or authenticity

}


private boolean isFormFilled(){
        //TODO adding error for wrong data and empt data

        if(edUserName.getText().toString().equals("") || edPassword.getText().toString().equals("")
                || edEmail.getText().toString().equals("")||edClientId.getText().toString().equals(""))
            return false;
        if(!isTypeSelected())
            return  false;

        Log.v(TAG,"Form is filled properly");
        return  true;
}

private boolean isTypeSelected(){
    return rbType_3.isSelected() || rbType_2.isSelected() || rbType_1.isSelected();
}
private void initViews() {

    rbType_1=findViewById(R.id.type_1);
    rbType_2=findViewById(R.id.type_2);
    rbType_3= findViewById(R.id.type_3);
    edClientId=findViewById(R.id.client_id);
    edEmail=findViewById(R.id.email);
    edPassword=findViewById(R.id.password);
    edUserName=findViewById(R.id.username);
    btLogin= findViewById(R.id.button);
}


}
