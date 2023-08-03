package com.example.cachecountviewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.cachecountviewpager2.adapter.CacheTryAdapter;

public class JavaMainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private RecyclerView rlv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);
        viewPager2 = findViewById(R.id.vp2);
        CacheTryAdapter cacheTryAdapter = new CacheTryAdapter(this);
        viewPager2.setAdapter(cacheTryAdapter);
        viewPager2.setOffscreenPageLimit(1);
        rlv = (RecyclerView) viewPager2.getChildAt(0);
        rlv.setItemViewCacheSize(-2);

    }
}