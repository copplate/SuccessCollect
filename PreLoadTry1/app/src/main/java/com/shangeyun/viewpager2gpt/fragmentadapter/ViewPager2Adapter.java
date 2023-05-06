package com.shangeyun.viewpager2gpt.fragmentadapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
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
import com.shangeyun.viewpager2gpt.R;
import com.shangeyun.viewpager2gpt.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private List<VideoFragment> fragments;
    private Context mContext;
    private SimpleExoPlayer mPlayer; // 新型播放器对象
    private SimpleExoPlayer mPlayer2; // 新型播放器对象
    private SimpleExoPlayer mPlayer3; // 新型播放器对象


    private String url = "http://192.168.3.234:8080/nianan2_war/beauty3";
    private String url2 = "http://192.168.3.234:8080/nianan2_war/beauty4";
    private String url3 = "http://192.168.3.234:8080/nianan2_war/beauty5";
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity, List<VideoFragment> fragments) {
        super(fragmentActivity);
        this.fragments = fragments;
        mContext = fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("tiktok", "createFragment: ------------");
        List<String> videoList = new ArrayList<>();
        mPlayer = new SimpleExoPlayer.Builder(mContext).build();
        mPlayer2 = new SimpleExoPlayer.Builder(mContext).build();
        mPlayer3 = new SimpleExoPlayer.Builder(mContext).build();
        prepareVideo(url);
        prepareNextVideo(url2);
        prepareThirdVideo(url3);
        if (position == 0) {
            fragments.get(position).setPlayer(mPlayer);
            fragments.get(position + 1).setPlayer(mPlayer2);
            fragments.get(position + 2).setPlayer(mPlayer3);
        }
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        Log.d("tiktok", "getItemCount: -------");
        return fragments.size();
    }

    private void prepareVideo(String videoUrl) {
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
    private void prepareNextVideo(String videoUrl) {
        // 创建HTTP在线视频的工厂对象
        DataSource.Factory factory = new DefaultHttpDataSourceFactory(Util.getUserAgent(mContext, "okook"),
                new DefaultBandwidthMeter.Builder(mContext).build());
        // 创建指定地址的媒体对象
        MediaItem videoItem = new MediaItem.Builder().setUri(Uri.parse(videoUrl)).build();
        // 基于工厂对象和媒体对象创建媒体来源
        MediaSource videoSource = new ProgressiveMediaSource.Factory(factory).createMediaSource(videoItem);
        mPlayer2.setMediaSource(videoSource); // 设置播放器的媒体来源
        // 设置播放器的重播模式，REPEAT_MODE_ALL表示反复重播
        mPlayer2.setRepeatMode(SimpleExoPlayer.REPEAT_MODE_ALL);
        mPlayer2.prepare(); // 播放器准备就绪

    }
    private void prepareThirdVideo(String videoUrl) {
        // 创建HTTP在线视频的工厂对象
        DataSource.Factory factory = new DefaultHttpDataSourceFactory(Util.getUserAgent(mContext, "okook"),
                new DefaultBandwidthMeter.Builder(mContext).build());
        // 创建指定地址的媒体对象
        MediaItem videoItem = new MediaItem.Builder().setUri(Uri.parse(videoUrl)).build();
        // 基于工厂对象和媒体对象创建媒体来源
        MediaSource videoSource = new ProgressiveMediaSource.Factory(factory).createMediaSource(videoItem);
        mPlayer3.setMediaSource(videoSource); // 设置播放器的媒体来源
        // 设置播放器的重播模式，REPEAT_MODE_ALL表示反复重播
        mPlayer3.setRepeatMode(SimpleExoPlayer.REPEAT_MODE_ALL);
        mPlayer3.prepare(); // 播放器准备就绪

    }
}
