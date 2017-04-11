package com.example.administrator.baseactivty.SWrecyclerview.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.baseactivty.R;


/**
 * Created by Administrator on 2017/3/30.
 */

public class RefreshRecyclerView extends RecyclerView {
    private AutoLoadAdapter autoLoadAdapter;
    private OnLoadMoreListener loadMoreListener;//加载数据监听
    private boolean isLoadingMore = false;//是否加载更多
    private boolean loadMoreEnable;//是否允许加载跟多
    private int footResoure = -1;//脚布局
    private boolean footer_visible = false;//脚布局是否可见
    public static String YNLOAD_FINSH = "";
    public static String NNLOAD_FINSH = "NNLOAD_FINSH";
    private float distanceY = 0;
    float startY = 0;

    public void setIsEnd(String isEnd) {
        this.YNLOAD_FINSH = isEnd;
    }

    public RefreshRecyclerView(Context context) {
        this(context,null);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(getAdapter() != null && getLayoutManager() != null){
                    int lastVisiblePosition = ((LinearLayoutManager)getLayoutManager()).findLastVisibleItemPosition();
                    int itemCount = getAdapter().getItemCount();
                    if(distanceY < 0 && itemCount != 0 && lastVisiblePosition + 4>=itemCount-1&& !isLoadingMore && loadMoreEnable){
                        loading();
                        if(footResoure != -1){//有脚布局
                            footer_visible = true;
                            getAdapter().notifyItemChanged(itemCount - 1);
                        }
                        if(loadMoreListener != null){
                            loadMoreListener.loadMoreListener();
                        }
                    }
                }
            }
        });
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float y = ev.getRawY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                distanceY = y - startY;
                startY = y;
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        autoLoadAdapter = new AutoLoadAdapter(adapter);//添加动画
        super.setAdapter(autoLoadAdapter);
    }

    /**
     * 设置是否允许加载更多
     *
     * @param isEnable
     */
    public void setLoadMoreEnable(boolean isEnable) {
        this.loadMoreEnable = isEnable;
    }

    /**
     * 设置脚布局
     */
    public void setFooterResource(int footerResource) {
        this.footResoure = footerResource;
    }

    /**
     * 加载更多数据回调
     *
     * @param listener
     */
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.loadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        void loadMoreListener();//上拉刷新
    }


    /**
     * 刷新数据
     */
    public void notifyData() {
        if (getAdapter() != null) {
            loadMoreComplete();
            if(footResoure != -1 && loadMoreEnable && !"NNLOAD_FINSH".equals(YNLOAD_FINSH)){
                //隐藏脚布局
                footer_visible = false;
            }
            getAdapter().notifyDataSetChanged();//刷新重新绘制界面
        }
    }

    /**
     * 加载完成
     */
    private void loadMoreComplete() {
        this.isLoadingMore = false;
    }

    /**
     * 正在刷新
     */
    private void loading(){
        this.isLoadingMore = true;//设置正在刷新
    }

    public class AutoLoadAdapter extends Adapter<ViewHolder> {
        private Adapter dataAdapter;//数据adapter
        private final int TYPE_FOOTER = Integer.MAX_VALUE;//底部布局

        public AutoLoadAdapter(Adapter adapter) {
            this.dataAdapter = adapter;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1 && loadMoreEnable && footResoure != -1 && footer_visible ) {
                return TYPE_FOOTER;
            }

            if (dataAdapter.getItemViewType(position) == TYPE_FOOTER) {
                throw new RuntimeException("adapter中itemType不能为:" + Integer.MAX_VALUE);
            }
            return dataAdapter.getItemViewType(position);

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = null;
            if (viewType == TYPE_FOOTER) {//脚部
                View view = LayoutInflater.from(getContext()).inflate(footResoure, parent, false);
                holder = new FooterViewHolder(view);
            } else {//数据
                holder = dataAdapter.onCreateViewHolder(parent, viewType);
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int itemViewType = getItemViewType(position);
            if (itemViewType != TYPE_FOOTER) {
                dataAdapter.onBindViewHolder(holder, position);
            }
            if(holder instanceof FooterViewHolder){
                FooterViewHolder viewHolder = (FooterViewHolder) holder;
                viewHolder.viewVisable();
            }
        }

        @Override
        public int getItemCount() {
            if (dataAdapter.getItemCount() != 0) {
                int count = dataAdapter.getItemCount();
                if (loadMoreEnable && footResoure != -1 && footer_visible) {
                    count++;
                }
                return count;
            }
            return 0;
        }

        public class FooterViewHolder extends ViewHolder {
            LinearLayout linearLayout;
            TextView textView;
            public FooterViewHolder(View itemView) {
                super(itemView);
                linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_loading);
                textView = (TextView) itemView.findViewById(R.id.tv_load_end);

            }
            public void viewVisable(){
                if("NNLOAD_FINSH".equals(YNLOAD_FINSH)){
                    Toast.makeText(getContext(),"没有了，换",Toast.LENGTH_SHORT).show();
                    linearLayout.setVisibility(GONE);
                    textView.setVisibility(VISIBLE);
                }else {
                    linearLayout.setVisibility(VISIBLE);
                    textView.setVisibility(GONE);
                }
            }
        }

    }
}
