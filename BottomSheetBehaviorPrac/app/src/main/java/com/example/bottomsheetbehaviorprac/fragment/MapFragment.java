package com.example.bottomsheetbehaviorprac.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.bottomsheetbehaviorprac.R;
import com.example.bottomsheetbehaviorprac.adapterrv.SearchShopHintAdapter;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment {
    private List<String> testList = new ArrayList<>();
    private RecyclerView rlvBottom;
    private Button btnShow;
    private LinearLayout llBottom;
    private boolean isLinearLayoutVisible = true;
//    private int initialPeekHeight;

    public MapFragment() {
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
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rlvBottom = view.findViewById(R.id.rlv_mddialog_bottom);
        llBottom = view.findViewById(R.id.ll_mddialog_bottom);
        btnShow = view.findViewById(R.id.btn_show);
        for (int i = 0; i < 15; i++) {
            testList.add("测试数据" + i);
        }
        SearchShopHintAdapter searchShopHintAdapter = new SearchShopHintAdapter(testList, getActivity());
        rlvBottom.setAdapter(searchShopHintAdapter);
        rlvBottom.setLayoutManager(new LinearLayoutManager(getActivity()));
        btnShow.setOnClickListener(v -> {
            if (isLinearLayoutVisible) {
//                llBottom.setVisibility(View.INVISIBLE);
                hideViewWithAnimation();
                isLinearLayoutVisible = false;

            } else {
                llBottom.setVisibility(View.VISIBLE);
                BottomSheetBehavior<LinearLayout> behavior = BottomSheetBehavior.from(llBottom);
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                animateViewFromBottom();
                isLinearLayoutVisible = true;
            }
        });
//        initialPeekHeight = getActivity().getWindow().getDecorView().getHeight();
        BottomSheetBehavior<LinearLayout> behavior = BottomSheetBehavior.from(llBottom);
//        behavior.setExpandedOffset(15);//不好使
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    isLinearLayoutVisible = false;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }

    private void animateViewFromBottom() {
        // 创建动画，让 View 从底部渐渐升起
        ObjectAnimator animator = ObjectAnimator.ofFloat(llBottom, "translationY", getActivity().getWindow().getDecorView().getHeight(), 0);
        /*ObjectAnimator animator = ObjectAnimator.ofFloat(llBottom, "translationY",
                initialPeekHeight, 0);//initialPeekHeight一直是0*/
        animator.setDuration(300); // 设置动画持续时间
        animator.setInterpolator(new AccelerateDecelerateInterpolator()); // 设置动画插值器
        animator.start(); // 开始动画
    }

    public void hideViewWithAnimation() {
        llBottom.animate()
                .translationY(llBottom.getHeight())
                .setDuration(500)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        llBottom.setVisibility(View.INVISIBLE);

                    }
                })
                .start();
    }


}