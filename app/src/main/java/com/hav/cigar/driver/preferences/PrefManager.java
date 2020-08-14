package com.hav.cigar.driver.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

public class PrefManager {
    Set<String> strings;
    private Context _context;

    public PrefManager(Context context) {
        this._context = context;

    }
    public Context getContext() {
        return _context;
    }

    public void setContext(Context context) {
        this._context = context;
    }

    protected SharedPreferences getSharedPreferences(String key) {
        return PreferenceManager.getDefaultSharedPreferences(_context);
    }

    public String getString(String key, String def) {
        SharedPreferences prefs = getSharedPreferences(key);
        return prefs.getString(key, def);
    }

    public void setString(String key, String val) {
        SharedPreferences prefs = getSharedPreferences(key);
        SharedPreferences.Editor e = prefs.edit();
        e.putString(key, val);
        e.apply();
    }

    public String getToken(String key, String def) {
        SharedPreferences prefs = getSharedPreferences(key);
        String b = prefs.getString(key, def);
        return b;
    }


    public void setToken(String key, String val) {
        SharedPreferences prefs = getSharedPreferences(key);
        SharedPreferences.Editor e = prefs.edit();
        e.putString(key, val);
        e.apply();
    }
    }




