package farm.gecdevelopers.com.farm;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;

public class NetworkUtility {
    static String res=null;

    public static String hitApi(){
            final String url="http://axxentfarms.com/farm/files/pages/examples2/fetchtable.php?";


            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.e("Response is: ", response);
                            res=response;

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    //Toast.makeText(DashBoardActivity.this,"Failed to fetch, please retry",Toast.LENGTH_SHORT).show();


                }
            });
            DashBoardActivity.queue.add(stringRequest);
return res;
        }

    }

