package com.example.administrator.baseactivty;

import android.view.View;
import android.widget.Button;

import com.example.administrator.baseactivty.Banner.BannerImageActivity;
import com.example.administrator.baseactivty.Base.BaseFragment;
import com.example.administrator.baseactivty.Demo.CountDownViewActivity;
import com.example.administrator.baseactivty.Demo.bottom_tab_toggle.TabActivity;
import com.example.administrator.baseactivty.SWrecyclerview.SwRecyclerViewActivity;
import com.example.administrator.baseactivty.util.StartActivityUtil;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MyFragment extends BaseFragment {
    private Button butBannner;
    private Button but_swrv;
    private Button but_title;
    private Button but_time;
    @Override
    public int getLayoutID() {
        return R.layout.fragmentxml;
    }

    @Override
    public void intviews() {
        butBannner = findView(R.id.but_banner);
        but_swrv = findView(R.id.but_swrv);
        but_title = findView(R.id.but_title);
        but_time = findView(R.id.but_time);
    }

    @Override
    public void intDate() {

    }

    @Override
    public void intListener() {
        butBannner.setOnClickListener(this);
        but_swrv.setOnClickListener(this);
        but_title.setOnClickListener(this);
        but_time.setOnClickListener(this);
    }

    @Override
    public void processClick(View view) {
        switch (view.getId()){
            case R.id.but_banner://Banner轮播图
                StartActivityUtil.startActivity(getContext(),BannerImageActivity.class);
                break;
            case R.id.but_swrv:
                StartActivityUtil.startActivity(getContext(), SwRecyclerViewActivity.class);
                break;
            case R.id.but_title:
                StartActivityUtil.startActivity(getContext(), TabActivity.class);
                break;
            case R.id.but_time:
                StartActivityUtil.startActivity(getContext(), CountDownViewActivity.class);
                break;
        }
    }
}
