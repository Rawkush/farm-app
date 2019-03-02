package farm.gecdevelopers.com.farm.activity.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.admin.AddFarm;

public class Manager_DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager__dash_board);


        Intent intent = new Intent(this, AddFarm.class);
        startActivity(intent);

    }


}
