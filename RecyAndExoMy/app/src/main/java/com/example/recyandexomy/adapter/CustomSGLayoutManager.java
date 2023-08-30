package com.example.recyandexomy.adapter;

import android.content.Context;
import android.util.DisplayMetrics;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class CustomSGLayoutManager extends LinearLayoutManager {
    private static final float SCROLL_SPEED = 0.7f; // 自定义的滚动速度倍数

    public CustomSGLayoutManager(Context context) {
        super(context);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrolled = super.scrollVerticallyBy((int) (dy * SCROLL_SPEED), recycler, state);
        return scrolled;
        //return super.scrollVerticallyBy(dy, recycler, state);

    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        //super.smoothScrollToPosition(recyclerView, state, position);
        LinearSmoothScroller linearSmoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SCROLL_SPEED * super.calculateSpeedPerPixel(displayMetrics);
                    }
                };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    public static float getScrollSpeed() {
        return SCROLL_SPEED;
    }

}
