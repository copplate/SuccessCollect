package com.shangeyun.preloadprac.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.google.android.exoplayer2.source.MediaSource;
import com.shangeyun.preloadprac.bean.VideoInfo;
import com.shangeyun.preloadprac.fragment.VideoFragment;

import java.util.List;

//上下滑动的vp适配器
public class VideoPagerAdapter extends FragmentStateAdapter {
    private List<VideoInfo> mVideoList; // 声明一个地址列表
    private List<MediaSource> mSources;//声明一个视频资源列表
    private Context context;

    // 碎片页适配器的构造方法，传入碎片管理器与商品信息列表
    public VideoPagerAdapter(FragmentActivity fa, List<VideoInfo> videoList,List<MediaSource> mediaSources) {
        super(fa);
        this.context = fa;
        mVideoList = videoList;
        mSources = mediaSources;
    }

    // 创建指定位置的碎片Fragmen
    @Override
    public Fragment createFragment(int position) {
        //这个是左边的recommendFragment

        return VideoFragment.newInstance(position, mVideoList.get(position),mSources.get(position));
    }

    // 获取碎片Fragment的个数
    @Override
    public int getItemCount() {
        return mVideoList.size();
    }
}
