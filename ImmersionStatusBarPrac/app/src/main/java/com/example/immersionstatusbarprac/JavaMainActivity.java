package com.example.immersionstatusbarprac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.immersionstatusbarprac.util.StatusBarUtils;

public class JavaMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);
        int height = StatusBarUtils.getHeight(this);
        Log.d("tiktok", "onCreate: -------height--" + height);
//        StatusBarUtils.setColor(this, getResources().getColor(R.color.yellow));
        StatusBarUtils.setTransparent(this);
    }
}