package com.example.administrator.baseactivty;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.baseactivty.Base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by yzl on 2017/3/31.
 * viewpager的适配器
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<BaseFragment> fragments;
    public MyPagerAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
