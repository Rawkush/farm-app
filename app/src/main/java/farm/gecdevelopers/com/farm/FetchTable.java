package farm.gecdevelopers.com.farm;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchTable implements NetworkUtility {

    private RequestQueue queue;
    private Context context;
    private static JSONObject table;

    public FetchTable(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);

    }

    public FetchTable() {
    }

    private void fetchTable() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, TABLE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("Response is: ", response);
                        try {
                            table = new JSONObject(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Failed to fetch, please retry", Toast.LENGTH_SHORT).show();


            }
        });
        queue.add(stringRequest);

    }

    public final void startConnection() {
        fetchTable();
    }

    public final JSONArray getAllUsers() {
        return table.optJSONArray("login_user");

    }

    public final JSONArray getFarmActivity() {
        return table.optJSONArray("addfarmactivity");

    }

    public final JSONArray getFarms() {
        return table.optJSONArray("add_f");

    }

    public final JSONArray getPlots() {
        return table.optJSONArray("add_farm");
    }


    public final JSONArray getItemsType() {
        return table.optJSONArray("additem");
    }


    public final JSONArray getDailyExpense() {
        return table.optJSONArray("dailyexpenses");
    }


}
