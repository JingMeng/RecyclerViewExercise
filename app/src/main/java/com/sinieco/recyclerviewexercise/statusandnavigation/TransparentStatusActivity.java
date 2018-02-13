package com.sinieco.recyclerviewexercise.statusandnavigation;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.sinieco.recyclerviewexercise.R;

/**
 * Author:BaiMeng
 * Time:2018/2/12
 * Description:
 */

public class TransparentStatusActivity extends AppCompatActivity{
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 5.0以上才有此方法
//        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        // 4.4设置状态栏透明，效果和在主题中设置<item name="android:windowTranslucentStatus">true</item> 一样
        // 不过，这里可以给每个Activity设置不同的状态栏颜色，主题一旦设置App中所有的状态栏的颜色都一样
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
//        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        setContentView(R.layout.activity_transparentstatus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("网易云音乐");
        toolbar.setBackgroundResource(R.color.colorPrimaryDark);

        ViewGroup.LayoutParams toolbarLayoutParams = toolbar.getLayoutParams();
        int statusBarHeight = getStatusBarHeight();
        toolbarLayoutParams.height += statusBarHeight;
        toolbar.setPadding(toolbar.getPaddingLeft(),toolbar.getPaddingTop()+statusBarHeight,
                toolbar.getPaddingRight(),toolbar.getPaddingBottom());
        toolbar.setLayoutParams(toolbarLayoutParams);

        View view = findViewById(R.id.bottom_view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = getNavigationBarHeight();
        view.setLayoutParams(layoutParams);
    }

    private int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int height = getResources().getDimensionPixelSize(identifier);
        return height >= 0 ? height : 0;
    }

    private int getNavigationBarHeight(){
        int identifier = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        int height = getResources().getDimensionPixelSize(identifier);
        return height >=0 ? height : 0 ;
    }
}
