package com.sinieco.recyclerviewexercise.statusandnavigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.sinieco.recyclerviewexercise.BaseTrasluctionActivity;
import com.sinieco.recyclerviewexercise.R;

/**
 * Author:BaiMeng
 * Time:2018/2/13
 * Description:
 */

public class TranslucentActivity extends BaseTrasluctionActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparentstatus);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        View bottomNavigationView = findViewById(R.id.bottom_view);
        setTranslucent(toolbar,bottomNavigationView,getResources().getColor(R.color
                .colorPrimaryDark));

    }
}
