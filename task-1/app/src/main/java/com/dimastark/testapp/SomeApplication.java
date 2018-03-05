package com.dimastark.testapp;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

public class SomeApplication extends Application {
    private static final String TAG = "Dima/TestApp";

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.d(TAG, String.format("onConfigurationChanged(orientation=%s)", newConfig.orientation));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.d(TAG, "onTerminate()");
    }
}
