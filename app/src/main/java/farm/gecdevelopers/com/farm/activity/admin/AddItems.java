package farm.gecdevelopers.com.farm.activity.admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import farm.gecdevelopers.com.farm.R;

public class AddItems extends AppCompatActivity {

    RequestQueue queue;
    private EditText edItemName, edDescription, edManufacturer;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        bindViews();
    }


    private void bindViews() {
        queue = Volley.newRequestQueue(this);

        edItemName = findViewById(R.id.item_name);
        edDescription = findViewById(R.id.description);
        edManufacturer = findViewById(R.id.manufacturer);
        btnSubmit = findViewById(R.id.submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToDatabse();
            }
        });

    }


    private void sendDataToDatabse() {
        String url = "http://axxentfarms.com/farm/files/pages/apps/additem.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(AddItems.this, response, Toast.LENGTH_SHORT).show();


                        Log.i("Response is: ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AddItems.this, error.getMessage(), Toast.LENGTH_SHORT).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<>();
                param.put("itemname", edItemName.getText().toString());
                param.put("itemdisc", edDescription.getText().toString());
                param.put("itemman", edManufacturer.getText().toString());
                return param;

            }

        };


        queue.add(stringRequest);


    }


}
