package com.slamur.library.daolibrary.android.settings;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsUtils {

    public static SharedPreferences getSettings(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public static final String LOGIN_KEY = "Login key", DEFAULT_LOGIN = null;
    public static final String PASSWORD_KEY = "Password key", DEFAULT_PASSWORD = null;

    public static void setStringValue(Context context, String key, String value) {
        getSettings(context)
                .edit()
                .putString(key, value)
                .commit();
    }

    public static String getStringValue(Context context, String key, String defaultValue) {
        return getSettings(context)
                .getString(key, defaultValue);
    }

    public static String getLogin(Context context) {
        return getStringValue(context, LOGIN_KEY, DEFAULT_LOGIN);
    }

    public static String getPassword(Context context) {
        return getStringValue(context, PASSWORD_KEY, DEFAULT_PASSWORD);
    }
}
