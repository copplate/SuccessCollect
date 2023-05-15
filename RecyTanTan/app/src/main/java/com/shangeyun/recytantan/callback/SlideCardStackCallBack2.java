package com.shangeyun.recytantan.callback;

import android.content.Context;
import android.graphics.Canvas;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.shangeyun.recytantan.adapter.MyCardStackAdapter;
import com.shangeyun.recytantan.bean.CardStackBean;
import com.shangeyun.recytantan.layoutmanager.CardStack3LayoutManager;

public class SlideCardStackCallBack2<T> extends ItemTouchHelper.SimpleCallback{
    private final MyCardStackAdapter<T> mAdapter;


    public SlideCardStackCallBack2(MyCardStackAdapter<T> mAdapter) {
        super(0, 15);
        this.mAdapter = mAdapter;
    }

    // 拖拽使用,不用管
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
// 当前滑动的View下标
        int layoutPosition = viewHolder.getLayoutPosition();
        // 删除当前滑动的元素
//        CardStackBean<T> bean = mAdapter.getData().remove(layoutPosition);
        String title = mAdapter.getTitleList().remove(layoutPosition);
        CardStackBean<T> bean = new CardStackBean<T>(null,title,"ll");
        // 添加到集合第0个位置 造成循环滑动的效果
//        mAdapter.addData(0, bean);
        mAdapter.notifyDataSetChanged();

    }
    // 设置回弹距离
    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.5f;
    }


    /**
     * lol,感觉这个方法重写还是不重写都没什么变化啊
     * */
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        int maxDistance = recyclerView.getWidth() / 2;

        // dx = 当前滑动x位置
        // dy = 当前滑动y位置
        //sqrt 开根号
        double sqrt = Math.sqrt((dX * dX + dY * dY));

        // 放大系数
        double scaleRatio = sqrt / maxDistance;

        // 系数最大为1
        if (scaleRatio > 1.0) {
            scaleRatio = 1.0;
        }

        int childCount = recyclerView.getChildCount();
        // 循环所有数据
        for (int i = 0; i < childCount; i++) {
            View view = recyclerView.getChildAt(i);

            int valueDip = toDip(view.getContext(), 20f);

            /*
             * 作者:android 超级兵
             * TODO
             *   childCount - 1 =  itemView总个数
             *    childCount - 1 - i = itemView总个数 - i = 从最后一个开始
             *
             * 假设 childCount - 1 = 7
             *     i累加
             *     那么level = childCount - 1 - 0 = 7
             *     那么level = childCount - 1 - 1 = 6
             *     那么level = childCount - 1 - 2 = 5
             *     那么level = childCount - 1 - 3 = 4
             *     那么level = childCount - 1 - 4 = 3
             *      。。。。
             */
            int level = childCount - 1 - i;
            if (level > 0) {
                // 最大显示叠加个数:CardStack2LayoutManager.MAX_SHOW_COUNT = 4
                if (level < CardStack3LayoutManager.MAX_SHOW_COUNT - 1) {
                    // 缩放比例: CardStack2LayoutManager.SCALE = 0.05
                    float scale = CardStack3LayoutManager.SCALE;

                    // valueDip * level  = 原始平移距离
                    // scaleRatio * valueDip = 平移系数
                    // valueDip * level - scaleRatio * valueDip = 手指滑动过程中的Y轴平移距离
                    // 因为是Y轴,所以向上平移是 - 号
                    view.setTranslationY((float) (valueDip * level - scaleRatio * valueDip));

                    // 1 - scale * level = 原始缩放大小
                    // scaleRatio * scale = 缩放系数
                    // 因为是需要放大,所以这里是 + 号
                    view.setScaleX((float) ((1 - scale * level) + scaleRatio * scale));
                    view.setScaleY((float) ((1 - scale * level) + scaleRatio * scale));
                }
            }
        }
    }
    private int toDip(Context context, float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }


// 设置回弹时间
    /*@Override
    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        return 3000;
    }*/

}
