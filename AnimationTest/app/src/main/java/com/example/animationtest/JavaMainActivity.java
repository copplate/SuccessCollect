package com.example.animationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.example.animationtest.databinding.ActivityJavaMainBinding;

public class JavaMainActivity extends AppCompatActivity {
    private ActivityJavaMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_java_main);


        // 创建一个缩放动画，从 0% 缩放到 100%
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(binding.ivIcon, View.SCALE_X, 0f, 1f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(binding.ivIcon, View.SCALE_Y, 0f, 1f);

        // 设置动画持续时间
        scaleXAnimator.setDuration(2000);
        scaleYAnimator.setDuration(2000);

        scaleXAnimator.start();
        scaleYAnimator.start();
        binding.btnScale.setOnClickListener(v -> {
            // 启动动画
            scaleXAnimator.start();
            scaleYAnimator.start();
        });

        // 创建旋转动画，绕 Z 轴旋转
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(binding.ivIcon, "rotation", 0f, 360f);

        // 设置动画持续时间
        rotationAnimator.setDuration(2000);

        // 设置插值器，使动画匀速旋转
        rotationAnimator.setInterpolator(new LinearInterpolator());


        binding.btnZRotate.setOnClickListener(v -> {
            // 启动动画
            rotationAnimator.start();
        });

        binding.btnUpToDown.setOnClickListener(v -> {
            Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            binding.ivIcon.startAnimation(anim1);
        });
        binding.btnFadeIn.setOnClickListener(v -> {
            Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.fade_in_text);
            binding.tvSlogan.startAnimation(anim1);

        });
    }
}