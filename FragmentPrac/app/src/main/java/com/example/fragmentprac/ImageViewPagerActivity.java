package com.example.fragmentprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.fragmentprac.adapter_.MyImageViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImageViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private MyImageViewPagerAdapter myImageViewPagerAdapter;
    private List<ImageView> mImageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_pager);

        mViewPager = findViewById(R.id.vp);
        initData();

        myImageViewPagerAdapter = new MyImageViewPagerAdapter(mImageViews);
        mViewPager.setAdapter(myImageViewPagerAdapter);


    }

    private void initData() {
        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.drawable.jp_building);
        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.drawable.jp_ru_army);
        ImageView imageView3 = new ImageView(this);
        imageView3.setImageResource(R.drawable.jp_tiger);
        ImageView imageView4 = new ImageView(this);
        imageView4.setImageResource(R.drawable.jp_quan_xiao_jiang);
        mImageViews = new ArrayList<>();
        mImageViews.add(imageView1);
        mImageViews.add(imageView2);
        mImageViews.add(imageView3);
        mImageViews.add(imageView4);
    }
}