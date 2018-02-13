package com.sinieco.recyclerviewexercise.wrapper;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:BaiMeng
 * Time:2018/2/5
 * Description: RecyclerView包装类用于添加头布局，脚布局，
 *  在Xml文件中替代RecyclerView使用
 */

public class WrapperRecyclerView extends RecyclerView {
    private HeaderViewAdapter mHeaderAdapter;
    private Adapter mAdpater ;

    /**
     * Adapter数据观察者一旦Adapter的数据发生该边回调下面的方法
     */
    private AdapterDataObserver mObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            if (mAdpater == null) {
                return;
            }
            if (mHeaderAdapter != mAdpater) {
                mHeaderAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            if (mAdpater == null) {
                return;
            }
            if (mHeaderAdapter != mAdpater) {
                mHeaderAdapter.notifyItemRangeChanged(positionStart,itemCount);
            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            if (mAdpater == null) {
                return;
            }
            if (mHeaderAdapter != mAdpater) {
                mHeaderAdapter.notifyItemRangeChanged(positionStart,itemCount,payload);
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            if (mAdpater == null) {
                return;
            }
            if (mHeaderAdapter != mAdpater) {
                mHeaderAdapter.notifyItemRangeInserted(positionStart,itemCount);
            }
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            if (mAdpater == null) {
                return;
            }
            if (mHeaderAdapter != mAdpater) {
                mHeaderAdapter.notifyItemRangeRemoved(positionStart,itemCount);
            }
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            if (mAdpater == null) {
                return;
            }
            if (mHeaderAdapter != mAdpater) {
                mHeaderAdapter.notifyItemMoved(fromPosition,toPosition);
            }
        }
    };


    public WrapperRecyclerView(Context context) {
        this(context, null);
    }

    public WrapperRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrapperRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     *  设置Adapter，将传入的Adapter包装成HeaderViewAdapter，
     *   并且注册观察者
     * @param adapter
     */
    @Override
    public void setAdapter(Adapter adapter) {
        if (mAdpater != null){
            mAdpater.unregisterAdapterDataObserver(mObserver);
            mAdpater = null;
        }
        this.mAdpater = adapter;
        if (!(adapter instanceof HeaderViewAdapter)) {
            mHeaderAdapter = new HeaderViewAdapter(adapter);
        } else {
            mHeaderAdapter = (HeaderViewAdapter) adapter;
        }
        super.setAdapter(mHeaderAdapter);
        mAdpater.registerAdapterDataObserver(mObserver);
        mHeaderAdapter.setItemInSingleLine(this);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (mHeaderAdapter != null) {
            mHeaderAdapter.setItemInSingleLine(this);
        }
        super.setLayoutManager(layout);
    }

    /**
     * 添加头布局，必须放到setAdapter方法之后调用，否则会报错（仿ListView）
     *  添加的布局不能是直接new的空间否则会报错，需要通过xml生成的控件
     * @param headerView
     */
    public void addHeaderView(View headerView) {
        if (mHeaderAdapter != null) {
            mHeaderAdapter.addHeaderView(headerView);
        } else {
            throw new NullPointerException("Adapter为null,请先设置Adapter");
        }
    }

    public void addFooterView(View footerView) {
        if (mHeaderAdapter != null) {
            mHeaderAdapter.addFooterView(footerView);
        } else {
            throw new NullPointerException("Adapter为null,请先设置Adapter");
        }
    }

    public void removeHeaderView(View headerView) {
        if (mHeaderAdapter != null){
            mHeaderAdapter.removeHeaderView(headerView);
        } else {
            throw new NullPointerException("Adapter为null,请先设置Adapter");
        }
    }

    public void removeFooterView(View footerView) {
        if (mHeaderAdapter != null) {
           mHeaderAdapter.removeFooterView(footerView);
        } else {
          throw new NullPointerException("Adapter为null,请先设置Adapter");
        }
    }
}
