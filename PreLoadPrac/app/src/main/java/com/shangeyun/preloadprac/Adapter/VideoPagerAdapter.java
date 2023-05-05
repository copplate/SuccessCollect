package com.shangeyun.preloadprac.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.shangeyun.preloadprac.bean.VideoInfo;
import com.shangeyun.preloadprac.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

//上下滑动的vp适配器
public class VideoPagerAdapter extends FragmentStateAdapter {
    private List<VideoInfo> mVideoList; // 声明一个地址列表
    private Context mContext;
    private List<VideoFragment> fragments;
    private SimpleExoPlayer mPlayer;
    private List<SimpleExoPlayer> playerList = new ArrayList<>();


    // 碎片页适配器的构造方法，传入碎片管理器与商品信息列表
    public VideoPagerAdapter(FragmentActivity fa, List<VideoInfo> videoList, List<VideoFragment> fragments) {
        super(fa);
        this.mContext = fa;
        mVideoList = videoList;
        this.fragments = fragments;
    }

    // 创建指定位置的碎片Fragment
    @Override
    public Fragment createFragment(int position) {
        Log.d("tiktok", "createFragment: ----------");
        //这个是左边的recommendFragment

        return fragments == null ? null :fragments.get(position);
    }

    // 获取碎片Fragment的个数
    @Override
    public int getItemCount() {
        return mVideoList.size();
    }
    private void prepareVideo(String videoUrl,SimpleExoPlayer mPlayer) {
        // 创建HTTP在线视频的工厂对象
        DataSource.Factory factory = new DefaultHttpDataSourceFactory(Util.getUserAgent(mContext, "okook"),
                new DefaultBandwidthMeter.Builder(mContext).build());
        // 创建指定地址的媒体对象
        MediaItem videoItem = new MediaItem.Builder().setUri(Uri.parse(videoUrl)).build();
        // 基于工厂对象和媒体对象创建媒体来源
        MediaSource videoSource = new ProgressiveMediaSource.Factory(factory).createMediaSource(videoItem);
        mPlayer.setMediaSource(videoSource); // 设置播放器的媒体来源
        // 设置播放器的重播模式，REPEAT_MODE_ALL表示反复重播
        mPlayer.setRepeatMode(SimpleExoPlayer.REPEAT_MODE_ALL);
        mPlayer.prepare(); // 播放器准备就绪
    }

}
