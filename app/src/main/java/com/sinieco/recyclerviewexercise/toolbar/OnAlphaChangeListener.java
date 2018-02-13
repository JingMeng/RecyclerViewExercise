package com.sinieco.recyclerviewexercise.toolbar;

/**
 * Author:BaiMeng
 * Time:2018/2/11
 * Description: 滑动ScrollView的时候根据距离算出透明度的监听
 */

public interface OnAlphaChangeListener {
    // 当滑动距离改变是回调该方法
    void onAlphaChange(float alpha);
}
