package com.mydemo.marketdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mydemo.marketdemo.utils.SPConstant;
import com.mydemo.marketdemo.utils.SPUtil;
import com.mydemo.marketdemo.view.GuideActivity;
import com.mydemo.marketdemo.view.MainActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Splash界面
 */
public class SplashActivity extends AppCompatActivity
{

    private CompositeSubscription mCompositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    private int delayTime = 2;

    @Override
    protected void onStart()
    {
        super.onStart();
        delayPlan();
    }

    /**
     * 延时2秒跳转主页面
     */
    private void delayPlan()
    {
        mCompositeSubscription = new CompositeSubscription();
        Subscription mSubscription = Observable.timer(delayTime, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>()
                {
                    @Override
                    public void call(Long aLong)
                    {
                        finishTask();
                    }
                });
        mCompositeSubscription.add(mSubscription);
    }

    private void finishTask()
    {
        Intent intent;
        if (SPUtil.getBoolean(getBaseContext(), SPConstant.IS_FIRST))
        {
            intent = new Intent(SplashActivity.this, GuideActivity.class);
        } else
        {
            intent = new Intent(SplashActivity.this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mCompositeSubscription.unsubscribe();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
