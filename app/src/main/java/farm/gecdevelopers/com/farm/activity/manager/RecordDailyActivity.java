package farm.gecdevelopers.com.farm.activity.manager;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import farm.gecdevelopers.com.farm.NetworkUtility;
import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.admin.DashBoardActivity;
import farm.gecdevelopers.com.farm.models.FarmActivityData;
import farm.gecdevelopers.com.farm.models.PlotData;
import farm.gecdevelopers.com.farm.spinnerAdapter.FarmActivitySpinnerAdapter;
import farm.gecdevelopers.com.farm.spinnerAdapter.PlotNameSpinnerAdapter;

public class RecordDailyActivity extends AppCompatActivity {


    RequestQueue queue;
    private final int GALLERY_REQUEST = 1;
    Button btnImage, btnVideo, btnDocument;
    EditText edDate, edAcresCovered, edResources, edComments;
    ImageView imageView;
    ImageButton ibCalendar;
    Spinner spnPlotName, spnFarmActivity;
    String plotId = "", actId = "";
    int day = 0, month = 0, year = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_daily);
        bindViews();
        init();
        //  sendDataToDatabse();   // do this in submit button listner

    }

    private void init() {

        setDefaultTime();

        PlotNameSpinnerAdapter plotNameSpinnerAdapter = new PlotNameSpinnerAdapter(this, getPlotList());
        spnPlotName.setAdapter(plotNameSpinnerAdapter);
        spnPlotName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PlotData fm = (PlotData) parent.getItemAtPosition(position);
                plotId = fm.getFarmId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        FarmActivitySpinnerAdapter spinnerAdapter = new FarmActivitySpinnerAdapter(this, getFarmActivity());
        spnFarmActivity.setAdapter(spinnerAdapter);
        spnFarmActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FarmActivityData fm = (FarmActivityData) parent.getItemAtPosition(position);
                actId = fm.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        ibCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ActivityCompat.requestPermissions(
                        RecordDailyActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        GALLERY_REQUEST);


            }
        });


    }


    private void setDefaultTime() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        edDate.setText(String.valueOf(day) + "/" + month + "/" + year);
    }

    private void bindViews() {
        queue = Volley.newRequestQueue(this);
        spnFarmActivity = findViewById(R.id.farm_activity_spinner);
        spnPlotName = findViewById(R.id.plotname_spinner);
        btnDocument = findViewById(R.id.upload_document);
        btnImage = findViewById(R.id.upload_image);
        btnVideo = findViewById(R.id.upload_video);
        edAcresCovered = findViewById(R.id.acres_covered);
        edComments = findViewById(R.id.comments);
        edDate = findViewById(R.id.date);
        edResources = findViewById(R.id.resources);
        imageView = findViewById(R.id.imageView);
        ibCalendar = findViewById(R.id.ib_date);


    }

    private void openDatePicker() {

        DatePickerDialog.OnDateSetListener calendar = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                edDate.setText(String.valueOf(dayOfMonth) + "/" + monthOfYear + "/" + year);


            }

        };

        new DatePickerDialog(this, calendar, year, month, day).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == GALLERY_REQUEST) {


            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE")
                        , GALLERY_REQUEST);


            } else {
                Toast.makeText(getApplicationContext(), "you don't have permisssion to access gallery", Toast.LENGTH_SHORT).show();
            }

        }


    }

    private void sendDataToDatabse() {

        if (isFormFilled()) {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, NetworkUtility.ADD_DAILY_ACTIVITY,
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
                    param.put("loan_type", plotId);
                    param.put("act", actId);


                    return param;

                }

            };


            queue.add(stringRequest);


        }
    }

    private boolean isFormFilled() {


        String date, comment, resources, acresCovered;
        date = edDate.getText().toString();
        comment = edComments.getText().toString();
        resources = edResources.getText().toString();
        acresCovered = edAcresCovered.getText().toString();


        if (TextUtils.isEmpty(date)) {
            edDate.setError(getString(R.string.cant_be_empty));
            return false;
        }
        if (TextUtils.isEmpty(comment)) {
            edComments.setError(getString(R.string.cant_be_empty));
            return false;

        }
        if (TextUtils.isEmpty(resources)) {
            edResources.setError(getString(R.string.cant_be_empty));
            return false;

        }
        if (TextUtils.isEmpty(actId)) {

            Toast.makeText(this, "select Farm Activity", Toast.LENGTH_SHORT).show();
            return false;

        }
        if (TextUtils.isEmpty(acresCovered)) {
            edAcresCovered.setError(getString(R.string.cant_be_empty));
            return false;
        }

        if (TextUtils.isEmpty(plotId)) {
            Toast.makeText(this, "select Plot", Toast.LENGTH_SHORT).show();
            return false;

        }


        return true;


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri imageFilePath = data.getData();
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(imageFilePath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }


    }


    public ArrayList<PlotData> getPlotList() {
        ArrayList<PlotData> plotArrayList = new ArrayList<>();


        try {
            JSONArray jsonArray = DashBoardActivity.data.getPlots();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject eachMan = jsonArray.getJSONObject(i);
                String user = eachMan.getString("farm_manager");
                /* TODO get curent user from shared preference
                if(!user.equals(currentuser))
                   {
                   continue;
                   }

                 */
                String name = eachMan.getString("farm_name");
                String farmId = eachMan.getString("farm_id");
                plotArrayList.add(new PlotData(name, farmId));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return plotArrayList;
    }

    public ArrayList<FarmActivityData> getFarmActivity() {
        ArrayList<FarmActivityData> farmActivities = new ArrayList<>();
        try {
            JSONArray jsonArray = DashBoardActivity.data.getFarmActivity();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject eachMan = jsonArray.getJSONObject(i);


                String name = eachMan.getString("activity_name");
                String id = eachMan.getString("activity_id");
                farmActivities.add(new FarmActivityData(name, id, null));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return farmActivities;
    }



}
