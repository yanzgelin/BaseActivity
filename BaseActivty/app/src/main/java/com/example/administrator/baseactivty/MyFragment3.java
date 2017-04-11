package com.example.administrator.baseactivty;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.baseactivty.Base.BaseFragment;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MyFragment3 extends BaseFragment {
    private TextView tv_frag;
    @Override
    public int getLayoutID() {
        return R.layout.fragmentxml3;
    }

    @Override
    public void intviews() {
        tv_frag = findView(R.id.tv_frg);
    }

    @Override
    public void intDate() {

    }

    @Override
    public void intListener() {
        tv_frag.setOnClickListener(this);
    }

    @Override
    public void processClick(View view) {
        switch (view.getId()){
            case R.id.tv_frg:
                Toast.makeText(getContext(),"懒加载模式的fragment",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
