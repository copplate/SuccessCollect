package com.shangeyun.preloadprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.shangeyun.preloadprac.fragment.RecommendFragment;

public class PreloadActivity extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RecommendFragment recommendFragment = new RecommendFragment();
        fragmentTransaction.add(R.id.fcv, recommendFragment);
        fragmentTransaction.commit();


    }
}