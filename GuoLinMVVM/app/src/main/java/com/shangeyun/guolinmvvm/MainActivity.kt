package com.shangeyun.guolinmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shangeyun.guolinmvvm.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var btn:Button
    private lateinit var tv:TextView
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(MyObserver())
        tv = findViewById(R.id.tv)
        btn = findViewById(R.id.btn)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //郭霖写法
        viewModel.counter.observe(this, Observer { count ->
            tv.text = count.toString()
        })

        //我抄gpt的写法
        viewModel.counter.observe(this,Observer<Int>(){
            it -> tv.text = it.toString()
        })
        //gpt的写法
        viewModel.counter.observe(this, object : Observer<Int> {
            override fun onChanged(count: Int) {
                tv.text = count.toString()
            }
        })

        btn.setOnClickListener(View.OnClickListener {
            viewModel.plusOne()
        })
        /*btn.setOnClickListener(View.OnClickListener {
            viewModel.counter++
            tv.text = "${viewModel.counter}"

        })*/
//        tv.text = "${viewModel.counter}"

    }
}