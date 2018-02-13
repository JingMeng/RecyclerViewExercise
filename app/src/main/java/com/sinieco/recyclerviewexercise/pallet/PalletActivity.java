package com.sinieco.recyclerviewexercise.pallet;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinieco.recyclerviewexercise.R;

import org.w3c.dom.Text;

/**
 * Author:BaiMeng
 * Time:2018/2/11
 * Description: 演示调色板的使用
 */

public class PalletActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pallet);
        ImageView img = (ImageView) findViewById(R.id.picture);
        BitmapDrawable bd = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bd.getBitmap();
        final TextView tv1 = (TextView) findViewById(R.id.tv1);
        final TextView tv2 = (TextView) findViewById(R.id.tv2);
        final TextView tv3 = (TextView) findViewById(R.id.tv3);
        final TextView tv4 = (TextView) findViewById(R.id.tv4);
        final TextView tv5 = (TextView) findViewById(R.id.tv5);
        final TextView tv6 = (TextView) findViewById(R.id.tv6);
        final TextView tv7 = (TextView) findViewById(R.id.tv7);
        final TextView description = (TextView)findViewById(R.id.description);
        // generate方法是一个异步任务，会在主线程中回调onGenerated
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int darkmuted = palette.getDarkMutedColor(Color.BLACK);
                tv1.setText("DarkMuted:"+"柔和的暗色");
                tv1.setBackgroundColor(darkmuted);

                int darkvibrant = palette.getDarkVibrantColor(Color.BLACK);
                tv2.setText("DarkVibrant:"+"鲜艳的暗色");
                tv2.setBackgroundColor(darkvibrant);

                int lightMutedColor = palette.getLightMutedColor(Color.BLACK);
                tv3.setText("LightMuted:"+"柔和的亮色");
                tv3.setBackgroundColor(lightMutedColor);

                int lightVibrantColor = palette.getLightVibrantColor(Color.BLACK);
                tv4.setText("LightVibrant:"+"鲜艳的亮色");
                tv4.setBackgroundColor(lightVibrantColor);

                int mutedColor = palette.getMutedColor(Color.BLACK);
                tv5.setText("muted:"+"柔和的颜色");
                tv5.setBackgroundColor(mutedColor);

                int vibrantColor = palette.getVibrantColor(Color.BLACK);
                tv6.setText("vibrant:"+"鲜艳的颜色");
                tv6.setBackgroundColor(vibrantColor);

                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                setBackgroundAlpha(description, 0.6f,vibrantSwatch,"此图片的标题,\ngoogle推荐的鲜艳的颜色,\n" +
                        "我就看看咋样");

//                Palette.Swatch mutedSwatch = palette.getMutedSwatch();
//                setBackgroundAlpha(description,0.6f,mutedSwatch,
// "此图片的标图，\ngoogle推荐的柔和的颜色，\n我就看看咋样");


            }
        });
    }

    private void setBackgroundAlpha(TextView tv, float alpha , Palette.Swatch swatch,String
            description){
        int textColor = swatch.getBodyTextColor();
        int rgb = swatch.getRgb();
        tv.setText(description);
        tv.setTextColor(textColor);
        int blue = rgb & 0xff;
        int green = rgb >> 8 & 0xff;
        int red = rgb >> 16 & 0xff;
        //无符号右移，获取alpha
        int lastAlpha = rgb >>> 24 & 0xff;
        int currentAlpha = (int) (lastAlpha * alpha);
        int argb = Color.argb(currentAlpha, red, green, blue);
        tv.setBackgroundColor(argb);
    }
}
