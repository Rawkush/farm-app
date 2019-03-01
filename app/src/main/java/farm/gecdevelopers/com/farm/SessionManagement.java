package farm.gecdevelopers.com.farm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import farm.gecdevelopers.com.farm.activity.Login;


public class SessionManagement {
    public static SharedPreferences pref;

    // Editor for Shared preferences
    static Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    public static final String PREF_NAME = "FarmPref";

    // All Shared Preferences Keys
    public static final String IS_SIGNED_IN = "IsSignedIn";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PWD";
    public static final String TYPE = "TYPE";


    public SessionManagement(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String username, String pwd,String type){
        editor.putString(IS_SIGNED_IN, "true");
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, pwd);
        editor.putString(TYPE, type);

        // commit changes
        editor.commit();
    }

    /*public boolean isSignedIn(){
        return pref.getBoolean(IS_SIGNED_IN, false);
    }*/

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, Login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);

    }

}
