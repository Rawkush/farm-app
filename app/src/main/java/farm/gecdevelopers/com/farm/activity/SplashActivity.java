package farm.gecdevelopers.com.farm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.SessionManagement;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.activity.manager.Manager_DashBoardActivity;

public class SplashActivity extends AppCompatActivity {

    int SPLASH_TIME_OUT=3000;
    String signedIn="";
    public static String type="";


public static SessionManagement session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
         session=new SessionManagement(SplashActivity.this);
        signedIn=session.pref.getString(SessionManagement.IS_SIGNED_IN,"");



        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if(signedIn.equals("true")){
                     type=session.pref.getString(SessionManagement.TYPE,"0");

                    if(type.equals("1")) {
                        Intent intent = new Intent(SplashActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                    }else if(type.equals("2")){
                        Intent intent = new Intent(SplashActivity.this, Manager_DashBoardActivity.class);
                        startActivity(intent);

                    }else if(type.equals("3")){
                        Intent intent = new Intent(SplashActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                    }

                }else{
                    Intent i=new Intent(SplashActivity.this,Login.class);
                    startActivity(i);
                }


                 finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
