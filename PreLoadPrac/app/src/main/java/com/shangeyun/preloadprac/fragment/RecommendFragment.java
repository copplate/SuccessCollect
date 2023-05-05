package com.shangeyun.preloadprac.fragment;

import static androidx.viewpager2.widget.ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.shangeyun.preloadprac.Adapter.VideoPagerAdapter;
import com.shangeyun.preloadprac.PreloadActivity;
import com.shangeyun.preloadprac.R;
import com.shangeyun.preloadprac.bean.VideoInfo;
import com.shangeyun.preloadprac.task.VideoLoadTask;
import com.shangeyun.preloadprac.util.MyConstant;


import java.util.ArrayList;
import java.util.List;

/**
 * 里面有一个上下滑动的vp2,分开fragment看能不能保证页面切换暂停播放
 * */
public class RecommendFragment extends Fragment {
    private List<VideoInfo> mVideoList = new ArrayList<>(); // 声明一个地址列表
    private VideoPagerAdapter mAdapter; // 声明一个视频翻页适配器对象
    private ViewPager2 vp2_content;
    private List<VideoFragment> fragments = new ArrayList<>();// 声明一个fragment列表
    private List<SimpleExoPlayer> playerList = new ArrayList<>();
    private List<SimpleExoPlayer> playerList2 = new ArrayList<>();
    private List<String> urlList;
    private Context mContext;


//    private VideoFragment.Messanger ms;

