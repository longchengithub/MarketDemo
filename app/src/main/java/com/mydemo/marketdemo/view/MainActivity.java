package com.mydemo.marketdemo.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mydemo.marketdemo.R;
import com.mydemo.marketdemo.base.RxBaseFragment;
import com.mydemo.marketdemo.view.adapter.MainFragmentAdapter;
import com.mydemo.marketdemo.view.fragment.CategoriesFragment;
import com.mydemo.marketdemo.view.fragment.HomeFragment;
import com.mydemo.marketdemo.view.fragment.SearchFragment;
import com.mydemo.marketdemo.view.fragment.SettingFragment;
import com.mydemo.marketdemo.view.fragment.ShoppingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener
{

    @BindView(R.id.main_viewPager)
    ViewPager mViewPager;
    @BindView(R.id.main_radioGroup)
    RadioGroup mRadioGroup;

    private MainFragmentAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initFragment();

        initViewPager();

        initRadioGroup();
    }

    private void initRadioGroup()
    {
        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    private void initViewPager()
    {
        mPagerAdapter = new MainFragmentAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);    //设置最大缓存的页面数量
        mViewPager.addOnPageChangeListener(this);
    }

    private RxBaseFragment[] mFragments;

    private void initFragment()
    {
        HomeFragment mHomeFragment = new HomeFragment();
        SearchFragment mSearchFragment = new SearchFragment();
        SettingFragment mSettingFragment = new SettingFragment();
        ShoppingFragment mShoppingFragment = new ShoppingFragment();
        CategoriesFragment mCategoriesFragment = new CategoriesFragment();

        mFragments = new RxBaseFragment[]{mHomeFragment, mSearchFragment, mCategoriesFragment, mShoppingFragment, mSettingFragment};
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    private int prevIndex;

    @Override
    public void onPageSelected(int position)
    {
        ((RadioButton) mRadioGroup.getChildAt(prevIndex)).setChecked(false);
        ((RadioButton) mRadioGroup.getChildAt(position)).setChecked(true);
        prevIndex = position;
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        for (int i = 0; i < group.getChildCount(); i++)
        {
            if (((RadioButton) group.getChildAt(i)).isChecked())
            {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
