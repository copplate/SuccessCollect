package com.shangeyun.preloadprac.fragment;

import static androidx.viewpager2.widget.ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT;

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

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.shangeyun.preloadprac.Adapter.VideoPagerAdapter;
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
                videoInfo.setVideo(MyConstant.testUrl1);
                videoInfo.setAddress("fjsahfjas");
                videoInfo.setDate("fjsahfjas");
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
        mAdapter = new VideoPagerAdapter(getActivity(), mVideoList);
        vp2_content.setAdapter(mAdapter); // 设置二代翻页视图的适配器

    }

    // 开始加载最新的短视频
    private void startLoad() {
        // 创建一个视频列表加载任务
        VideoLoadTask task = new VideoLoadTask(getActivity(),"veryVeryGood", videoList -> {
//            srl_dynamic.setRefreshing(false); // 结束下拉刷新布局的刷新动作
            mVideoList.clear();
            mVideoList.addAll(videoList);
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
        new VideoLoadTask(getActivity(), new VideoLoadTask.VideoLoadListener() {
            @Override
            public void onVideoLoad(List<VideoInfo> videoList) {

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

    /*public void setMs(VideoFragment.Messanger ms) {
        this.ms = ms;
    }*/
}