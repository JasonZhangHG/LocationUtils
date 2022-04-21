package com.locationdemo.android;

import com.blankj.utilcode.util.Utils;

import androidx.multidex.MultiDexApplication;

public class CCApplication extends MultiDexApplication {

    private static CCApplication INSTANCE;

    public static CCApplication getInstance() {
        return INSTANCE;
    }

    public CCApplication() {
        INSTANCE = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        Utils.init(this);
    }

}
