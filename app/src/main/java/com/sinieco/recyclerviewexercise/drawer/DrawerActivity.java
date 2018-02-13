package com.sinieco.recyclerviewexercise.drawer;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sinieco.recyclerviewexercise.R;

/**
 * Author:BaiMeng
 * Time:2018/2/7
 * Description:
 */

public class DrawerActivity extends AppCompatActivity {

    private LinearLayout contentPart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
//        NavigationView navigation = (NavigationView)findViewById(R.id.navigation);
//        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer);
//        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                CharSequence title = item.getTitle();
//                Log.e("点击",title+"按钮");
//                return true;
//            }
//        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer);
        LinearLayout menuPart = (LinearLayout)findViewById(R.id.menu_part);
        contentPart = (LinearLayout)findViewById(R.id.content_part);
        setSupportActionBar(toolbar);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string
//                .open,R
//                .string.close);
//        toggle.syncState();
//        drawer.addDrawerListener(toggle);
//
//        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                // slideOffset from 0 to 1
//                float scale = 1 - (0.3f * slideOffset);
//                int width = contentPart.getMeasuredWidth();
//                contentPart.setScaleX(scale);
//                contentPart.setScaleY(scale);
//                contentPart.setTranslationX(width * slideOffset * 0.5f);
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//
//            }
//        });
    }

//    public void showSnackbar(final View view) {
//        Snackbar snackbar = Snackbar.make(view, "展示snackbar", Snackbar.LENGTH_LONG);
//        snackbar.setAction("我知道了", new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast toast = new Toast(v.getContext());
//                toast.setGravity(Gravity.CENTER,0,0);
//                toast.setView(LayoutInflater.from(v.getContext()).inflate(R.layout.toast_view,
//                        contentPart,false));
//                toast.setDuration(Toast.LENGTH_LONG);
//                toast.show();
//            }
//        });
//        snackbar.show();
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_searchview,menu);
        MenuItem menuItem =  menu.findItem(R.id.serch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconified(false);
        // 设置进入页面显示搜索，默认显示Title
        searchView.isIconfiedByDefault();
        searchView.setSubmitButtonEnabled(true);
        ImageView goBtn = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id
                .search_go_btn);
        goBtn.setImageResource(android.R.drawable.ic_btn_speak_now);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("点击","提交按钮");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e("QueryTextChange",newText);
                return false;
            }
        });    
        return super.onCreateOptionsMenu(menu);
    }
}
