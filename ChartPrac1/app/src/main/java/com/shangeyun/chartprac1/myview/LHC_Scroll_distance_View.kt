package com.shangeyun.chartprac1.myview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
/**
 * 可以移动的小红圆球
 * */
class LHC_Scroll_distance_View @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyle) {
    //
    var viewxToY = 60f
    var maxXInit=0f
    var startX = 0f
    override fun onDraw(canvas: Canvas) {
        maxXInit= measuredWidth.toFloat()
        drawCircle(canvas)
    }

    private fun drawCircle(canvas: Canvas) {
        val linePaint = Paint()
        linePaint.isAntiAlias = true
        linePaint.strokeWidth = 20f
        linePaint.strokeCap = Paint.Cap.ROUND
        linePaint.color = Color.RED
        linePaint.style = Paint.Style.FILL

        Log.d("tiktok", "drawCircle: ----------")
        canvas.drawCircle(viewxToY, (measuredHeight/2).toFloat(), 60f, linePaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
            }
            MotionEvent.ACTION_MOVE -> {
                //每次通知计算滑动的一点点
                val dis = event.x - startX
                //记录这次移动结束的event.x就是下一次的滑动起始滑动的位置
                startX = event.x
                //将每次的滑动小段距离在当前距离的基础上叠加起来
                viewxToY=viewxToY+dis
                //通知刷新View
                invalidate()

            }

        }
        return true
    }
}
