package com.sinieco.recyclerviewexercise.TabLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinieco.recyclerviewexercise.R;

import java.util.Random;

/**
 * Author:BaiMeng
 * Time:2018/2/11
 * Description:
 */


public class TabItemFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String title = (String) bundle.get("Title");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tab,null);
        ((TextView)view.findViewById(R.id.title_in_fragment)).setText(title);
        int color = Color.argb((int)(Math.random()*200)+55,(int)(Math.random()*200)+55,
                (int)(Math.random() *200)+55, (int)(Math.random()*200)+55);
        view.setBackgroundColor(color);
        return view;
    }
}
