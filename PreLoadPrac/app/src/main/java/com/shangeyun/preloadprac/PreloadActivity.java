package com.shangeyun.preloadprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.shangeyun.preloadprac.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

public class PreloadActivity extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;
    private int temp = 3;
    private List<SimpleExoPlayer> playerList = new ArrayList<>();
    public int haha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RecommendFragment recommendFragment = new RecommendFragment();
        fragmentTransaction.add(R.id.fcv, recommendFragment);
        fragmentTransaction.commit();


    }

    public List<SimpleExoPlayer> getPlayerList() {
        return playerList;
    }
}