package com.example.leftslidedelete

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Scroller
import kotlin.math.abs

class SwipeView(context: Context, attrs: AttributeSet): ViewGroup(context,attrs) {
    private val scroller= Scroller(context);
    private var sendViewWidth=0;
    private var firstX="0".toFloat();//第一个触点的位置
    private var isSendViewShow=false;
    private var newX="0".toFloat()


    private var lastX="0".toFloat();
    private var lastY="0".toFloat();
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (childCount==2){
            val view = getChildAt(1)
            sendViewWidth=view.measuredWidth;//记录按钮的宽度
            view.layout(r, t, r + view.measuredWidth, b) //删除按钮排布在第一个View之后
        }
        getChildAt(0).layout(l, t, r, b)//设置第一个控件的位置 上下左右的位置
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureChild(getChildAt(0),widthMeasureSpec, heightMeasureSpec)//测量孩子的高度和宽度 先测量第一个孩子 因为第二个孩子的高度肯定跟着第一个孩子跑
        var width = MeasureSpec.getSize(widthMeasureSpec)//父组件能够给的宽度
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)//父组件的宽度的设置方式
        var height = MeasureSpec.getSize(heightMeasureSpec)//父组件能够给的高度
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)//父组件的高度的设置方式
        var resultWidth=0;
        var resultHeight=0;

        //其实只有三种类型
        if(widthSpecMode==MeasureSpec.EXACTLY){//表示父控件给子view一个具体的值，子view要设置成这些值的大小
            resultWidth=width //听老爸的
        }else if (widthSpecMode==MeasureSpec.UNSPECIFIED){//父组件没告诉你限制 随你发挥
            resultWidth= getChildAt(0).measuredWidth //用第一个孩子宽度
        }else if(widthSpecMode==MeasureSpec.AT_MOST){//表示父控件个子view一个最大的特定值，而子view不能超过这个值的大小
            resultWidth=Math.min(getChildAt(0).measuredWidth,width) //看看谁最小听谁的
        }
        //其实只有三种类型
        if(heightSpecMode==MeasureSpec.EXACTLY){//表示父控件给子view一个具体的值，子view要设置成这些值的大小
            resultHeight=height//听老爸的
        }else if (heightSpecMode==MeasureSpec.UNSPECIFIED){//父组件没告诉你限制 随你发挥
            resultHeight= getChildAt(0).measuredHeight //用第一个孩子的高度
        }else if(heightSpecMode==MeasureSpec.AT_MOST){//表示父控件个子view一个最大的特定值，而子view不能超过这个值的大小
            resultHeight=Math.min(getChildAt(0).measuredHeight,height) //看看谁最小听谁的
        }

        setMeasuredDimension(resultWidth, resultHeight)

        //测量第二个  第二个孩子其实高度已经确定了，所以高度穿进去用 EXACTLY 和老爸的height，宽度呢就还是自己来所以把爷爷的宽度要求传递进去老爸其实不关心你的宽度
        measureChild(getChildAt(1),widthMeasureSpec, MeasureSpec.makeMeasureSpec(resultHeight,MeasureSpec.EXACTLY))//测量孩子的高度和宽度




    }


    //拿到了这个事件    我是否是否消费这个事件  如果不消费的话就会还给父级 让父取处理
    override fun onTouchEvent(event: MotionEvent): Boolean {

        var consum=true

        Log.d("SwipeView", "onTouchEvent: "+event.action)
        var x=event.x//这次进来的x
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {// 如果孩子不要这个事件 才会到这里来 如果孩子不要的话 自己要消耗掉否则后续的事件都没了
                consum=true
            }
            MotionEvent.ACTION_MOVE -> {//这个会进来多次
                var newX = firstX-x;//手指移动距离第一次触点的位置 其实就是现在内容需要在的位置 （左为正）
                Log.d("SwipeView", "ACTION_MOVE:isSendViewShow "+isSendViewShow)
                Log.d("SwipeView", "ACTION_MOVE:OFFSET "+newX)

                if (!isSendViewShow){//按钮还没显示的情况下  向左滑才是有效的  0<=newX<=sendViewWidth
                    if (newX>sendViewWidth){ //最远只能滑动第二个控件的宽度
                        newX=sendViewWidth.toFloat()
                    }
                    if (newX<0){//无效的
                        newX="0".toFloat()
                    }
                }


                if (isSendViewShow){//按钮已经显示了  向右滑才是有效的有效的距离   -sendViewWidth<=newX<=0
                    if (newX<-sendViewWidth){
                        newX=-sendViewWidth.toFloat()
                    }
                    if (newX>0){//无效的
                        newX="0".toFloat()
                    }

                    newX=newX+sendViewWidth
                }



                scrollTo(newX.toInt(), 0)//因为这个方法 是让内容距离原始（也就是第一次绘制）的位置 偏移的位置 所以上面newX算的是距离初始的偏移量
                Log.d("SwipeView", "ACTION_MOVE: newX"+newX)

                consum=true
            }

            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL-> {
                if (isSendViewShow){//原先是有按钮的
                    if (scrollX <((sendViewWidth/5)*4)) {//说明真的想关闭了
                        scrollTo(0, 0)
                        isSendViewShow=false
                    } else {
                        scrollTo(sendViewWidth, 0)
                        isSendViewShow=true
                    }

                }else{//没展示按钮的
                    if (scrollX >= sendViewWidth/5) {// 代表真的想打开
                        scrollTo(sendViewWidth, 0)
                        isSendViewShow=true
                    } else {//否则不显示
                        scrollTo(0, 0)
                        isSendViewShow=false
                    }
                }

                Log.d("SwipeView", "ACTION_UP: isSendViewShow "+isSendViewShow)

            }
            else -> { consum=false}
        }
        return consum
    }




    //做外部拦截----是否消拦截这个事件（true拦截，false 不拦截）  因为孩子设置了click事件，如果直接发下 所有的事件都会被孩子消费掉的，不会再回来用父亲的事件了。所以自己要先把用到的拦截一下 其他的再发给孩子
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action){
            MotionEvent.ACTION_DOWN -> {//因为孩子要触发click 起码要有个down,所以先下发。 当你拦截了一个之后，后续onInterceptTouchEvent不会再调用了
                //记录按下的时候的X
                firstX=ev.x;//记录

                newX="0".toFloat()
                return false;
            }
            MotionEvent.ACTION_MOVE -> {
                return true;
            }
            else->{return false}
        }

    }



    //做内部拦截  就是去控制老爸的
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action){
            MotionEvent.ACTION_DOWN -> {//让老爸不要拦截
                lastX=ev.x;
                lastY=ev.y;
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            MotionEvent.ACTION_MOVE -> {
                if (abs( ev.x-lastX)> abs(ev.y-lastY)){//说明x滑动的距离大于Y的距离 说明是左右滑动 那么就不允许老爸拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }else{//允许老爸去拦截
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
            MotionEvent.ACTION_UP ->{//让老爸不要拦截
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            else->{return false}
        }

        return super.dispatchTouchEvent(ev)
    }
}
