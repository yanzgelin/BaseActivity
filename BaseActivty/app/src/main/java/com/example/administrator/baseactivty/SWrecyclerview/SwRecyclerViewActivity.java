package com.example.administrator.baseactivty.SWrecyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.administrator.baseactivty.R;
import com.example.administrator.baseactivty.SWrecyclerview.widgets.DividerItemDecoration;
import com.example.administrator.baseactivty.SWrecyclerview.widgets.GestureSwipeRefreshLayout;
import com.example.administrator.baseactivty.SWrecyclerview.widgets.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SwRecyclerViewActivity extends AppCompatActivity {
    RefreshRecyclerView recyclerView;
    RecyclerVievAdapter recyAdapter;
    GestureSwipeRefreshLayout swipeRefreshLayout;
    List<ShopBean> shopList;
    int pageNum = 1;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.recyclerviewactivity);
        initData();
        initView();
    }

    private void initData() {
        shopList = new ArrayList<>();
        DataBuilder.builderShopList();
        List<ShopBean> shopDataForPage = DataBuilder.getShopDataForPage(pageNum);
        //构建列表数据
        if (shopDataForPage != null) {
            shopList.addAll(shopDataForPage);
        }
    }

    private void initView() {
        initRecyclerView();
        initSwipeRefresh();

    }

    private void initRecyclerView() {
        recyclerView = (RefreshRecyclerView) findViewById(R.id.rlv_view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyAdapter = new RecyclerVievAdapter(this, shopList);
        recyclerView.setLoadMoreEnable(true);//允许加载更多
        recyclerView.setFooterResource(R.layout.view_footer);//设置脚布局
        recyclerView.setAdapter(recyAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    private void initSwipeRefresh() {
        swipeRefreshLayout = (GestureSwipeRefreshLayout) findViewById(R.id.srl_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(new GestureSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 更新完后调用该方法结束刷新
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        recyclerView.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ShopBean shopBean = null;
                        if(pageNum >= 5){
                            recyclerView.setIsEnd(RefreshRecyclerView.NNLOAD_FINSH);

                            recyclerView.notifyData();
                            return;
                        }
                        for(int i = 0;i < 5;i++){
                            shopBean = new ShopBean();
                            shopBean.setImg(R.mipmap.ic_launcher);
                            shopBean.setPrecent(i);
                            shopBean.setShenyu(i);
                            shopBean.setTotal(i);
                            shopBean.setTitle("商品" + i);
                            shopList.add(shopBean);
                        }
                        pageNum++;
                        recyclerView.notifyData();
                    }
                }, 2000);
            }
        });
    }
}
