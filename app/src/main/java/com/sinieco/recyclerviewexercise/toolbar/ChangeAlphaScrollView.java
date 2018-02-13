package com.sinieco.recyclerviewexercise.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ScrollView;

/**
 * Author:BaiMeng
 * Time:2018/2/11
 * Description:
 */

public class ChangeAlphaScrollView extends ScrollView {
    private OnAlphaChangeListener mAlphaListener;
    public ChangeAlphaScrollView(Context context) {
        super(context,null);
    }

    public ChangeAlphaScrollView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public ChangeAlphaScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        int y = getScrollY();
        int height = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
        if(mAlphaListener != null && y <= (int)height/3 ){
            Log.e("透明度","==="+(y/(height/3f)));
            mAlphaListener.onAlphaChange(y/(height/3f));
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void setOnAlphaChangeListener(OnAlphaChangeListener listener){
        this.mAlphaListener = listener;
    }
}
