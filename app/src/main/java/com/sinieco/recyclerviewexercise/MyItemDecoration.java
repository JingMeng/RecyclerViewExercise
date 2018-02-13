package com.sinieco.recyclerviewexercise;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Author:BaiMeng
 * Time:2018/2/5
 * Description: 条目装饰，用于绘制条目的分割线等等
 */

class MyItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivide;
    public MyItemDecoration(Context context, int drawableId){
        mDivide = context.getResources().getDrawable(drawableId);
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
      //  super.onDraw(c, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            drawHorizotal(c,parent,layoutManager);
            drawVertical(c,parent,layoutManager);
        } else if (layoutManager instanceof LinearLayoutManager) {
            if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager
                    .HORIZONTAL){
                drawVertical(c,parent,layoutManager);
            }else if (((LinearLayoutManager) layoutManager).getOrientation() ==
                    LinearLayoutManager.VERTICAL){
                drawHorizotal(c,parent, layoutManager);
            }
        }


    }

    /**
     *  条目的偏移，一般来说在那里绘制像那里偏移
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
      //  super.getItemOffsets(outRect, view, parent, state);
        // 绘制LinearLayout垂直方向分隔线
        int right = 0;
        int bottom = 0;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
           right =  mDivide.getIntrinsicWidth();
           bottom =  mDivide.getIntrinsicHeight();
           outRect.set(0,0,right,bottom);
           return;
        } else if (layoutManager instanceof LinearLayoutManager) {
            if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager
                    .HORIZONTAL){
                bottom =  mDivide.getIntrinsicHeight();
            }else if (((LinearLayoutManager) layoutManager).getOrientation() ==
                    LinearLayoutManager.VERTICAL){
                right =  mDivide.getIntrinsicWidth();
            }
        }
        outRect.set(0,0,right,bottom);
    }

    //画横线
    private void drawHorizotal(Canvas c, RecyclerView parent, RecyclerView.LayoutManager layoutManager) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < parent.getChildCount() - 1; i++) {
            if (layoutManager instanceof GridLayoutManager) {
                if(isLastRow(i,parent)){
                    return;
                }
            }
            int top = parent.getChildAt(i).getBottom();
            mDivide.setBounds(left,top, right,top + mDivide.getIntrinsicHeight());
            mDivide.draw(c);
        }
    }

    //画竖线
    private void drawVertical(Canvas c, RecyclerView parent, RecyclerView.LayoutManager layoutManager) {
        for (int i = 0; i < parent.getChildCount() - 1; i++) {
            if (layoutManager instanceof GridLayoutManager) {
                if(isLastClum(i,parent)){
                    continue;
                }
            }
            int top = parent.getChildAt(i).getTop();
            int bottom = parent.getChildAt(i).getBottom();
            int left = parent.getChildAt(i).getRight();
            mDivide.setBounds(left,top, left+mDivide.getIntrinsicWidth(),bottom);
            mDivide.draw(c);
        }
    }

    /**
     * 判断是否是最后一列
     * @param i
     * @param parent
     * @return
     */
    private boolean isLastClum(int i, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int span = ((GridLayoutManager)layoutManager).getSpanCount();
            return (i % span == span - 1) ? true : false;
        }
        return false;
    }

    /**
     * 判断是否是最后一行
     * @param i
     * @param parent
     * @return
     */
    private boolean isLastRow(int i, RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int span = ((GridLayoutManager)layoutManager).getSpanCount();
            int counts = parent.getChildCount();
            int rows = (counts % span == 0) ? counts / 3 : counts / 3 + 1;
            return i >= (rows - 1) * span ? true : false;
        }
        return false;
    }
}
