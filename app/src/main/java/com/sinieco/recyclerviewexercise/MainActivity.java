package com.sinieco.recyclerviewexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.WrapperListAdapter;


import com.sinieco.recyclerviewexercise.touchhelper.RvItemTouchCallback;
import com.sinieco.recyclerviewexercise.wrapper.WrapperRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecycerView;
    private MyItemDecoration decoration;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycerView = (RecyclerView) findViewById(R.id.recyclerview);

        mRecycerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager
                .VERTICAL,false));
        decoration = new MyItemDecoration(getApplicationContext(), R.drawable
                .shape_divide);
        mRecycerView.addItemDecoration(decoration);
        mAdapter = new MyAdapter<String>(getDataList(),R.layout.list_item);
        mRecycerView.setAdapter(mAdapter);

//        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams
//                .MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
//
//        View headerView1 = LayoutInflater.from(this).inflate(R.layout.layout_headerandfooter,
//                mRecycerView,
//                false);
//        ((TextView)headerView1.findViewById(R.id.header_name)).setText("HeaderView1");
//        mRecycerView.addHeaderView(headerView1);
//
        RvItemTouchCallback callback = new RvItemTouchCallback(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecycerView);

    }

    public void switchLayout(View view) {
        RecyclerView.LayoutManager layoutManager = mRecycerView.getLayoutManager();
        if (!(layoutManager instanceof GridLayoutManager)) {
            mRecycerView.setLayoutManager(new GridLayoutManager(this,3));
        } else {
            mRecycerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager
                    .VERTICAL,false));
        }
    }

    private List<String> getDataList() {
        List<String> data = new ArrayList<>();
        for (int i =0; i < 30; i++) {
            data.add("item "+i);
        }
        return data;
    }
}
