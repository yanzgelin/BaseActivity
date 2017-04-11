package com.example.administrator.baseactivty.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;

/**
 * 抽取的BaseActivity
 */

public abstract class  BaseActivity extends AppCompatActivity implements View.OnClickListener{

    public SparseArray<View> mArray;
    public abstract int getLayoutID();//获取布局文件
    public abstract void intviews();//找布局中的控件view
    public abstract void intDate();//初始化数据
    public abstract void intListener();//初始化view的事件监听
    public abstract void processClick(View view);//处理view的点击事件
    /**
     * view 的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        processClick(v);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//对标题栏进行隐藏
        setContentView(getLayoutID());
        //初始化集合
        mArray = new SparseArray<>();
        intviews();//进行查找控件
        intDate();//进行初始化数据
        intListener();//进行初始化点击事件

    }

    /**
     * 查找view控件将其放到集合中
     * @param viewID 所要查找view的id
     * @param <E> 返回的view
     * @return
     */
    public <E extends View> E findView(int viewID){
        E view = (E)mArray.get(viewID);
        if(view == null){//如果查找的view不在集合里将其添加到集合里
            view = (E) findViewById(viewID);
            if(view != null){
                mArray.append(viewID,view);
            }
        }
        return  view;
    }

    /**
     * 给view设置点击事件
     * @param view 绑定事件的view
     * @param <E>
     */
    public <E extends View> void setOnClick(E view){
        view.setOnClickListener(this);
    }

}
