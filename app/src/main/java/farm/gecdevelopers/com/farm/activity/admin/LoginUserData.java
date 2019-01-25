package farm.gecdevelopers.com.farm.activity.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import farm.gecdevelopers.com.farm.R;

public class LoginUserData extends AppCompatActivity {
    RadioButton rbType_2,rbType_3;
    EditText edUserName,edPassword, edEmail,edId;
    Button btLogin;
    RadioGroup radioGroup;
    final String TAG="My Tag";
    int type=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_data);

        initViews();

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
                || edEmail.getText().toString().equals("")||edId.getText().toString().equals(""))
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

    rbType_2=findViewById(R.id.type_2);
    rbType_3= findViewById(R.id.type_3);
    edId=findViewById(R.id.id);
    edEmail=findViewById(R.id.email);
    edPassword=findViewById(R.id.password);
    edUserName=findViewById(R.id.username);
    btLogin= findViewById(R.id.button);
    radioGroup=findViewById(R.id.rdGroup);
}


}
