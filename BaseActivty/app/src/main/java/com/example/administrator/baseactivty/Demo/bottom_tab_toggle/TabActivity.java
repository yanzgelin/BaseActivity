package com.example.administrator.baseactivty.Demo.bottom_tab_toggle;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.baseactivty.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 底部标题栏的显示与隐藏activity
 */
public class TabActivity extends AppCompatActivity {
    private static final String TAG = "Main";
    RecyclerView recyclerView;
    LinearLayout llBottom;
    List<String> listDatas;
    private static int height = 0;
    HidingScrollListener listener;
    private boolean aBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        recySetting();
        ViewTreeObserver viewTreeObserver = llBottom.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                height = llBottom.getMeasuredHeight();
                //必须放在该方法中，这样保证了height作为listener借口不为0
                Log.e(TAG, "onPreDraw");
                initHidingScrollListener();
                recyclerView.addOnScrollListener(listener);
                llBottom.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
    }


    private void recySetting() {
        listDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            listDatas.add("国王与乞丐" + i);
        }
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);
        recyclerView = (RecyclerView) findViewById(R.id.ryv);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, listDatas);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    /**
     * 初始化recycle滑动监听
     */
    private void initHidingScrollListener() {
        listener = new HidingScrollListener(this, height) {
            @Override
            public void onMoved(int distance) {
                Log.e(TAG, "onMoved distance：" + distance + "&mToolbarHeight：" + height);
                //移动时：重新设置tab的bottomMatgin
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) llBottom.getLayoutParams();
                //向上滑是正值 此时应该隐藏，对应的tab应该下移，所以设置为负值
                //向下滑是负值，此时应该显示，对应的tab应该上移，负负得正
                params.bottomMargin = (-distance);
                llBottom.setLayoutParams(params);
                if (distance >= height) {//在滑动时候判断是否底部菜单在显示
                    //此时tab已经隐藏，置为false
                    aBoolean = false;
                } else {
                    //此时tab正在显示,置为true
                    aBoolean = true;
                }
            }

            @Override
            public void onShow() {///显示的时候更改变量
                aBoolean = true;
                //此时tab显示，距离底部为0
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) llBottom.getLayoutParams();
                params.bottomMargin = 0;
                llBottom.setLayoutParams(params);
            }

            @Override
            public void onHide() {//隐藏时候更改状态
                aBoolean = false;
                //此时tab隐藏，距离底部为tab高度的负值
                //为什么这里换成LIneaLayout就不行？？
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) llBottom.getLayoutParams();
                params.bottomMargin = -height;
                llBottom.setLayoutParams(params);
            }
        };
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<String> list;
        LayoutInflater inflater;
        Context context;

        public MyRecyclerViewAdapter(Context context, List<String> list) {
            this.list = list;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.item_list_text_layout, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof VH) {
                VH vh = (VH) holder;
                vh.tvContent.setText(list.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class VH extends RecyclerView.ViewHolder {
            TextView tvContent;

            public VH(View itemView) {
                super(itemView);
                tvContent = (TextView) itemView.findViewById(R.id.name);
            }
        }
    }
}
