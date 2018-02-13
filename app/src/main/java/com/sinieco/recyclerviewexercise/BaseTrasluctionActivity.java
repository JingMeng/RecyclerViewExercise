package com.sinieco.recyclerviewexercise;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Config;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;


/**
 * Author:BaiMeng
 * Time:2018/2/13
 * Description: 沉浸式状态栏和导航栏的基类Activity
 */

public class BaseTrasluctionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果是4.4及以上或者5.0一下设置透明状态栏和导航栏
        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 设置沉浸式状态栏的方法
     * @param toolbar
     * @param bottomNavigationView
     * @param color
     */
    protected void setTranslucent(Toolbar toolbar, View bottomNavigationView, int color ) {
        setTranslucentStatusBar(toolbar, color);
        setTranslucentNavigationBar(toolbar, bottomNavigationView,color);
    }

    private void setTranslucentNavigationBar(Toolbar toolbar , View bottomNavigationView, int
            color) {
        // 4.4设置透明导航栏会导致布局重叠，可以通过添加一个View占位来解决
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            boolean isShown =  hasNavigationBarShown();
            if (isShown) {
//                ViewGroup  decorView = (ViewGroup) getWindow().getDecorView();
//                ViewGroup content = decorView.findViewById(android.R.id.content);
//                View myView = content.getChildAt(0);
//                if(!(myView instanceof LinearLayout)){
//                    content.removeView(myView);
//                    LinearLayout linearLayout = new LinearLayout(this);
//                    linearLayout.setOrientation(LinearLayout.VERTICAL);
//                    content.addView(linearLayout, ViewGroup.LayoutParams.MATCH_PARENT,
//                            ViewGroup.LayoutParams.MATCH_PARENT);
//                    LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
//                    contentParams.weight = 1;
//                    linearLayout.addView(myView,contentParams);
//                    if (bottomNavigationView == null){
//                        bottomNavigationView = new View(this);
//                        LinearLayout.LayoutParams navigatinParams = new LinearLayout.LayoutParams
//                                (LinearLayout.LayoutParams.MATCH_PARENT, 0);
//                        linearLayout.addView(bottomNavigationView,navigatinParams);
//                    }
//                }
//                Log.e("NavigationBar == null?","  ===  "+(bottomNavigationView == null ? true :
//                        false));
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)bottomNavigationView
//                        .getLayoutParams();
//                layoutParams.height = getNavigationBarHeight();
//                layoutParams.weight = LinearLayout.LayoutParams.MATCH_PARENT;
//                bottomNavigationView.setBackgroundResource(R.color.colorPrimary);
//                bottomNavigationView.setLayoutParams(layoutParams);

                if (bottomNavigationView != null) {
                    ViewGroup.LayoutParams layoutParams = bottomNavigationView.getLayoutParams();
                    layoutParams.height = getNavigationBarHeight();
                    bottomNavigationView.setBackgroundColor(color);
                    bottomNavigationView.setLayoutParams(layoutParams);
                }
            }

        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(color);
        }
    }

    private int getNavigationBarHeight() {
        int height = 0;
        int id = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (id >= 0) {
            height = getResources().getDimensionPixelSize(id);
        }
        return height;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private boolean hasNavigationBarShown() {
        WindowManager wm = getWindowManager();
        DisplayMetrics metrics = new DisplayMetrics();
        // 获取整个屏幕的尺寸信息
        wm.getDefaultDisplay().getRealMetrics(metrics);
        int realWidth = metrics.widthPixels;
        int realHeight = metrics.heightPixels;
        metrics = new DisplayMetrics();
        // 获取显示内容的尺寸信息
        wm.getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        return realHeight - height > 0 || realWidth -width >0 ;
    }

    private void setTranslucentStatusBar(Toolbar toolbar, int color) {
        // 4.4即以上5.0一下的设置透明状态栏会造成布局重叠问题
        // 可以给通过给Toolbar设置padding值来解决
        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (toolbar != null){
                int statusHeight = getStatusHeight();
                ViewGroup.LayoutParams params = toolbar.getLayoutParams();
                params.height += statusHeight;
                toolbar.setPadding(toolbar.getPaddingLeft(),toolbar.getPaddingTop() + statusHeight,
                        toolbar.getPaddingRight(), toolbar.getPaddingBottom());
                toolbar.setLayoutParams(params);
            }
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 安卓5.0及以上通过setStatusColor设置
            getWindow().setStatusBarColor(color);
        }
    }

    private int getStatusHeight() {
//        int id = getResources().getIdentifier("status_bar_height", "dimen", "andriod");
//        int height = 0;
//        if (id >= 0) {
//            height = getResources().getDimensionPixelSize(id);
//        }

        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
