package com.example.cachecountviewpager2.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun activityStart() {
        Log.d("tiktok", "activityStart")
        Log.d("tiktok", "activityStart")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun activityStop() {
        Log.d("tiktok", "activityStop")
        Log.d("tiktok", "activityStop")
    }


}