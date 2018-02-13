package com.sinieco.recyclerviewexercise;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinieco.recyclerviewexercise.touchhelper.OnItemTouchListener;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Author:BaiMeng
 * Time:2018/2/5
 * Description:
 */

public class MyAdapter<T> extends Adapter<MyAdapter<T>.MyViewHolder> implements OnItemTouchListener {

    private List<T> mData;
    private int mLayoutId;
    public MyAdapter(List<T> data, int layoutId){
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    @Override
    public MyAdapter<T>.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId,parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyAdapter<T>.MyViewHolder holder, int position) {
        holder.tv.setText((String)mData.get(position));
    }

    @Override
    public int getItemCount() {
        if(mData == null){
            return 0;
        }
        return mData.size();
    }

    @Override
    public void onMove(int formPosition, int toPosition) {
        Collections.swap(mData,formPosition,toPosition);
        notifyItemMoved(formPosition,toPosition);
    }

    @Override
    public void onRemove(int target, int deration) {
        mData.remove(target);
        notifyItemRemoved(target);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv_content);
        }
    }
}
