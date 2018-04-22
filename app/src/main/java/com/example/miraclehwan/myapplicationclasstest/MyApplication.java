package com.example.miraclehwan.myapplicationclasstest;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application{

    private static String mLatestActiveActivityName = null;
    private static long mLatestActiveTime = 0;

    private static MyApplication instance;

    public static MyApplication getAppInstance() { return instance; }

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
    }

    public void setLatestActive(String setLatestActiveActivityName, long setLatestActiveTime){
        mLatestActiveActivityName = setLatestActiveActivityName;
        mLatestActiveTime = setLatestActiveTime;
        Log.e("daehwanlog", String.valueOf(setLatestActiveTime));
    }

    public boolean isStartPinActivity(long CurrentTime){
        long IntervalTime = CurrentTime - mLatestActiveTime;
        if (0 <= IntervalTime && IntervalTime <= 5000){
            return false;
        }
        return true;
    }

    public static String getmLatestActiveActivityName() {
        return mLatestActiveActivityName;
    }

}
