package com.sinieco.recyclerviewexercise.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.sinieco.recyclerviewexercise.R;


/**
 * Author:BaiMeng
 * Time:2018/2/11
 * Description:
 */

public class ToolbarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_activity);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setTitle("网易云音乐");
        toolbar.setSubtitle("怀旧经典");
        ChangeAlphaScrollView scrollView = (ChangeAlphaScrollView) findViewById(R.id.scrollview);
        scrollView.setOnAlphaChangeListener(new OnAlphaChangeListener() {
            @Override
            public void onAlphaChange(float alpha) {
                toolbar.getBackground().setAlpha((int) ((1 - alpha)*255f));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.setting:
                Log.e("点击","设置");
                break;
            case R.id.delete:
                Log.e("点击","删除");
                break;
            case R.id.share:
                Log.e("点击","分享");
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
