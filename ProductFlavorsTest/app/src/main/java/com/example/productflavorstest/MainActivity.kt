package com.example.productflavorstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvVersionName: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvVersionName = findViewById(R.id.tv_version_name)
        getString(R.string.version_name)
        tvVersionName.setText(getString(R.string.version_name))
    }
}