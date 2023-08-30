package com.example.recyandexomy;

import android.content.Context;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerPool {
    public static PlayerPool playerPool = null;
    private static List<SimpleExoPlayer> playerList = new ArrayList<>();

    public static List<SimpleExoPlayer> getPlayerList(Context context) {
        if (playerPool == null) {
            playerPool = new PlayerPool();
            for (int i = 0; i < 3; i++) {
                //1. 自定义 DefaultLoadControl 参数
                int MIN_BUFFER_MS = 1_000; // 最小缓冲时间
                int MAX_BUFFER_MS = 3_000; // 最大缓冲时间
                int PLAYBACK_BUFFER_MS = 300; // 最小播放缓冲时间，只有缓冲到达这个时间后才是可播放状态
                //666,从DEFAULT_BUFFER_FOR_PLAYBACK_MS = 2500改到700
                int REBUFFER_MS = 1_000; // 当缓冲用完，再次缓冲的时间
                DefaultLoadControl loadControl = new DefaultLoadControl.Builder()
                        .setPrioritizeTimeOverSizeThresholds(true)//缓冲时时间优先级高于大小
                        .setBufferDurationsMs(MIN_BUFFER_MS, MAX_BUFFER_MS, PLAYBACK_BUFFER_MS, REBUFFER_MS)
                        .build();
                SimpleExoPlayer player = new SimpleExoPlayer.Builder(context)
                        .setLoadControl(loadControl)
                        .build();
                playerList.add(player);
            }
        }

        return playerList;
    }

}
