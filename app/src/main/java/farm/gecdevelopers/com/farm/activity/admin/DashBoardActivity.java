    package farm.gecdevelopers.com.farm.activity.admin;

    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
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

    import java.util.ArrayList;

    import farm.gecdevelopers.com.farm.R;
    import farm.gecdevelopers.com.farm.models.DailyActivity_Data;
    import farm.gecdevelopers.com.farm.models.DailyExpense_Data;
    import farm.gecdevelopers.com.farm.models.Document_Data;

    public class DashBoardActivity extends AppCompatActivity {

        RequestQueue queue;
        ArrayList<DailyActivity_Data> dailyActivity_data;
        ArrayList<DailyExpense_Data> dailyExpense_data;
        ArrayList<Document_Data> document_data;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_dash_board);
            queue =  Volley.newRequestQueue(this);
            dailyActivity_data= new ArrayList<>();
            dailyExpense_data= new ArrayList<DailyExpense_Data>();
            document_data= new ArrayList<>();
            fetchTable();
        }

        private void fetchTable(){
            final String url="http://axxentfarms.com/farm/files/pages/examples2/fetchtable.php?";


            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.e("Response is: ", response);
                            try {
                                JSONObject table = new JSONObject(response);
                                extractDataFromTable(table);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(DashBoardActivity.this,"Failed to fetch, please retry",Toast.LENGTH_SHORT).show();


                }
            });
            queue.add(stringRequest);

        }

        private void extractDataFromTable(JSONObject table){

            extractDailyActivity(table);
            extractDailyExpenses(table);
            extractDocumentData(table);
        }

        private void extractDailyActivity(JSONObject table){
            try {
                JSONArray array= table.getJSONArray("dailyactivity");

                for(int i=0;i<array.length();i++){


                    JSONObject data=array.getJSONObject(i);
                    DailyActivity_Data s= new DailyActivity_Data();


                    s.setDailyAcitvityID(Long.parseLong(data.getString("dactivity_id")));
                    s.setFarmID(Long.parseLong(data.getString("farmid")));
                    s.setImageName(data.getString("imgname"));
                    s.setDocName(data.getString("docname"));
                    s.setVideoName(data.getString("vidname"));
                    s.setActid(Long.parseLong(data.getString("actid")));
                    s.setDetails(data.getString("det"));
                    s.setComments(data.getString("cmts"));
                    s.setFarmLatitude(data.getString("farmlat"));
                    s.setFarmLongitude(data.getString("farmlong"));
                    s.setDateAndTime(data.getString("date"));
                    s.setUserId(data.getInt("userid"));
                    s.setHect(data.getString("hect"));
                    s.setStatus(data.getInt("status"));
                    s.setReason(data.getInt("reason"));

                    dailyActivity_data.add(s);


                }







            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        private void extractDailyExpenses(JSONObject table){
            try {
                JSONArray array= table.getJSONArray("dailyexpenses");

                for(int i=0;i<array.length();i++){

                    JSONObject data=array.getJSONObject(i);
                    DailyExpense_Data s= new DailyExpense_Data();
                    s.setId(Long.parseLong(data.getString("id")));
                    s.setFarmId(data.getInt("farmid"));
                    s.setPurpose(data.getString("purpose"));
                    s.setSupplier(data.getString("supplier"));
                    s.setDescription(data.getString("description"));
                    s.setDateAndTime(data.getString("datetime"));
                    s.setUnit(Long.parseLong(data.getString("unit")));
                    s.setUnitPrice(Long.parseLong(data.getString("unitprice")));
                    s.setTotal(Long.parseLong(data.getString("total")));
                    s.setUserId(Long.parseLong(data.getString("userid")));
                    dailyExpense_data.add(s);

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        private  void extractDocumentData(JSONObject table){

            try {
                JSONArray array= table.getJSONArray("document");

                for(int i=0;i<array.length();i++){

                    JSONObject data=array.getJSONObject(i);
                    Document_Data s= new Document_Data();

                    s.setId(Long.parseLong(data.getString("id")));
                    s.setUserId(data.getInt("user_id"));
                    s.setName(data.getString("name"));
                    s.setLink(data.getString("link"));
                    document_data.add(s);

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
