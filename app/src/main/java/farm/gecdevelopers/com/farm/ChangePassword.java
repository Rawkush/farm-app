package farm.gecdevelopers.com.farm;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
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

public class ChangePassword extends AppCompatActivity {


    TextInputEditText edOldPassword, edNewPassword, edConfirmPassword;
    Button btnsubmit;
    String userId = "2";// TODO chnage it to current user
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        bindViews();
        init();

    }

    private void bindViews() {
        edOldPassword = findViewById(R.id.old_password);
        edNewPassword = findViewById(R.id.new_password);
        edConfirmPassword = findViewById(R.id.confirm_password);
        btnsubmit = findViewById(R.id.submit);
        queue = Volley.newRequestQueue(this);

    }

    private void init() {

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void changePassword() {

        if (isFormFilled()) {
            final String oldPassword = edOldPassword.getText().toString();
            final String newPassWord = edNewPassword.getText().toString();
            final String confirmPassword = edConfirmPassword.getText().toString();

            if (!newPassWord.equals(confirmPassword)) {
                edConfirmPassword.setError(getString(R.string.password_not_match));
                return;
            }

            StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.CHANGE_PASSWORD_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            //todo show a dialogbox instead of toast
                            Toast.makeText(ChangePassword.this, response, Toast.LENGTH_SHORT).show();
                            Log.i("Response is: ", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(ChangePassword.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> param = new HashMap<>();

                    param.put("chanpass", oldPassword);
                    param.put("curpass", newPassWord);
                    param.put("concurpass", confirmPassword);
                    param.put("user_id", userId);

                    return param;
                }
            };
            queue.add(stringRequest);
        }


    }


    private boolean isFormFilled() {


        String oldPassword = edOldPassword.getText().toString();
        String newPassWord = edNewPassword.getText().toString();
        String confirmPassword = edConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(oldPassword)) {
            edOldPassword.setError(getString(R.string.cant_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(newPassWord)) {
            edNewPassword.setError(getString(R.string.cant_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            edConfirmPassword.setError(getString(R.string.cant_be_empty));
            return false;
        }


        return true;
    }


}
