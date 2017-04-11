package com.example.administrator.baseactivty;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.administrator.baseactivty.Base.BaseActivity;
import com.example.administrator.baseactivty.Base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MainActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout table;
    private ArrayList<BaseFragment> fragments;
    private ArrayList<String> title = new ArrayList<>();
    private MyPagerAdapter myPagerAdapter;
    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void intviews() {
        viewPager =  findView(R.id.fram);
        table = findView(R.id.table);

    }

    @Override
    public void intDate() {
        fragments = new ArrayList<>();
        MyFragment myFragment = new MyFragment();
        MyFragment1 myFragment1 = new MyFragment1();
        MyFragment2 myFragment2 = new MyFragment2();
        MyFragment3 myFragment3 = new MyFragment3();
        fragments.add(myFragment);
        fragments.add(myFragment1);
        fragments.add(myFragment2);
        fragments.add(myFragment3);
        title.add("实例");
        title.add("说明");
        title.add("示例");
        title.add("我的");
        for(int i = 0 ; i < title.size(); i++){
            table.addTab(table.newTab().setText(title.get(i)));
        }
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOffscreenPageLimit(4);
        table.setupWithViewPager(viewPager);
        for (int i=0;i<table.getTabCount();i++){
            table.getTabAt(i).setText(title.get(i));
        }
    }

    @Override
    public void intListener() {

    }

    @Override
    public void processClick(View view) {
        switch (view.getId()){

        }
    }
}
