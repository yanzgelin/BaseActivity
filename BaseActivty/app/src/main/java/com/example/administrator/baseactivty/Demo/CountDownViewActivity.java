package com.example.administrator.baseactivty.Demo;

import android.view.View;
import android.widget.Button;

import com.example.administrator.baseactivty.Base.BaseActivity;
import com.example.administrator.baseactivty.R;
import com.example.administrator.baseactivty.widget.CountDownView;

/**
 * Created by Administrator on 2017/4/1.
 */

public class CountDownViewActivity extends BaseActivity {
    private CountDownView countDownView;
    private Button but_start;

    @Override
    public int getLayoutID() {
        return R.layout.activity_countdown;
    }

    @Override
    public void intviews() {
        countDownView = findView(R.id.count_down);
        but_start = findView(R.id.but_start);
    }

    @Override
    public void intDate() {

    }

    @Override
    public void intListener() {
        countDownView.setOnClickListener(this);
        but_start.setOnClickListener(this);
    }

    @Override
    public void processClick(View view) {
        switch (view.getId()){
            case R.id.count_down:
                //countDownView.start();
                break;
            case R.id.but_start:
                countDownView.start();
                break;
        }
    }
}
