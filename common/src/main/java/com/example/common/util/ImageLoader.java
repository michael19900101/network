package com.example.common.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.common.app.BaseApp;

/**
 * Created by aotuman on 2019/1/8
 * describe:
 */
public class ImageLoader {
    //获取Url地址
    public static void getUrl(String url, ImageView imageView) {
        Glide.with(BaseApp.getContext()).load(url).into(imageView);
    }
}
