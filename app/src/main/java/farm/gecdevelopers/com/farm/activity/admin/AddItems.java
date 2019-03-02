package farm.gecdevelopers.com.farm.activity.admin;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class AddItems extends AppCompatActivity implements NetworkUtility {

    RequestQueue queue;

    private TextInputLayout edItemName;
    private TextInputLayout edDescription;
    private TextInputLayout edManufacturer;


    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        bindViews();
        init();
    }


    private void bindViews() {
        queue = Volley.newRequestQueue(this);

        edItemName = findViewById(R.id.item_name);
        edDescription = findViewById(R.id.description);
        edManufacturer = findViewById(R.id.manufacturer);
        btnSubmit = findViewById(R.id.submit);

    }

    private void init() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToDatabse();
            }
        });


    }


    private void sendDataToDatabse() {
        if (isFormFilled()) {

            final String itemName = edItemName.getEditText().getText().toString();
            final String description = edDescription.getEditText().getText().toString();
            final String manufacturer = edManufacturer.getEditText().getText().toString();


            StringRequest stringRequest = new StringRequest(Request.Method.POST, ADD_ITEM_TYPE_URL,
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
                    param.put("itemname", itemName);
                    param.put("itemdisc", description);
                    param.put("itemman", manufacturer);
                    return param;

                }

            };


            queue.add(stringRequest);


        }
    }


    private boolean isFormFilled() {

        String itemName = edItemName.getEditText().getText().toString();
        String description = edDescription.getEditText().getText().toString();
        String manufacturer = edManufacturer.getEditText().getText().toString();


        edDescription.setErrorEnabled(false);
        edDescription.setError(null);
        edItemName.setErrorEnabled(false);
        edItemName.setError(null);
        edManufacturer.setErrorEnabled(false);
        edManufacturer.setError(null);

        if (TextUtils.isEmpty(itemName)) {

            edItemName.setErrorEnabled(true);
            edItemName.setError(getString(R.string.cant_be_empty));
            return false;
        }

        if (TextUtils.isEmpty(description)) {

            edDescription.setErrorEnabled(true);
            edDescription.setError(getString(R.string.cant_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(manufacturer)) {

            edManufacturer.setErrorEnabled(true);
            edManufacturer.setError(getString(R.string.cant_be_empty));
            return false;
        }

        return true;

    }



}
