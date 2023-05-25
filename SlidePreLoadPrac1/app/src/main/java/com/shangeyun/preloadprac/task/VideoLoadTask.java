package com.shangeyun.preloadprac.task;

import android.app.Activity;
import android.net.Uri;


import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.shangeyun.preloadprac.bean.VideoInfo;
import com.shangeyun.preloadprac.util.MyConstant;

import java.util.ArrayList;
import java.util.List;

public class VideoLoadTask extends Thread {
    private static final String TAG = "VideoLoadTask";
    private Activity mAct; // 声明一个活动实例
    private VideoLoadListener mListener; // 声明一个视频加载的监听器对象
    private String name;
    //    private int a = 0;
    private CacheDataSourceFactory cacheDataSourceFactory;

    public VideoLoadTask(Activity act, String threadName,
                         CacheDataSourceFactory cacheDataSourceFactory,
                         VideoLoadListener listener) {
        mAct = act;
//        super.setName(threadName);//这样好像没法设置线程名啊
        mListener = listener;
        this.cacheDataSourceFactory = cacheDataSourceFactory;
    }

    @Override
    public void run() {

        VideoInfo videoInfo = new VideoInfo();
        VideoInfo videoInfo2 = new VideoInfo();
        VideoInfo videoInfo3 = new VideoInfo();
        VideoInfo videoInfo4 = new VideoInfo();
        VideoInfo videoInfo5 = new VideoInfo();
        VideoInfo videoInfo6 = new VideoInfo();
        videoInfo.setVideo(MyConstant.testUrl1);
        videoInfo.setDesc("fjsahfjas");
        List<VideoInfo> aaa = new ArrayList<>();
        aaa.add(videoInfo);
        videoInfo2.setVideo(MyConstant.testUrl2);
        aaa.add(videoInfo2);
        videoInfo3.setVideo(MyConstant.testUrl3);
        aaa.add(videoInfo3);
        videoInfo4.setVideo(MyConstant.testUrl4);
        aaa.add(videoInfo4);
        videoInfo5.setVideo(MyConstant.testUrl5);
        aaa.add(videoInfo5);
        videoInfo6.setVideo(MyConstant.testUrl6);
        aaa.add(videoInfo6);
        List<MediaSource> mSources = new ArrayList<>();
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(cacheDataSourceFactory)
                .createMediaSource(Uri.parse(MyConstant.testUrl1));
        MediaSource mediaSource2 = new ProgressiveMediaSource.Factory(cacheDataSourceFactory)
                .createMediaSource(Uri.parse(MyConstant.testUrl2));
        MediaSource mediaSource3 = new ProgressiveMediaSource.Factory(cacheDataSourceFactory)
                .createMediaSource(Uri.parse(MyConstant.testUrl3));
        MediaSource mediaSource4 = new ProgressiveMediaSource.Factory(cacheDataSourceFactory)
                .createMediaSource(Uri.parse(MyConstant.testUrl4));
        MediaSource mediaSource5 = new ProgressiveMediaSource.Factory(cacheDataSourceFactory)
                .createMediaSource(Uri.parse(MyConstant.testUrl5));
        MediaSource mediaSource6 = new ProgressiveMediaSource.Factory(cacheDataSourceFactory)
                .createMediaSource(Uri.parse(MyConstant.testUrl6));
        mSources.add(mediaSource);
        mSources.add(mediaSource2);
        mSources.add(mediaSource3);
        mSources.add(mediaSource4);
        mSources.add(mediaSource5);
        mSources.add(mediaSource6);
        mListener.onVideoLoad(aaa,mSources);
        }


    // 定义一个视频加载的监听器接口，在获得响应之后回调onVideoLoad方法
    public interface VideoLoadListener {
        void onVideoLoad(List<VideoInfo> videoList,List<MediaSource> mediaSources);
    }

}
