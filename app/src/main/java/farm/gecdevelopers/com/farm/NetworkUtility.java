package farm.gecdevelopers.com.farm;

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;

public class NetworkUtility {
    static String res=null;

    final static String BASE_URL = "http://axxentfarms.com/farm/files/pages/examples2/fetchtable.php";

    public static URL buildUrl() {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws Exception {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();



        String JSONFILE = null;
        try {


            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if(hasInput)
                JSONFILE = scanner.next();
            else
                JSONFILE=null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONFILE;

    }



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

