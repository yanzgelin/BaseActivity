package com.example.administrator.baseactivty;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by yzl on 2017/3/31.
 *
 * 广告倒计时http://www.jianshu.com/p/3db73ba78882
 */

public class SplashScreenActivity extends AppCompatActivity {
    private boolean isOk = true;
    private int time = 10;
    private TextView tvTime;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what == 0){
                tvTime.setText("还剩"+time+"s");
                time--;
                if(time<=0){
                    isOk = false;
                }
            }else if(msg.what == 1){
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        tvTime = (TextView) findViewById(R.id.time);
        fun2();
       /* new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, 6000);*/
    }

    private void fun2(){
        new Thread(){
            @Override
            public void run() {
                while(isOk){
                    try {
                        Thread.sleep(1000);
                        if(time == 0){
                            handler.sendEmptyMessage(1);
                        }else {
                            handler.sendEmptyMessage(0);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
