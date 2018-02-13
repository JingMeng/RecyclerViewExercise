package com.sinieco.recyclerviewexercise.cardviewandfloatingactionbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.sinieco.recyclerviewexercise.R;

/**
 * Author:BaiMeng
 * Time:2018/2/13
 * Description:
 */

public class FloatingActionButtonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floatingactionbutton);
        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.action_button);

    }
}
