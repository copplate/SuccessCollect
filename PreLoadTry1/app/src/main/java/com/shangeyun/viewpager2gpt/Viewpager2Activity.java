package com.shangeyun.viewpager2gpt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.shangeyun.viewpager2gpt.bean.VideoInfo;
import com.shangeyun.viewpager2gpt.constant.MyConstant;
import com.shangeyun.viewpager2gpt.fragment.VideoFragment;
import com.shangeyun.viewpager2gpt.fragmentadapter.ViewPager2Adapter;

import java.util.ArrayList;
import java.util.List;

public class Viewpager2Activity extends AppCompatActivity {
    private ViewPager2 vp2;
    private List<VideoInfo> mVideoList = new ArrayList<>(); // 声明一个地址列表


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager2);
        vp2 = findViewById(R.id.vp2);
        List<VideoFragment> fragmentList = new ArrayList<>();

        /*BlankFragment blankFragment = new BlankFragment();
        BlankFragment blankFragment1 = new BlankFragment();
        BlankFragment blankFragment2 = new BlankFragment();
        BlankFragment blankFragment3 = new BlankFragment();
        fragmentList.add(blankFragment);
        fragmentList.add(blankFragment1);
        fragmentList.add(blankFragment2);
        fragmentList.add(blankFragment3);*/

        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setVideo(MyConstant.testUrl1);
        VideoFragment videoFragment = VideoFragment.newInstance(0, videoInfo);
        fragmentList.add(videoFragment);

        VideoInfo videoInfo2 = new VideoInfo();
        videoInfo2.setVideo(MyConstant.testUrl2);
        VideoFragment videoFragment2 = VideoFragment.newInstance(0, videoInfo2);
        fragmentList.add(videoFragment2);

        VideoInfo videoInfo3 = new VideoInfo();
        videoInfo3.setVideo(MyConstant.testUrl3);
        VideoFragment videoFragment3 = VideoFragment.newInstance(0, videoInfo3);
        fragmentList.add(videoFragment3);



        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this, fragmentList);
        vp2.setAdapter(viewPager2Adapter);


    }
}