package com.mydemo.marketdemo.base;

import android.os.Bundle;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenlong on 2016/12/21.
 */

public abstract class RxBaseActivity extends RxAppCompatActivity
{
    private Unbinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        binder = ButterKnife.bind(this);
        initViews(savedInstanceState);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        binder.unbind();
    }

    protected abstract void initViews(Bundle savedInstanceState);

    public abstract int getLayoutId();
}
