package com.hav.cigar.driver.utility;

import android.app.Application;

import com.hav.cigar.driver.preferences.PrefManager;

public class MyApplication extends Application {
    private PrefManager preferences;;
    @Override
    public void onCreate() {
        super.onCreate();

        doInit();
    }

    private void doInit() {
        this.preferences = new PrefManager(this);
    }

    public synchronized PrefManager getPreferences() {
        return preferences;
    }

}
