package com.mydemo.marketdemo.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chenlong on 2016/12/21.
 */

public abstract class RxBaseFragment extends RxFragment
{
    private View mParentView;

    private Unbinder binder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if (mParentView == null)
            mParentView = inflater.inflate(getLayoutId(), container, false);
        return mParentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        binder = ButterKnife.bind(this, view);
        initView(savedInstanceState);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binder.unbind();
    }

    protected abstract void initView(Bundle savedInstanceState);

    public abstract int getLayoutId();
}
