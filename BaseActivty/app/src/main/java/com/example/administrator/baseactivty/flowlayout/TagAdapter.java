package com.example.administrator.baseactivty.flowlayout;

import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class TagAdapter<T> {
    private List<T> mTagDatas;
    private OnDataChangedListener mOnDataChangedListener;
    private HashSet<Integer> mCheckedPosList = new HashSet<>();

    /**
     * 构造集合数据
     */
    public TagAdapter(List<T> datas) {
        mTagDatas = datas;
    }

    /**
     * 构造数组数据转为集合
     */
    public TagAdapter(T[] datas) {
        mTagDatas = new ArrayList<T>(Arrays.asList(datas));
    }


    interface OnDataChangedListener {
        void onChanged();
    }

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        mOnDataChangedListener = listener;
    }

    /**
     * 设置选中标签
     *
     * @param pos
     */
    public void setSelectedList(int... pos) {
        for (int i = 0; i < pos.length; i++)
            mCheckedPosList.add(pos[i]);
        notifyDataChanged();
    }

    /**
     * 设置选中标签
     *
     * @param set set集合
     */
    public void setSelectedList(Set<Integer> set) {
        mCheckedPosList.clear();
        mCheckedPosList.addAll(set);
        notifyDataChanged();
    }

    /**
     * 获取被选中的标签集合
     *
     * @return set集合
     */
    public HashSet<Integer> getPreCheckedList() {
        return mCheckedPosList;
    }

    /**
     * 获取流式布局中标签数量
     *
     * @return
     */
    public int getCount() {
        return mTagDatas == null ? 0 : mTagDatas.size();
    }

    /**
     * 选中标签时回调
     */
    public void notifyDataChanged() {
        mOnDataChangedListener.onChanged();
    }

    /**
     * 获取指定位置的标签bean
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return mTagDatas.get(position);
    }

    public abstract View getView(FlowLayout parent, int position, T t);

    public boolean setSelected(int position, T t) {
        return false;
    }


}