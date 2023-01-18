package com.example.sn.home_container;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {

    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        System.out.println(position);
        int pageWidth = view.getWidth();
        View page = view;
        float mOffset=0.5f;

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0.5f);
        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(view.getWidth()/2*position);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position < 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

            // Counteract the default slide transition
            view.setTranslationX(-position * view.getWidth());
            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
            view.setTranslationY(0.8f);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            //页面不滑动的时候
            //移动X轴坐标，使得卡片在同一坐标
            page.setTranslationX(-position * page.getWidth());
            //缩放卡片并调整位置
            float scale = (page.getWidth() - mOffset * position) / page.getWidth();
            page.setScaleX(0.75f);
            page.setScaleY(0.75f);
            //移动Y轴坐标
            page.setTranslationY(position * mOffset);
        }
    }
}
