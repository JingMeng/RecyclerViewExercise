package com.sinieco.recyclerviewexercise.TabLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sinieco.recyclerviewexercise.R;

/**
 * Author:BaiMeng
 * Time:2018/2/11
 * Description:
 */

public class TabLayoutActivity extends AppCompatActivity {
    private String[] mTitle = {
       "新闻",
       "段子",
       "热点",
//       "房产",
//       "军事",
//       "娱乐",
//       "电影",
//       "音乐",
//       "旅游"
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        FragmentManager manager = getSupportFragmentManager();
        TabFragmentAdapter adapter = new TabFragmentAdapter(manager);
        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position,true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setTabsFromPagerAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        for (int i = 0; i < tabLayout.getTabCount(); i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            View view = LayoutInflater.from(this).inflate(R.layout.tab_custom, tabLayout, false);
            View view = View.inflate(this,R.layout.tab_custom,null);
            ((TextView)view.findViewById(R.id.name)).setText(mTitle[i]);
            tab.setCustomView(view);
        }

    }

    private class TabFragmentAdapter extends FragmentStatePagerAdapter{


        public TabFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new TabItemFragment();
            Bundle bundle = new Bundle();
            bundle.putString("Title",mTitle[position]);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }
    }
}
