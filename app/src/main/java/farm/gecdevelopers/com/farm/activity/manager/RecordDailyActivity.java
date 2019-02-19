package farm.gecdevelopers.com.farm.activity.manager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class RecordDailyActivity extends AppCompatActivity {


    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_daily);
        queue = Volley.newRequestQueue(this);

        sendDataToDatabse();

    }


    private void sendDataToDatabse() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.ADD_FARM_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //todo show a dialogbox instead of toast
                        Toast.makeText(RecordDailyActivity.this, response, Toast.LENGTH_SHORT).show();


                        Log.i("Response is: ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(RecordDailyActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<>();


                return param;

            }

        };


        queue.add(stringRequest);


    }


}
