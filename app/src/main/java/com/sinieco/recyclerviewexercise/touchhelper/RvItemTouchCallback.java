package com.sinieco.recyclerviewexercise.touchhelper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.sinieco.recyclerviewexercise.R;

/**
 * Author:BaiMeng
 * Time:2018/2/6
 * Description: RecyclerView条目触摸辅助类，用来处理左右滑动和上下拖拽
 */

public class RvItemTouchCallback extends ItemTouchHelper.Callback {

    private OnItemTouchListener mListener;

    public RvItemTouchCallback(OnItemTouchListener listener) {
        this.mListener = listener;
    }

    /**
     * 拖拽长按支持
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    /**
     *  滑动长按支持
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * 返回的flags代表了支持那些动作，如左划右划等
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        // makeMovementFlags 方法的参数是有顺序的，第一个是拖拽标志，第二个是滑动标志
        int movementFlags = makeMovementFlags(dragFlags,swipeFlags);
        return movementFlags;
    }

    /**
     * 当Item上下滑动的时候回调此方法
     * @param recyclerView
     * @param viewHolder 滑向哪个Item
     * @param target   滑动的Item
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // 如果两个Item的类型不一样不交换位置
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        int fromPosition = target.getAdapterPosition();
        int toPostiont = viewHolder.getAdapterPosition();
        if (mListener != null) {
            mListener.onMove(fromPosition,toPostiont);
        }
        return true;
    }

    /**
     *
     * @param viewHolder
     * @param direction  滑动方向
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (mListener != null) {
           mListener.onRemove(viewHolder.getAdapterPosition(),direction);
        }
    }

    /**
     * 绘制Item时回调,Item进行拖拽或滑动时会不断回调此方法
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX      水平增量往左为负，往右为正，单位为像素
     * @param dY      垂直增量往下为负，往上为正，单位为像素
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        float scale = 1 - (Math.abs(dX) / viewHolder.itemView.getMeasuredWidth());
        viewHolder.itemView.setScaleX(scale);
        viewHolder.itemView.setScaleY(scale);
        if(scale <= 0){
            viewHolder.itemView.setScaleX(1);
            viewHolder.itemView.setScaleY(1);
        }
    }

    /**
     * 条目选中改变时回调
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG || actionState == ItemTouchHelper
                .ACTION_STATE_SWIPE) {
            viewHolder.itemView.setBackgroundResource(R.color.colorAccent);
        }
    }

    /**
     * 触摸状态完成后调用，用于清空Item的状态
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
    }
}
