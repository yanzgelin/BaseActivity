package com.example.administrator.baseactivty.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/4/1.
 */

public class StartActivityUtil {
    public static void startActivity(Context context,Class clazz){
        Intent intent = new Intent(context,clazz);
        context.startActivity(intent);
    }
}
