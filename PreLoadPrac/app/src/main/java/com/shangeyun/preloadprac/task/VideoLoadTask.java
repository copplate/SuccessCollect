package com.shangeyun.preloadprac.task;

import android.app.Activity;
import android.net.Uri;


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
import com.shangeyun.preloadprac.util.MyConstant;

import java.util.ArrayList;
import java.util.List;

public class VideoLoadTask extends Thread {
    private static final String TAG = "VideoLoadTask";
    private Activity mAct; // 声明一个活动实例
    private VideoLoadListener mListener; // 声明一个视频加载的监听器对象
    private String name;
//    private int a = 0;
    private List<SimpleExoPlayer> playerList = new ArrayList<>();


    public VideoLoadTask(Activity act, String threadName, VideoLoadListener listener) {
        mAct = act;
//        super.setName(threadName);//这样好像没法设置线程名啊
        mListener = listener;
    }

    @Override
    public void run() {
        List<VideoFragment> fragmentList = new ArrayList<>();


        VideoInfo videoInfo = new VideoInfo();
        VideoInfo videoInfo2 = new VideoInfo();
        VideoInfo videoInfo3 = new VideoInfo();
        VideoInfo videoInfo4 = new VideoInfo();
        VideoInfo videoInfo5 = new VideoInfo();
        VideoInfo videoInfo6 = new VideoInfo();

        SimpleExoPlayer mPlayer = new SimpleExoPlayer.Builder(mAct).build();
        SimpleExoPlayer mPlayer2 = new SimpleExoPlayer.Builder(mAct).build();
        SimpleExoPlayer mPlayer3 = new SimpleExoPlayer.Builder(mAct).build();
        SimpleExoPlayer mPlayer4 = new SimpleExoPlayer.Builder(mAct).build();
        SimpleExoPlayer mPlayer5 = new SimpleExoPlayer.Builder(mAct).build();
        SimpleExoPlayer mPlayer6 = new SimpleExoPlayer.Builder(mAct).build();
        playerList.add(mPlayer);
        playerList.add(mPlayer2);
        playerList.add(mPlayer3);
        playerList.add(mPlayer4);
        playerList.add(mPlayer5);
        playerList.add(mPlayer6);
        List<String> tempList = new ArrayList<>();
        tempList.add(MyConstant.testUrl6);
        tempList.add(MyConstant.testUrl2);
        tempList.add(MyConstant.testUrl3);
        tempList.add(MyConstant.testUrl4);
        tempList.add(MyConstant.testUrl5);
        tempList.add(MyConstant.testUrl6);
        mAct.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    prepareVideo(tempList.get(i),playerList.get(i));
                }
            }
        });
        videoInfo.setVideo(MyConstant.testUrl6);
        videoInfo.setDesc("fjsahfjas");
        VideoFragment videoFragment = VideoFragment.newInstance(0, videoInfo);
        //
        videoFragment.setPlayer(playerList.get(0));
        fragmentList.add(videoFragment);
        List<VideoInfo> aaa = new ArrayList<>();
        aaa.add(videoInfo);
        videoInfo2.setVideo(MyConstant.testUrl2);
        VideoFragment videoFragment2 = VideoFragment.newInstance(0, videoInfo2);
        //
        videoFragment2.setPlayer(playerList.get(1));
        fragmentList.add(videoFragment2);
        aaa.add(videoInfo2);
        videoInfo3.setVideo(MyConstant.testUrl3);
        VideoFragment videoFragment3 = VideoFragment.newInstance(0, videoInfo3);
        //
        videoFragment3.setPlayer(playerList.get(2));

        fragmentList.add(videoFragment3);
        aaa.add(videoInfo3);
        videoInfo4.setVideo(MyConstant.testUrl4);
        VideoFragment videoFragment4 = VideoFragment.newInstance(0, videoInfo4);
        //
        videoFragment4.setPlayer(playerList.get(3));

        fragmentList.add(videoFragment4);
        aaa.add(videoInfo4);
        videoInfo5.setVideo(MyConstant.testUrl5);
        VideoFragment videoFragment5 = VideoFragment.newInstance(0, videoInfo5);
        //
        videoFragment5.setPlayer(playerList.get(4));

        fragmentList.add(videoFragment5);
        aaa.add(videoInfo5);
        videoInfo6.setVideo(MyConstant.testUrl6);
        VideoFragment videoFragment6 = VideoFragment.newInstance(0, videoInfo6);
        //
        videoFragment6.setPlayer(playerList.get(5));

        fragmentList.add(videoFragment6);
        aaa.add(videoInfo6);
        mListener.onVideoLoad(aaa, fragmentList);
    }


    // 定义一个视频加载的监听器接口，在获得响应之后回调onVideoLoad方法
    public interface VideoLoadListener {
        void onVideoLoad(List<VideoInfo> videoList, List<VideoFragment> fragmentList);
    }

    private void prepareVideo(String videoUrl, SimpleExoPlayer mPlayer) {
        // 创建HTTP在线视频的工厂对象
        DataSource.Factory factory = new DefaultHttpDataSourceFactory(Util.getUserAgent(mAct, "okook"),
                new DefaultBandwidthMeter.Builder(mAct).build());
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
