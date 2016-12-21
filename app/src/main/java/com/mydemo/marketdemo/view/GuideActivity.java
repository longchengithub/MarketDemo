package com.mydemo.marketdemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mydemo.marketdemo.R;
import com.mydemo.marketdemo.utils.DisplayUtil;
import com.mydemo.marketdemo.utils.SPConstant;
import com.mydemo.marketdemo.utils.SPUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity
{

    @BindView(R.id.guide_viewpager)
    ViewPager mGuideViewpager;
    @BindView(R.id.guide_points)
    LinearLayout mPoints;

    private GuideAdapter mGuideAdapter;
    private int prevIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        initPoints();
        mPoints.getChildAt(0).setEnabled(false);

        mGuideAdapter = new GuideAdapter();
        mGuideViewpager.setAdapter(mGuideAdapter);
        mGuideViewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
                mPoints.getChildAt(prevIndex).setEnabled(true);
                mPoints.getChildAt(position).setEnabled(false);
                prevIndex = position;
            }
        });
    }

    /**
     * 生成对应数量的小圆点
     */
    private void initPoints()
    {
        mPoints.removeAllViews();
        for (int i = 0; i < mGuidePics.length + 1; i++)
        {
            ImageView dot = new ImageView(getBaseContext());
            dot.setBackgroundResource(R.drawable.selector_point);
            dot.setScaleType(ImageView.ScaleType.CENTER);
            dot.setEnabled(true);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DisplayUtil.dp2px(getBaseContext(), 10),
                    DisplayUtil.dp2px(getBaseContext(), 10));
            params.leftMargin = 30;
            params.rightMargin = 30;
            dot.setLayoutParams(params);
            mPoints.addView(dot);
        }
    }

    private int[] mGuidePics = {R.mipmap.guide1, R.mipmap.guide2};

    private class GuideAdapter extends PagerAdapter
    {
        @Override
        public int getCount()
        {
            return mGuidePics.length + 1;
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            if (position == getCount() - 1)
            {
                View guideLogin = View.inflate(GuideActivity.this, R.layout.last_guide_pager, null);
                View tv_login = guideLogin.findViewById(R.id.guide_login);
                tv_login.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        //TODO 为了测试 默认永远属于第一次打开应用 进引导页面 改回SP值为false为不进
                        SPUtil.putBoolean(GuideActivity.this, SPConstant.IS_FIRST, true);
                        startActivity(new Intent(GuideActivity.this, MainActivity.class));
                        finish();
                    }
                });
                container.addView(guideLogin);
                return guideLogin;
            } else
            {
                ImageView imageView = new ImageView(getBaseContext());
                imageView.setImageResource(mGuidePics[position]);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                container.addView(imageView);
                return imageView;
            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
        }
    }
}
