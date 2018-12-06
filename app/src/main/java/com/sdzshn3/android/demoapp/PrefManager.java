package com.sdzshn3.android.demoapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    private static final String DEMO_APP_PREF_KEY = "demo_app_pref";
    private static final String CURRENT_PROFILE = "current_profile";
    private static final String IS_LOGGED_IN = "IsLoggedIn";
    private SharedPreferences demoAppPref;
    private SharedPreferences.Editor demoAppPrefEditor;

    public PrefManager(Context context) {
        int PRIVATE_MODE = 0;
        demoAppPref = context.getSharedPreferences(DEMO_APP_PREF_KEY, PRIVATE_MODE);
        demoAppPrefEditor = demoAppPref.edit();
    }

    public boolean isLoggedIn() {
        return demoAppPref.getBoolean(IS_LOGGED_IN, false);
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        demoAppPrefEditor.putBoolean(IS_LOGGED_IN, isLoggedIn);
        demoAppPrefEditor.apply();
    }

    public int getCurrentActiveProfileId() {
        return demoAppPref.getInt(CURRENT_PROFILE, -1);
    }

    public void setCurrentActiveProfileId(int id) {
        demoAppPrefEditor.putInt(CURRENT_PROFILE, id);
        demoAppPrefEditor.apply();
    }
}
