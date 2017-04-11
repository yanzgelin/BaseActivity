package com.example.administrator.baseactivty.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * String字符串的各种处理
 */
public class StringUtil {
    /**
     * int数组转换为集合
     *
     * @param arr 要转换的数组
     * @return 转换后的集合
     */
    public static List<Integer> integerArrCovertList(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /**
     * String数组转换为集合
     *
     * @param arr 要转换的数组
     * @return 转换后的集合
     */
    public static List<String> stringArrCovertList(String[] arr) {
        if (arr.length == 0) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /**
     * 字符串以符号分割为数组
     *
     * @param str       要分割的字符串
     * @param separator 分隔符
     * @return 字符数组
     */
    public static String[] stringSliptArr(String str, String separator) {
        String[] arr = new String[]{};
        if (TextUtils.isEmpty(str))
            return arr;
        arr = str.split(separator);
        return arr;
    }

    /**
     * 在单词之间添加符号
     */
    public static String stringAddChar(List<String> list) {
        String s = null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + ",");
        }
        s = sb.substring(0, sb.length() - 1);
        return s;
    }
}
