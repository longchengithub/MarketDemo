package com.mydemo.marketdemo.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mydemo.marketdemo.R;
import com.mydemo.marketdemo.base.RxBaseFragment;
import com.mydemo.marketdemo.widget.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by chenlong on 2016/12/20.
 */

public class HomeFragment extends RxBaseFragment
{
    @BindView(R.id.home_banner)
    Banner mBanner;
    @BindView(R.id.home_recyclerView)
    RecyclerView mHomeRecyclerView;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_home;
    }

    private List<Integer> images = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState)
    {

        images.add(R.drawable.banner1);
        images.add(R.drawable.banner);
        images.add(R.drawable.banner2);
        images.add(R.drawable.banner3);
        images.add(R.drawable.banner4);

        mBanner.setDelayTime(5);
        mBanner.setImages(images, 1);        //因为方法重载了 传随便一个数
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("HomeFragment", "我又创建了一次");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mBanner.onPause();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mBanner.startScroll();
    }
}
