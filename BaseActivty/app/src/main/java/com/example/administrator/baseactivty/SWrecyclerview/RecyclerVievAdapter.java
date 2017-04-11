package com.example.administrator.baseactivty.SWrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.baseactivty.R;

import java.util.List;

/**
 * Created by tailin on 2017/3/2.
 */
public class RecyclerVievAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ShopBean> mData;

    public RecyclerVievAdapter(Context context, List<ShopBean> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getItemCount() {
        return mData.size() == 0 ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_listview_shopdetail, parent, false);
            return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentViewHolder) {//内容
            if (mData != null) {
                ContentViewHolder holder1 = (ContentViewHolder) holder;
                ShopBean shopBean = mData.get(position);
                holder1.ivShop.setImageResource(shopBean.getImg());
                holder1.tvTitle.setText(shopBean.getTitle());
                holder1.tvTotal.setText(String.valueOf(shopBean.getTotal()));
                holder1.tvShengyu.setText(String.valueOf(shopBean.getShenyu()));
                holder1.progressBar.setProgress(shopBean.getPrecent());
            }
        }
    }

    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        ImageView ivShop;
        TextView tvTitle;
        ProgressBar progressBar;
        TextView tvTotal;
        TextView tvShengyu;
        ImageView ivJoin;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ivShop = (ImageView) itemView.findViewById(R.id.iv_shop);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_shop_content);
            progressBar = (ProgressBar) itemView.findViewById(R.id.pb_progressbar);
            tvTotal = (TextView) itemView.findViewById(R.id.tv_shopnum_total);
            tvShengyu = (TextView) itemView.findViewById(R.id.tv_shopnum_shengyu);
            ivJoin = (ImageView) itemView.findViewById(R.id.iv_canyu);
        }
    }

}
