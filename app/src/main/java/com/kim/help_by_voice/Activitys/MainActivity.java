package com.kim.help_by_voice.Activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;

import com.kim.help_by_voice.entity.ChangeColorIconWithTextView;
import com.kim.help_by_voice.entity.MyApplication;
import com.kim.help_by_voice.entity.ToDialectAnswer;
import com.kim.help_by_voice.entity.ToDialectQuestion;
import com.kim.help_by_voice.fragment.AskFragment;
import com.kim.help_by_voice.fragment.HomeFragment;
import com.kim.help_by_voice.fragment.MandaniaFragment;
import com.kim.help_by_voice.fragment.MeFragment;
import com.kim.help_by_voice.fragment.SongFragment;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class MainActivity extends FragmentActivity implements
        OnPageChangeListener, OnClickListener {
    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;

    private List<ChangeColorIconWithTextView> mTabIndicator = new ArrayList<ChangeColorIconWithTextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        initDatas();
        initData();
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    //init fragments' data
    private void initDatas() {
        HomeFragment homeFragment = new HomeFragment();
        mTabs.add(homeFragment);
        MandaniaFragment dialectFragment = new MandaniaFragment();
        mTabs.add(dialectFragment);
        AskFragment askFragment = new AskFragment();
        mTabs.add(askFragment);
        SongFragment songFragment = new SongFragment();
        mTabs.add(songFragment);
        MeFragment meFragment = new MeFragment();
        mTabs.add(meFragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mTabs.get(arg0);
            }
        };

        initTabIndicator();
    }

    //init tab indicator
    private void initTabIndicator() {
        ChangeColorIconWithTextView one = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_one);
        ChangeColorIconWithTextView two = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_two);
        ChangeColorIconWithTextView three = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_three);
        ChangeColorIconWithTextView four = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_four);
        ChangeColorIconWithTextView five = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_five);

        mTabIndicator.add(one);
        mTabIndicator.add(two);
        mTabIndicator.add(three);
        mTabIndicator.add(four);
        mTabIndicator.add(five);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);

        one.setIconAlpha(1.0f);
    }

    @Override
    public void onPageSelected(int arg0) {
    }

    //on page scrolled
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        if (positionOffset > 0) {
            ChangeColorIconWithTextView left = mTabIndicator.get(position);
            ChangeColorIconWithTextView right = mTabIndicator.get(position + 1);

            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        //reset other tabs
        resetOtherTabs();
        switch (v.getId()) {
            case R.id.id_indicator_one:
                mTabIndicator.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                mTabIndicator.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                mTabIndicator.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_indicator_four:
                mTabIndicator.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);
                break;
            case R.id.id_indicator_five:
                mTabIndicator.get(4).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(4, false);
                break;
        }
    }

    /**
     * reset the other Tab
     */
    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicator.size(); i++) {
            mTabIndicator.get(i).setIconAlpha(0);
        }
    }

    //Generate simulated data
    public void initData(){
        MyApplication myApplication  = (MyApplication)getApplication();
        ArrayList<ToDialectAnswer> answerList = myApplication.getAnswersList();
        ArrayList<ToDialectQuestion> questionList = myApplication.getQuestionList();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
