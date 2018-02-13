package com.sinieco.recyclerviewexercise.touchhelper;

/**
 * Author:BaiMeng
 * Time:2018/2/6
 * Description: 条目触摸监听，处理包括左右滑动
 * 上下拖拽等事件
 */

public interface OnItemTouchListener {
    /**
     *  交换连个Item的位置
     * @param formPosition
     * @param toPosition
     */
    void onMove(int formPosition, int toPosition);

    /**
     *  移除Item
     * @param target
     * @param deration
     */
    void onRemove(int target, int deration);
}
