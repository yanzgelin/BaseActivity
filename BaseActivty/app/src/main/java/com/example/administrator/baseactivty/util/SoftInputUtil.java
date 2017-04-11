package com.example.administrator.baseactivty.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘操作类
 */
public  class SoftInputUtil {

    /**
     * 切换，显示则隐藏，反之。
     *
     * @param context
     * @param view
     */
    public static void toggleSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0, 0);
        }
    }

    /**
     * 显示软键盘
     *
     * @param context
     * @param view    view必须是VISIBLE的EditText，如果不是VISIBLE的，需要先将其设置为VISIBLE。
     */
    public static void showSoftInout(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }

    }

    /**
     * 隐藏软键盘
     *
     * @param context
     * @param view
     */
    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 获取软键盘的状态
     *
     * @param context
     * @return true 打开
     */
    public static boolean isShowSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean status = false;//获取状态信息
        if (imm != null) {
            status = imm.isActive();
        }
        return status;
    }
}
