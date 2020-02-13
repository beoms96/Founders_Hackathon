package com.example.myapplication;

import android.app.Application;

import com.example.myapplication.util.PrefsHelper;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PrefsHelper.initialize(getApplicationContext());

    }
}
