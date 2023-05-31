package com.shangeyun.preloadprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.shangeyun.preloadprac.fragment.RecommendFragment;

public class PreloadActivity extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private SimpleExoPlayer simpleExoPlayer;
    private SimpleExoPlayer nextSimpleExoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RecommendFragment recommendFragment = new RecommendFragment();
        fragmentTransaction.add(R.id.fcv, recommendFragment);
        fragmentTransaction.commit();


    }

    /*public SimpleExoPlayer getSimpleExoPlayer() {
        return simpleExoPlayer;
    }

    public void setSimpleExoPlayer(SimpleExoPlayer simpleExoPlayer) {
        this.simpleExoPlayer = simpleExoPlayer;
    }*/

    public SimpleExoPlayer getNextSimpleExoPlayer() {
        return nextSimpleExoPlayer;
    }

    public void setNextSimpleExoPlayer(SimpleExoPlayer nextSimpleExoPlayer) {
        this.nextSimpleExoPlayer = nextSimpleExoPlayer;
    }
}