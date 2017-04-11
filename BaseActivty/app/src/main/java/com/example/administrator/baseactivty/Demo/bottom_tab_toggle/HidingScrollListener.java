package com.example.administrator.baseactivty.Demo.bottom_tab_toggle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


/**
 * This class is a ScrollListener for RecyclerView that allows to show/hide
 * views when list is scrolled.
 * 首页tab显示和隐藏监听
 */
public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {

    private static float HIDE_THRESHOLD = 10;      //隐藏阙值
    private static float SHOW_THRESHOLD = 70;   //显示阙值

    private int mToolbarOffset = 0;//抵消
    private boolean mControlsVisible = true;//是否显示
    private int mToolbarHeight;//底部导航栏高度
    private int mTotalScrolledDistance;//总滚动距离

    public HidingScrollListener(Context context, int height) {
        mToolbarHeight = height;//设置底部导航栏高度
        HIDE_THRESHOLD = height / 10; //隐藏阈值
        SHOW_THRESHOLD = height / 2;  //显示阈值
        Log.e("HidingScrollListener", "tab高度" + mToolbarHeight);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState != RecyclerView.SCROLL_STATE_IDLE) {
            //如果滚动状态是闲置进行位置设定
            Log.e("onScrollStateChanged", "mTotalScrolledDistance 滑动距离：" + mTotalScrolledDistance + "&mControlsVisible：" + mControlsVisible + "&mToolbarOffset：" + mToolbarOffset);
            if (mTotalScrolledDistance < mToolbarHeight) {//如果总滚动距离小于底部导航栏高度那么直接让导航栏重新显示
                //也就是说如果一开始滚动的距离相加没超过导航栏高度那么就会一直显示存在

                setVisible();
            } else {//如果总滚动距离大于底部导航栏高度
                if (mControlsVisible) {//判断是否显示 如果现在正在显示，也就是说现在正在上滑，上滑隐藏
                    if (mToolbarOffset > HIDE_THRESHOLD) {//如果这个值大于高度的十分之一，也就是显示只隐藏了十分之一
                        setInvisible();
                    } else {//有了一开始if的判断这个判断显得多余
                        setVisible();
                    }
                } else {//如果上次状态是隐藏，也就是说显示正在下滑，下滑显示
                    //因为此时mToolbarOffset=91，所以计算差为0，这样除非mToolbarOffset滑动有一定的值，这样才能去在隐藏状态下让他再次显示
                    if ((mToolbarHeight - mToolbarOffset) > SHOW_THRESHOLD) {//显示的值大于一半才让他显示
                        setVisible();
                    } else {
                        setInvisible();
                    }
                }
            }
        }else {
            setVisible();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);//dy正值是往下滑（手指向下），负值是往上滑
        Log.e("onScrolled", "dy：" + dy + "&mToolbarOffset 抵消值：" + mToolbarOffset + "&mToolbarHeight 底部tab高度：" + mToolbarHeight + "&mTotalScrolledDistance 滑动距离：" + mTotalScrolledDistance);
        if ((mToolbarOffset < mToolbarHeight && dy > 0) || (mToolbarOffset > 0 && dy < 0)) {
            //判断如果抵消值小于控件的高度并且是向下滑动（相当于是控件从无到有）||
            //或者判断抵消值如果大于0并且是向上滑动（相当于是从有到无）
            //当mToolbarOffset=mToolbarHeight 的高度，如果是上滑,则该方法不走，mToolbarOffset最大值为tab的高度
            //
            mToolbarOffset += dy;//因为有上下正负之分所以直接进行加法
        }
        //滑动作用距离总和区别于mToolbarOffset，mToolbarOffset最大值是tab的高度
        if (mTotalScrolledDistance < 0) {//如果滚动布局小于0那么让他等于0
            mTotalScrolledDistance = 0;
        } else {//否则如果一直向下滑那就一直相加到无限
            mTotalScrolledDistance += dy;
        }
        clipToolbarOffset();//每次进来重新设置一次滑动抵销值，最大值为tab的高度
        onMoved(mToolbarOffset);//抵消值回调
    }

    //每次进来设置抵消
    private void clipToolbarOffset() {
        if (mToolbarOffset > mToolbarHeight) {//如果抵消大于当前底部菜单高度那么设置一样相等
            mToolbarOffset = mToolbarHeight;
        } else if (mToolbarOffset < 0) {//如果小于0那么设置为0
            mToolbarOffset = 0;
        }
    }

    //显示
    public void setVisible() {
        if (mToolbarOffset > 0) {//如果滚动回调大于0让他直接显示并且触发变量
            onShow();
            mToolbarOffset = 0;
        }
        mControlsVisible = true;
    }

    //隐藏
    public void setInvisible() {
        if (mToolbarOffset < mToolbarHeight) {
            onHide();
            mToolbarOffset = mToolbarHeight;
        }
        mControlsVisible = false;
    }

    public abstract void onMoved(int distance);

    public abstract void onShow();

    public abstract void onHide();
}