    public RecommendFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        vp2_content = view.findViewById(R.id.vp2_content);
        vp2_content.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT_DEFAULT);
        mContext = getActivity();
        SimpleExoPlayer mPlayer = new SimpleExoPlayer.Builder(getActivity()).build();
        SimpleExoPlayer mPlayer2 = new SimpleExoPlayer.Builder(getActivity()).build();
        SimpleExoPlayer mPlayer3 = new SimpleExoPlayer.Builder(getActivity()).build();
        vp2_content.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                /*Log.d("ANR", "onPageScrolled: ------position----" + position);
                Log.d("ANR", "onPageScrolled: ------positionOffset----" + positionOffset);
                Log.d("ANR", "onPageScrolled: ------positionOffsetPixels----" + positionOffsetPixels);*/
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("ANR", "onPageSelected: -----position-----" + position);
                if (position == 11) {
                    SimpleExoPlayer mPlayer = null;
                    SimpleExoPlayer mPlayer2 = null;
                    SimpleExoPlayer mPlayer3 = null;
                    SimpleExoPlayer mPlayer4 = null;
                    SimpleExoPlayer mPlayer5 = null;
                    SimpleExoPlayer mPlayer6 = null;
                    mPlayer = new SimpleExoPlayer.Builder(mContext).build();
                    mPlayer2 = new SimpleExoPlayer.Builder(mContext).build();
                    mPlayer3 = new SimpleExoPlayer.Builder(mContext).build();
                    mPlayer4 = new SimpleExoPlayer.Builder(mContext).build();
                    mPlayer5 = new SimpleExoPlayer.Builder(mContext).build();
                    mPlayer6 = new SimpleExoPlayer.Builder(mContext).build();
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
                    for (int i = 0; i < 5; i++) {
                        prepareVideo(tempList.get(i),playerList.get(i));
                    }
                }
                /*if (position > 0) {
                    SimpleExoPlayer tempPlayer = fragments.get(position - 1).getPlayer();
                    if (tempPlayer.isPlaying()) {
                        tempPlayer.pause();
                    }
                    mPlayer.release();
                    mPlayer2.release();
                    mPlayer3.release();
                    prepareVideo(MyConstant.testUrl1,mPlayer);
                    prepareVideo(MyConstant.testUrl2,mPlayer2);
                    prepareVideo(MyConstant.testUrl3,mPlayer3);
                    fragments.get(position - 1).setPlayer(mPlayer);
                    fragments.get(position).setPlayer(mPlayer2);
                    fragments.get(position + 1).setPlayer(mPlayer3);
                }else{
                    prepareVideo(MyConstant.testUrl1,mPlayer);
                    prepareVideo(MyConstant.testUrl2,mPlayer2);
                    fragments.get(0).setPlayer(mPlayer);
                    fragments.get(1).setPlayer(mPlayer2);
                }*/

            }
        });
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                startLoad();
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                VideoInfo videoInfo = new VideoInfo();
                VideoInfo videoInfo2 = new VideoInfo();
                VideoInfo videoInfo3 = new VideoInfo();
                VideoInfo videoInfo4 = new VideoInfo();
                VideoInfo videoInfo5 = new VideoInfo();
                VideoInfo videoInfo6 = new VideoInfo();

                SimpleExoPlayer mPlayer = null;
                SimpleExoPlayer mPlayer2 = null;
                SimpleExoPlayer mPlayer3 = null;
                SimpleExoPlayer mPlayer4 = null;
                SimpleExoPlayer mPlayer5 = null;
                SimpleExoPlayer mPlayer6 = null;
                mPlayer = new SimpleExoPlayer.Builder(mContext).build();
                mPlayer2 = new SimpleExoPlayer.Builder(mContext).build();
                mPlayer3 = new SimpleExoPlayer.Builder(mContext).build();
                mPlayer4 = new SimpleExoPlayer.Builder(mContext).build();
                mPlayer5 = new SimpleExoPlayer.Builder(mContext).build();
                mPlayer6 = new SimpleExoPlayer.Builder(mContext).build();
                /*playerList.add(mPlayer);
                playerList.add(mPlayer2);
                playerList.add(mPlayer3);
                playerList.add(mPlayer4);
                playerList.add(mPlayer5);
                playerList.add(mPlayer6);*/
                playerList.addAll(((PreloadActivity) (getActivity())).getPlayerList());
                /*List<String> tempList = new ArrayList<>();
                tempList.add(MyConstant.testUrl6);
                tempList.add(MyConstant.testUrl2);
                tempList.add(MyConstant.testUrl3);
                tempList.add(MyConstant.testUrl4);
                tempList.add(MyConstant.testUrl5);
                tempList.add(MyConstant.testUrl6);
                for (int i = 0; i < 5; i++) {
                    prepareVideo(tempList.get(i),playerList.get(i));
                }*/
                videoInfo.setVideo(MyConstant.testUrl1);
                videoInfo.setAddress("fjsahfjas");
                videoInfo.setDate("fjsahfjas");
                videoInfo.setDesc("fjsahfjas");
                List<VideoInfo> aaa = new ArrayList<>();
                VideoFragment videoFragment = VideoFragment.newInstance(0, videoInfo);
                videoFragment.setPlayer(playerList.get(0));
                fragments.add(videoFragment);
                aaa.add(videoInfo);
                VideoFragment videoFragment2 = VideoFragment.newInstance(0, videoInfo2);
                videoFragment2.setPlayer(playerList.get(1));
                fragments.add(videoFragment2);
                videoInfo2.setVideo(MyConstant.testUrl2);
                aaa.add(videoInfo2);
                videoInfo3.setVideo(MyConstant.testUrl3);
                VideoFragment videoFragment3 = VideoFragment.newInstance(0, videoInfo3);
                videoFragment3.setPlayer(playerList.get(2));
                fragments.add(videoFragment3);
                aaa.add(videoInfo3);
                videoInfo4.setVideo(MyConstant.testUrl4);
                VideoFragment videoFragment4 = VideoFragment.newInstance(0, videoInfo4);
                videoFragment4.setPlayer(playerList.get(3));
                fragments.add(videoFragment4);
                aaa.add(videoInfo4);
                videoInfo5.setVideo(MyConstant.testUrl5);
                VideoFragment videoFragment5 = VideoFragment.newInstance(0, videoInfo5);
                videoFragment5.setPlayer(playerList.get(4));
                fragments.add(videoFragment5);
                aaa.add(videoInfo5);
                videoInfo6.setVideo(MyConstant.testUrl6);
                aaa.add(videoInfo6);
                VideoFragment videoFragment6 = VideoFragment.newInstance(0, videoInfo6);
                videoFragment6.setPlayer(playerList.get(5));
                fragments.add(videoFragment6);
                mVideoList.addAll(aaa);
                mAdapter.notifyDataSetChanged(); // 通知适配器数据发生变化
                refreshlayout.finishLoadMore(2000);//传入false表示加载失败
            }
        });
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // 保持屏幕常亮
        initView(); // 初始化视图
        startLoad(); // 开始加载短视频
    }

    // 初始化视图
    private void initView() {
//        srl_dynamic = findViewById(R.id.srl_dynamic);
//        srl_dynamic.setOnRefreshListener(() -> startLoad()); // 设置下拉布局的下拉刷新监听器
        // 构建一个视频地址的翻页适配器
        mAdapter = new VideoPagerAdapter(getActivity(), mVideoList,fragments);
        vp2_content.setAdapter(mAdapter); // 设置二代翻页视图的适配器

    }

    // 开始加载最新的短视频
    private void startLoad() {
        // 创建一个视频列表加载任务
        VideoLoadTask task = new VideoLoadTask(getActivity(),"veryVeryGood", (videoList,fragmentList) -> {
//            srl_dynamic.setRefreshing(false); // 结束下拉刷新布局的刷新动作
            mVideoList.clear();
            fragments.clear();
            mVideoList.addAll(videoList);
            fragments.addAll(fragmentList);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged(); // 通知适配器数据发生变化
                }
            });
        });

        Log.d("tiktok", "startLoad: -------------" + task.getName());
        task.start(); // 启动短视频列表加载任务
/*
        new VideoLoadTask(getActivity(), "qwer", new VideoLoadTask.VideoLoadListener() {
            @Override
            public void onVideoLoad(List<VideoInfo> videoList, List<VideoFragment> fragmentList) {

            }
        });
*/
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("tiktok", "onPause: ------RecommendFragment-----");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.d("tiktok", "setUserVisibleHint: ------RecommendFragment-----");
        }
    }
    private void prepareVideo(String videoUrl, SimpleExoPlayer mPlayer) {
//        mPlayer.release();
        // 创建HTTP在线视频的工厂对象
        DataSource.Factory factory = new DefaultHttpDataSourceFactory(Util.getUserAgent(getActivity(), "okook"),
                new DefaultBandwidthMeter.Builder(getActivity()).build());
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