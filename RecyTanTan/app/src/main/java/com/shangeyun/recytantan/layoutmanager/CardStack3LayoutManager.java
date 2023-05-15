package com.shangeyun.recytantan.layoutmanager;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class CardStack3LayoutManager extends RecyclerView.LayoutManager {
    // 最开始显示个数
    public static final int MAX_SHOW_COUNT = 4;
    // item 平移Y轴距
    public static final int TRANSLATION_Y = 20;

    // 缩放的大小
    public static final float SCALE = 0.05f;
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    // 必须重写 在 RecyclerView->OnLayout()时候调用,用来摆放 Item位置
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
// 缓存 ViewHolder
        detachAndScrapAttachedViews(recycler);

        // 最下面图片下标
        int bottomPosition = 0;
        // 获取所有图片
        int itemCount = getItemCount();

        //如果所有图片 > 显示的图片
        if (itemCount > MAX_SHOW_COUNT) {
            // 获取到从第几张开始
            bottomPosition = itemCount - MAX_SHOW_COUNT;
        }

        for (int i = bottomPosition; i < itemCount; i++) {
            // 获取当前view宽高
            View view = recycler.getViewForPosition(i);

            addView(view);

            // 测量
            measureChildWithMargins(view, 0, 0);

//            getWidth() RecyclerView 宽
//            getDecoratedMeasuredWidth() View的宽
            int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
            int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);


            // LinearLayoutManager#layoutChunk#layoutDecoratedWithMargins
            // 绘制布局
            layoutDecoratedWithMargins(view, widthSpace / 2,
                    heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view),
                    heightSpace / 2 + getDecoratedMeasuredHeight(view));

            /*
             * 作者:android 超级兵
             * TODO itemCount - 1  = 最后一个元素
                    最后一个元素 - i = 倒数的元素
             */
            int level = itemCount - 1 - i;

            if (level > 0) {
                int value = toDip(view.getContext(), TRANSLATION_Y);

                // 如果不是最后一个才缩放
                if (level < MAX_SHOW_COUNT - 1) {

                    // 平移
                    view.setTranslationY(value * level);
                    // 缩放
                    view.setScaleX(1 - SCALE * level);
                    view.setScaleY(1 - SCALE * level);
                } else {
                    // 最下面的View 和前一个View布局一样(level - 1)
                    view.setTranslationY(value * (level - 1));
                    view.setScaleX(1 - SCALE * (level - 1));
                    view.setScaleY(1 - SCALE * (level - 1));
                }
            }
        }
    }
    private int toDip(Context context, float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }


}

