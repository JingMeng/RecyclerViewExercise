package com.sinieco.recyclerviewexercise.wrapper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

/**
 * Author:BaiMeng
 * Time:2018/2/5
 * Description: Adapter包装类用个添加头布局和脚布局
 */

public class HeaderViewAdapter extends RecyclerView.Adapter {
    // 头布局的类型，每个头布局都是一种类型
    private static int HEADER_TYPE = 0x1000000;
    // 脚布局的类型，每个脚布局都是一种类型
    private static int FOOTER_TYPE = 0x2000000;
    private SparseArray<View> mHeaderView;
    private SparseArray<View> mFooterView;
    private RecyclerView.Adapter mAdapter;
    public HeaderViewAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        this.mHeaderView = new SparseArray<>();
        this.mFooterView = new SparseArray<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderPosition(position)) {
            return mHeaderView.keyAt(position);
        }
        if (isFooterPosition(position)){
            return mFooterView.keyAt(position - mHeaderView.size() - mAdapter.getItemCount());
        }
        return mAdapter.getItemViewType(position - mHeaderView.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isFooterType(viewType)) {
           return new HeaderViewHolder(mFooterView.get(viewType));
        }
        if (isHeaderType(viewType)){
            return new HeaderViewHolder(mHeaderView.get(viewType));
        }
        return mAdapter.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderPosition(position) || isFooterPosition(position)) {//是头部
            return ;
        }
        mAdapter.onBindViewHolder(holder,position - mHeaderView.size());
    }

    @Override
    public int getItemCount() {
        if(mAdapter == null){
            return mHeaderView.size() + mFooterView.size();
        }
        return mAdapter.getItemCount() + mHeaderView.size() + mFooterView.size();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void addHeaderView(View headerView) {
        if (mHeaderView.indexOfValue(headerView) < 0) {
            mHeaderView.put(HEADER_TYPE++,headerView);
        }
    }

    public void addFooterView(View footerView) {
        if (mFooterView.indexOfValue(footerView) < 0){
            mFooterView.put(FOOTER_TYPE++,footerView);
        }
    }

    public void removeHeaderView(View headerView) {
        if (mHeaderView.indexOfValue(headerView) >= 0) {
            mHeaderView.removeAt(mHeaderView.indexOfValue(headerView));
        }
    }

    public void removeFooterView(View footerView) {
        if (mFooterView.indexOfValue(footerView) >= 0) {
            mFooterView.removeAt(mFooterView.indexOfValue(footerView));
        }
    }

    /**
     *  如果是网格布局，头布局和脚布局设置单独占用一行
     * @param recyclerView
     */
    public void setItemInSingleLine(RecyclerView recyclerView){
        Log.e("setSingleLine","方法执行。");
        if (recyclerView.getLayoutManager() instanceof  GridLayoutManager) {
            final GridLayoutManager layoutManager = (GridLayoutManager)recyclerView.getLayoutManager();
            Log.e("isGridLayoutManager","方法执行。");
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    boolean isHeaderOrFooter = isFooterPosition(position) || isHeaderPosition(position);
                    return isHeaderOrFooter ? layoutManager.getSpanCount() : 1;
                }
            });
        }
    }

    private boolean isHeaderType(int type) {
        if (mHeaderView.indexOfKey(type) >= 0){
            return true;
        }
        return false;
    }

    private boolean isFooterType(int type) {
        if (mFooterView.indexOfKey(type) >= 0) {
            return true;
        }
        return false;
    }

    private boolean isHeaderPosition(int postion) {
        if(postion < mHeaderView.size()){
            return true;
        }
        return false;
    }

    private boolean isFooterPosition(int position) {
        if(position >= mHeaderView.size() + mAdapter.getItemCount()){
            return true;
        }
        return false;
    }
}
