package farm.gecdevelopers.com.farm.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import farm.gecdevelopers.com.farm.FetchTable;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.SessionManagement;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.activity.manager.Manager_DashBoardActivity;

public class SplashActivity extends AppCompatActivity {

    public static String type = "";
    public static SessionManagement session;
    int SPLASH_TIME_OUT = 2200;
    String signedIn = "";
    View parent;
    TextView axxent;
    Animation transit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        axxent = findViewById(R.id.axxent);

        session = new SessionManagement(SplashActivity.this);
        signedIn = session.pref.getString(SessionManagement.IS_SIGNED_IN, "");


        DashBoardActivity.data = new FetchTable(this);
        DashBoardActivity.data.startConnection();

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        transit = AnimationUtils.loadAnimation(this, R.anim.transit);
        axxent.setAnimation(transit);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if (signedIn.equals("true")) {
                    type = session.pref.getString(SessionManagement.TYPE, "0");


                    if (type.equals("1")) {
                        Intent intent = new Intent(SplashActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                    } else if (type.equals("2")) {
                        Intent intent = new Intent(SplashActivity.this, Manager_DashBoardActivity.class);
                        startActivity(intent);

                    } else if (type.equals("3")) {
                        Intent intent = new Intent(SplashActivity.this, DashBoardActivity.class);
                        startActivity(intent);
                    }

                } else {
                    Intent i = new Intent(SplashActivity.this, Login.class);
                    startActivity(i);
                }


                finish();
            }
        }, SPLASH_TIME_OUT);



   /*     runOnUiThread(new Runnable() {

            @Override
            public void run() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //add your code here
                    }
                }, 1000);

            }
        });*/

    }
}
