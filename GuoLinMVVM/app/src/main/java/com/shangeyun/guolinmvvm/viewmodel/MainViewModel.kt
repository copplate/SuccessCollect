package com.shangeyun.guolinmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    //    var counter = 0
    val counter = MutableLiveData<Int>()
    init {
        counter.value = 0
    }


    fun plusOne() {
        val count = counter.value ?: 0
        counter.value = count + 1
    }

    fun clear() {
        counter.value = 0
    }
}