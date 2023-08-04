package com.example.cachecountviewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.cachecountviewpager2.adapter.CacheTryAdapter;
import com.example.cachecountviewpager2.databinding.ActivityJavaMainBinding;

public class JavaMainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private RecyclerView rlv;
    private ActivityJavaMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);
        viewPager2 = findViewById(R.id.vp2);
        CacheTryAdapter cacheTryAdapter = new CacheTryAdapter(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_java_main);
        binding.vp2.setAdapter(cacheTryAdapter);
//        viewPager2.setOffscreenPageLimit(1);
//        rlv = (RecyclerView) viewPager2.getChildAt(0);
//        rlv.setItemViewCacheSize(-1);
        binding.btnOne.setOnClickListener(view -> binding.vp2.setCurrentItem(0, false));
        binding.btnTwo.setOnClickListener(view -> binding.vp2.setCurrentItem(1, false));
        binding.btnThree.setOnClickListener(view -> binding.vp2.setCurrentItem(2, false));
        binding.btnFour.setOnClickListener(view -> binding.vp2.setCurrentItem(3, false));
        binding.vp2.setUserInputEnabled(false);
    }
}