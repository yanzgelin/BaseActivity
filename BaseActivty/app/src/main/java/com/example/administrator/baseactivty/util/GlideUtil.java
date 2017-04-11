package com.example.administrator.baseactivty.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.baseactivty.R;

import java.io.File;

/**
 * glide加载图片工具类
 * .dontAnimate() //没有淡入淡出效果
 * .crossFade() //淡入淡出效果默认持续时间300,版本3.6.1默认激活的
 * .fitCenter() //缩放图像让图像都测量出来等于或小于 ImageView 的边界范围。该图像将会完全显示，但可能不会填满整个 ImageView。
 * .CenterCrop() //缩放图像让它填充到 ImageView 界限内并且裁剪额外的部分。ImageView 可能会完全填充，但图像可能不会完整显示。
 */
public class GlideUtil {
    /**
     * 从网络加载图片
     *
     * @param context
     * @param url
     * @param iv
     */
    public static void loadImaNormal(Context context, String url, ImageView iv) {
        Glide.with(context).load(url)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .fitCenter()
                .into(iv);
    }

    /**
     * 从网络加载图片,绕过内存缓存和磁盘缓存
     *
     * @param context
     * @param url
     * @param iv
     */
    public static void loadImgNoCache(Context context, String url, ImageView iv) {

        Glide.with(context).load(url)
                .placeholder(R.drawable.music_black_bg).error(R.mipmap.ic_launcher)
                .dontAnimate()
                .fitCenter()
                .skipMemoryCache(true)  //内存不缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE) //磁盘不缓存
                .into(iv);
    }

    /**
     * 从网络加载图片做缓存
     *
     * @param context
     * @param url
     * @param iv
     */
    public static void loadImagWithCache(Context context, String url, ImageView iv) {
        Glide.with(context).load(url)
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .dontAnimate() //没有淡入淡出效果
                .crossFade() //淡入淡出效果默认持续时间300,版本3.6.1默认激活的
                .fitCenter()
//                .override()
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                    }
                });
    }

    /**
     * 从资源中加载图片
     *
     * @param context
     * @param resId
     * @param iv
     */
    public static void loadImgFromRes(Context context, int resId, ImageView iv) {
        Glide.with(context).load(resId).into(iv);
    }

    /**
     * 从文件中加载图片
     *
     * @param context
     * @param file
     * @param iv
     */
    public static void loadImgFromFile(Context context, File file, ImageView iv) {
        Glide.with(context).load(file).into(iv);
    }

    /**
     * 从Uri中加载图片
     *
     * @param context
     * @param uri
     * @param iv
     */
    public static void loadImgFromUri(Context context, Uri uri, ImageView iv) {
        Glide.with(context).load(uri).into(iv);
    }
}
