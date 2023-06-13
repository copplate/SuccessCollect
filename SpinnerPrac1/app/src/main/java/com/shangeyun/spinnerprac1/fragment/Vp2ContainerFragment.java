package com.shangeyun.spinnerprac1.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shangeyun.spinnerprac1.R;
import com.shangeyun.spinnerprac1.adaptervp.HomePageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *问的gpt，制作点击button后让几个按钮依次弹出的效果
 * */
public class Vp2ContainerFragment extends Fragment {
    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;
    private List<Fragment> mFragmentList;
    private HomePageAdapter mHomePageAdapter;
    Button button1;
    Button button2;
    Button button3;

    public Vp2ContainerFragment() {
        // Required empty public constructor
    }


    public static Vp2ContainerFragment newInstance(String param1, String param2) {
        Vp2ContainerFragment fragment = new Vp2ContainerFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vp2_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager = view.findViewById(R.id.vp_study);
        mTabLayout = view.findViewById(R.id.tab_layout);
        button1 = view.findViewById(R.id.button1);
        button2 = view.findViewById(R.id.button2);
        button3 = view.findViewById(R.id.button3);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        EmptyFragment emptyFragment1 = new EmptyFragment();
        EmptyFragment emptyFragment2 = new EmptyFragment();
        EmptyFragment emptyFragment3 = new EmptyFragment();
        mFragmentList = new ArrayList<>();
        mFragmentList.add(emptyFragment1);
        mFragmentList.add(emptyFragment2);
        mFragmentList.add(emptyFragment3);
        mHomePageAdapter = new HomePageAdapter(this, mFragmentList);
        mViewPager.setAdapter(mHomePageAdapter);
        new TabLayoutMediator(mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("济南");
                        break;
                    case 1:
                        tab.setText("关注");
                        break;
                    case 2:
                        tab.setText("推荐");
                        break;
                }
            }
        }).attach();
        mTabLayout.getTabAt(0).setCustomView(getLayoutInflater().inflate(R.layout.tab_style_layout,
                (ViewGroup) view.findViewById(R.id.tab_layout), false));
        ImageView ivTriangle = mTabLayout.getTabAt(0).view.findViewById(R.id.iv_triangle);
        // 0f -> 360f，从旋转360度，也可以是负值，负值即为逆时针旋转，正值是顺时针旋转。
//        ObjectAnimator anim = ObjectAnimator.ofFloat(ivTriangle, "rotation", 0f, 90f);
        mTabLayout.getTabAt(0).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("tiktok", "onClick: ---------lol-------");
                Animation anim1 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
                Animation anim2 = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_triangle);
                button1.startAnimation(anim1);
                button2.startAnimation(anim1);
                ivTriangle.startAnimation(anim2);
                ivTriangle.setRotation(270);
                anim1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        button1.setVisibility(View.VISIBLE);
                        button2.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Animation anim2 = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
                        button3.startAnimation(anim2);
                        anim2.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                                button3.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

            }
        });
        mTabLayout.getTabAt(1).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                ivTriangle.setRotation(90);
            }
        });



    }
}