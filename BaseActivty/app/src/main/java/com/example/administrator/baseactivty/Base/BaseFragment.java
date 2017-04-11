package com.example.administrator.baseactivty.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yzl on 2017/3/31.
 * 抽取的BaseFragment，有懒加载的模式，这里采用的是ViewPager+Fragment，
 * 如果需要让Fragment进行缓存，那么必须对ViewPager进行缓存设置设置缓存
 * 页面viewPager.setOffscreenPageLimit(5);
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{

    private boolean isFirstLoad = true;//是否是第一次加载
    private boolean isVisable = false;//是否显示
    private boolean isinitView = false;//是否初始化view
    private View convertView;//整个fragment布局的view
    public SparseArray<View> mArray;
    public abstract int getLayoutID();//获取布局文件
    public abstract void intviews();//找布局中的控件view
    public abstract void intDate();//初始化数据
    public abstract void intListener();//初始化view的事件监听
    public abstract void processClick(View view);//处理view的点击事件

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mArray = new SparseArray<>();
        convertView = inflater.inflate(getLayoutID(),container,false);
        //进行查找控件
        intviews();
        isinitView = true;
        //进行加载数据
        lazyLoad();
        return convertView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {//此方法是在所有fragment生命周期之前进行调用
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){//用户可见的
            isVisable = true;//设置可以显示则进行加载数据
            lazyLoad();
        }else {//用户不可见的
            isVisable = false;//现在view不可见，不要进行加载数据
        }
    }

    /**
     * 懒加载数据与饿加载数据
     */
    private void lazyLoad(){
        if(!isFirstLoad || !isVisable || !isinitView){//view可见时的加载
            //如果不是第一次加载，不可见，不是初始化view则不加在数据
            return;
        }else {
            //初始化数据
            intListener();
            intDate();
            //设置不是第一次加载
            isFirstLoad = false;
        }
    }

    /**
     * 查找view控件
     * @param viewID view 的资源id号
     * @param <E>
     * @return 返回查找到的view
     */
    public  <E extends View> E findView(int viewID){
        E view = (E) mArray.get(viewID);
        if(view == null){
            view = (E) convertView.findViewById(viewID);
            if(view != null){
                mArray.append(viewID,view);
            }
        }
        return view;
    }

    /**
     * 为view绑定点击事件
     * @param view 绑定事件的view
     * @param <E>
     */
    public  <E extends View> void setOnClick(E view){
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        processClick(v);
    }
}
