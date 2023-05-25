package com.shangeyun.chartprac1.myview

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.shangeyun.chartprac1.bean.ViewPoint
import com.shangeyun.chartprac1.util.DensityUtil
/**
 * 比较low的折线图
 * */
class LHC_Line_View  @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0):
    View(context, attrs, defStyle) {
    val grid_wh=DensityUtil.px2fdp(context, 60f)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        val back_paint= Paint()
//        back_paint.style= Paint.Style.FILL
//        back_paint.color= Color.WHITE
//        back_paint.strokeWidth=10f
//        canvas.drawCircle(0f,0f,250f,back_paint)
        val back_paint=Paint()
        back_paint.style= Paint.Style.FILL
        back_paint.color=Color.WHITE
        back_paint.strokeWidth=10f
        canvas.save()
        //竟变化坐标。y轴向上为正
        canvas.scale(1f,-1f)
        //平移坐标系到左下角
        canvas.translate(0f, -(measuredHeight.toFloat()))
        val grid_paint=Paint()

        back_paint.style= Paint.Style.FILL
        back_paint.color=Color.WHITE
        back_paint.strokeWidth=10f


        grid_paint.style= Paint.Style.STROKE
        grid_paint.color=Color.WHITE
        grid_paint.strokeWidth=2f

        //平行y轴的线段
        val pathY= Path()
        pathY.moveTo((DensityUtil.px2fdp(context,50f)) ,0f)
        pathY.lineTo((DensityUtil.px2fdp(context,50f)) , measuredWidth.toFloat())
        canvas.drawPath(pathY,grid_paint)

        //平行x轴的线段
        val pathX=Path()
        pathX.moveTo(0f,DensityUtil.px2fdp(context,50f))
        pathX.lineTo(measuredWidth.toFloat(),DensityUtil.px2fdp(context,50f))
        canvas.drawPath(pathX,grid_paint)
        //x轴个数
        val countX=measuredWidth/DensityUtil.px2dp(context,40f)
        //y轴个数
        val countY=measuredHeight/DensityUtil.px2dp(context,40f)
        //平行x轴的线段
        /*for (index in 0 until countX.toInt()){
            val pathX=Path()
            pathX.moveTo(0f,DensityUtil.px2fdp(context,40f)*(index+1))
            pathX.lineTo(measuredWidth.toFloat(),DensityUtil.px2fdp(context,40f)*(index+1))
            canvas.drawPath(pathX,grid_paint)
        }*/
//        drawGridView(canvas,grid_paint)
        val viewPoint1 = ViewPoint(0f, 0f)
        val viewPoint2 = ViewPoint(60f,120f)
        val viewPoint3 = ViewPoint(90f,220f)
        val viewPoint4 = ViewPoint(120f,70f)
        val viewPoint5 = ViewPoint(150f,330f)
        val points = ArrayList<ViewPoint>()
        points.add(viewPoint1)
        points.add(viewPoint2)
        points.add(viewPoint3)
        points.add(viewPoint4)
        points.add(viewPoint5)
//        canvas.translate(0f,0f)
        drawLine(points,canvas)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //return super.onTouchEvent(event)
        if (event?.action == MotionEvent.ACTION_DOWN) {
            Log.e("tiktok", "onTouchEvent:x" + event.x)
            Log.e("tiktok", "onTouchEvent:y" + event.y)

            return super.onTouchEvent(event)
        } else {
            return super.onTouchEvent(event)

        }


    }

    //绘制网格
    private fun drawGridView(canvas: Canvas, grid_paint: Paint) {
        //平行y轴的线段
        val pathY = Path()
        pathY.moveTo(grid_wh, 0f)
        pathY.lineTo(grid_wh, measuredHeight.toFloat())
        canvas.drawPath(pathY, grid_paint)

        //平行x轴的线段
        val pathX = Path()
        pathX.moveTo(0f, grid_wh)
        pathX.lineTo(measuredWidth.toFloat(), grid_wh)
        canvas.drawPath(pathX, grid_paint)

        //x轴个数
        val countX = measuredWidth /grid_wh
        //y轴个数
        val countY = measuredHeight /grid_wh
        canvas.save()

        for (index in 0 until countY.toInt()) {
            canvas.translate(0f,grid_wh)
            canvas.drawPath(pathX, grid_paint)

        }

        canvas.restore()
        for (index in 0 until countX.toInt()) {
            canvas.translate(grid_wh, 0f)
            canvas.drawPath(pathY, grid_paint)
        }
    }
    //绘制折线图
    private fun drawLine(pointList: java.util.ArrayList<ViewPoint>, canvas: Canvas) {
        val linePaint=Paint()
        val path=Path()
        linePaint.style= Paint.Style.STROKE
        linePaint.color=Color.argb(255,34,192,255)
        linePaint.strokeWidth=10f
        //连线
        for (index in 0 until pointList.size){
            path.lineTo(pointList[index].x,pointList[index].y)
        }
        canvas.drawPath(path,linePaint)

        val circle_paint = Paint()
        circle_paint.strokeWidth = 10f
        circle_paint.style = Paint.Style.FILL
        circle_paint.color = Color.argb(255, 34, 192, 255)
        //连线
        for (index in 0 until pointList.size) {
            path.lineTo(pointList[index].x, pointList[index].y)
        }
        canvas.drawPath(path, linePaint)
        //渐变色菜的填充
        //连线
        for (index in 0 until pointList.size) {
            path.lineTo(pointList[index].x, pointList[index].y)
        }
        val endIndex=pointList.size-1
        path.lineTo(pointList[endIndex].x, 0f)
        path.close()
        linePaint.style= Paint.Style.FILL
        linePaint.shader=getShader()
        canvas.drawPath(path, linePaint)


        //画定点圆圈
        for (index in 0 until pointList.size) {
            canvas.drawCircle(pointList[index].x, pointList[index].y,16f,circle_paint)
        }

    }
    private fun getShader(): Shader {
        val shadeColors = intArrayOf(Color.argb(255, 250, 49, 33), Color.argb(165, 234, 115, 9), Color.argb(200, 32, 208, 88))
        return  LinearGradient((measuredWidth/2).toFloat(), measuredHeight.toFloat(), (measuredWidth/2).toFloat(), 0f, shadeColors, null, Shader.TileMode.CLAMP)
    }


}