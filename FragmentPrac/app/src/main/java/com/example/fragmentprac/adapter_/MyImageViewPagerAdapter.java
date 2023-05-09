package com.example.fragmentprac.adapter_;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MyImageViewPagerAdapter extends PagerAdapter {

    private List<ImageView> mImageViewList;

    public MyImageViewPagerAdapter(List<ImageView> mImageViewList) {
        this.mImageViewList = mImageViewList;
    }

    @Override
    public int getCount() {
        return mImageViewList == null ? 0 : mImageViewList.size();
    }


    //这个isViewFromObject()方法是说判断view和object是不是一个东西
    //Object object是public Object instantiateItem()返回的item，
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = mImageViewList.get(position);
        //把view添加到container里面去，这个ViewGroup container就是整个ViewPager
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }




}
