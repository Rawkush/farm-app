package farm.gecdevelopers.com.farm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import farm.gecdevelopers.com.farm.BackgroundWorker;
import farm.gecdevelopers.com.farm.R;

public class Login extends AppCompatActivity {

    EditText edUsername,edPassword;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edPassword=findViewById(R.id.password);
        edUsername=findViewById(R.id.username);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });


    }



    private void login(){

        String username=edUsername.getText().toString();
        String password=edPassword.getText().toString();

        BackgroundWorker backgroundWorker= new BackgroundWorker(this);
        backgroundWorker.execute("login",username,password);
    }
}
