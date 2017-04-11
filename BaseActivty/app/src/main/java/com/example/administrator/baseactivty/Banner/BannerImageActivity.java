package com.example.administrator.baseactivty.Banner;

/**
 * Created by Administrator on 2017/4/1.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.administrator.baseactivty.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告无限轮播类
 * 具体配置参阅github框架地址：https://github.com/youth5201314/banner
 */
public class BannerImageActivity extends AppCompatActivity {

    private Banner banner;
    private List<Integer> images;
    private List<String> titles;
    private final int BANNER_NUM = 5; //banner数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_banner_image);
        initBannerData();
        initBannerView();
    }

    //初始化广告数据
    private void initBannerData() {
        images = new ArrayList<>();
        for (int i = 0; i < BANNER_NUM; i++) {
            images.add(R.mipmap.ic_banner);
        }

        titles = new ArrayList<>();
        for (int i = 0; i < BANNER_NUM; i++) {
            titles.add("banner" + i);
        }
    }

    //初始化广告轮播
    private void initBannerView() {
        banner = (Banner) findViewById(R.id.banner);
        //设置banner样式（更换动画效果参考github地址）
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（当banner模式中有指示器时）左 中 右
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器（必须设置）
        banner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果（更换动画效果参考github地址）
        //banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //是否允许手动滑动轮播图,默认true
        banner.setViewPagerIsScroll(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置图片集合（必须设置）
        banner.setImages(images);
        //设置banner点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(BannerImageActivity.this, "点击了banner" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }
}

