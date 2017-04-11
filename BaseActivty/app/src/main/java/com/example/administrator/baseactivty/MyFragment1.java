package com.example.administrator.baseactivty;

import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.baseactivty.Base.BaseFragment;
import com.example.administrator.baseactivty.flowlayout.FlowLayout;
import com.example.administrator.baseactivty.flowlayout.TagAdapter;
import com.example.administrator.baseactivty.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/31.
 */

public class MyFragment1 extends BaseFragment {
    private TagFlowLayout tagFlowLayout;
    private TagAdapter<String> tagAdapter;
    private FloatingActionButton fab;

    @Override
    public int getLayoutID() {
        return R.layout.fragmentxml1;
    }

    @Override
    public void intviews() {
        tagFlowLayout = findView(R.id.view_flowlayout);
        fab = findView(R.id.fab);
    }

    @Override
    public void intDate() {
        List<String> tagList = new ArrayList<>();
        for (int i = 0; i < 44; i++) {
            tagList.add("流式布局" + i);
        }
        //适配器
        final LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        //每个流式view的样式
        //给每个流式布局子view设置内容
        //设置内容
        tagAdapter = new TagAdapter<String>(tagList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {//每个流式view的样式
                //给每个流式布局子view设置内容
                TextView textView = (TextView) layoutInflater.inflate(R.layout.item_text_flowlayout, parent, false);
                textView.setText(s);//设置内容
                return textView;
            }
        };
        //设置适配器
        tagFlowLayout.setAdapter(tagAdapter);
    }

    @Override
    public void intListener() {
        fab.setOnClickListener(this);
    }

    @Override
    public void processClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                Log.i("获取选中标签集的索引",""+tagFlowLayout.getSelectedList());
                Set<Integer> set = tagFlowLayout.getSelectedList();
                for(Integer index:set){
                    Log.i("获取选中的数据",""+tagAdapter.getItem(index));
                }
                break;
        }
    }
}
