package com.example.administrator.baseactivty.SWrecyclerview;


import com.example.administrator.baseactivty.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tailin on 2017/3/6.
 * 数据生产
 */
public class DataBuilder {

    private static List<ShopBean> list;
    private static List<String> listStr;
    private static int dataTotal = 100; //数据总数
    private static int pageTotal = dataTotal / 10; //页数
    private static int startIndex;
    private static int endIndex;

    private static List<ShopBean> getInstance() {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    private static List<String> getStrInstance() {
        if (listStr == null) {
            return new ArrayList<>();
        }
        return listStr;
    }

    //创建String数据
    public static void builderStrList() {
        DataBuilder.listStr = getStrInstance();
        if (DataBuilder.listStr != null) {
            for (int i = 0; i < dataTotal; i++) {
                DataBuilder.listStr.add(("商品" + i));
            }
        }
    }

    //分页取出数据 pageNum从1开始
    public static List<String> getStrDataForPage(int pageNum) {
        if (pageNum > pageTotal)
            return null;
        List<String> listPage = new ArrayList<>();
        startIndex = 0;
        endIndex = pageNum * 100;
        if (DataBuilder.listStr != null && DataBuilder.listStr.size() > 0) {
            for (int i = startIndex; i < endIndex; i++) {
                listPage.add(DataBuilder.listStr.get(i));
            }
        }
        return listPage;
    }

    //创建数据
    public static void builderShopList() {
        DataBuilder.list = getInstance();
        ShopBean shopBean;
        if (DataBuilder.list != null) {
            for (int i = 0; i < dataTotal; i++) {
                shopBean = new ShopBean();
                shopBean.setImg(R.mipmap.ic_launcher);
                shopBean.setPrecent(50);
                shopBean.setShenyu(100);
                shopBean.setTotal(3000);
                shopBean.setTitle("商品" + i);
                DataBuilder.list.add(shopBean);
            }
        }
    }

    //分页取出数据 pageNum从1开始
    public static List<ShopBean> getShopDataForPage(int pageNum) {
        if (pageNum > pageTotal - 1)
            return null;
        List<ShopBean> listPage = new ArrayList<>();
        startIndex = (pageNum-1)*10;
        endIndex = pageNum * 10;
        if (DataBuilder.list != null && DataBuilder.list.size() > 0) {
            for (int i = startIndex; i < endIndex; i++) {
                listPage.add(DataBuilder.list.get(i));
            }
        }
        return listPage;
    }

}
