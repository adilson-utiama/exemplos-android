package com.asuprojects.testearquivos.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtil {

    private SharedPreferences prefs;

    public SharedPrefsUtil(Context ctx) {
        prefs = ctx.getSharedPreferences("mypreferences", Context.MODE_PRIVATE);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    public boolean readBoolean(String key) {
        return prefs.getBoolean(key, false);
    }
}
