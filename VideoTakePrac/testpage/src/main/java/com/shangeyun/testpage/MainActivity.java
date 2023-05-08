package com.shangeyun.testpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.shangeyun.videotakeprac.ShortTakeActivity;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private Button btnTake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTake = findViewById(R.id.btn_take);
//        new ShortTakeActivity()
//        Gson gson = new Gson();
//        new OkHttpClient();
        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShortTakeActivity.class);
                startActivity(intent);
            }
        });

    }
}