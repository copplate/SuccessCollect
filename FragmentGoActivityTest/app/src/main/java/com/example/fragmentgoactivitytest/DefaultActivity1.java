package com.example.fragmentgoactivitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class DefaultActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default1);
        Intent intent = new Intent();
        intent.putExtra("DefaultActivity1", "你来到了DefaultActivity1");
        setResult(RESULT_OK,intent);
    }
}