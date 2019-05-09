package com.nazran.newsviews.app;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * MyAppPrefsManager handles some Prefs of NewsViews Application
 **/


public class MyAppPrefsManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor prefsEditor;
    
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "NewsViews_Prefs";

    private static final String IS_USER_LOGGED_IN = "isLogged_in";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public MyAppPrefsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        prefsEditor = sharedPreferences.edit();
    }
    
    
    public void setUserLoggedIn(boolean isUserLoggedIn) {
        prefsEditor.putBoolean(IS_USER_LOGGED_IN, isUserLoggedIn);
        prefsEditor.commit();
    }

    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(IS_USER_LOGGED_IN, false);
    }


    public void setFirstTimeLaunch(boolean isFirstTimeLaunch) {
        prefsEditor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTimeLaunch);
        prefsEditor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
