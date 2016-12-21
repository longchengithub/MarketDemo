package com.mydemo.marketdemo;

import android.app.Application;

/**
 * Created by chenlong on 2016/12/19.
 */

public class MarketApp extends Application
{
    public static MarketApp mMarketApp;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mMarketApp = this;
    }

    public static MarketApp getInstance()
    {
        return mMarketApp;
    }
}
