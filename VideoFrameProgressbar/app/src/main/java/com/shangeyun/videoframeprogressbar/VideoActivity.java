package com.shangeyun.videoframeprogressbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioListener;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.shangeyun.videoframeprogressbar.adapterrv.RecyPictureAdapter;
import com.shangeyun.videoframeprogressbar.customview.MyRecyclerView;
import com.shangeyun.videoframeprogressbar.util.MediaUtil;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class VideoActivity extends AppCompatActivity {
    private PlayerView mPlayerView;
    private Button btnSet;
    private MyRecyclerView rlv;
    private HorizontalScrollView hsv;
    private int toSeekPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mPlayerView = findViewById(R.id.styledPlayerView);
        btnSet = findViewById(R.id.btn_set);
        rlv = findViewById(R.id.rlv);
        hsv = findViewById(R.id.hsv);
        try {
            FileOutputStream output = openFileOutput("data", Context.MODE_PRIVATE);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(output));
            bufferedWriter.write("nihaoa");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file =
                new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES) + "/movie/temp.mp4");

        BufferedOutputStream bos;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bytes = new byte[3];
            bytes[0] = 'a';
            bytes[1] = 'a';
            bytes[2] = 'a';
            byte[] bytes1 = {'a', 'b', 'c'};
            bos.write(new byte[]{2, 3, 6});
            bos.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
        SimpleCache simpleCache = new SimpleCache(new File(getCacheDir(), "media"), new NoOpCacheEvictor());
        CacheDataSourceFactory cacheDataSourceFactory = new CacheDataSourceFactory(
                simpleCache,
                new DefaultHttpDataSource.Factory(),
                CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR);
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(cacheDataSourceFactory)
//                .createMediaSource(Uri.parse(getExternalFilesDir(Environment.DIRECTORY_MOVIES) + "beauty.mp4"));
                .createMediaSource(Uri.parse("http://192.168.3.234:8080/nianan2_war/football3"));
        SimpleExoPlayer exoPlayer = new SimpleExoPlayer.Builder(this).build();
        exoPlayer.setMediaSource(mediaSource);
        exoPlayer.prepare();
        exoPlayer.addAnalyticsListener(new AnalyticsListener() {
            @Override
            public void onLoadCompleted(EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                AnalyticsListener.super.onLoadCompleted(eventTime, loadEventInfo, mediaLoadData);

            }
        });
        exoPlayer.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                Player.Listener.super.onPlaybackStateChanged(state);
                if (state == Player.STATE_READY) {
                    Log.d("ANR", "onPlaybackStateChanged: ----------");
                    //当前player已经准备好了，可以开始准备下一个player了
//                    prepareNextPlayer();
                }
            }
        });
        exoPlayer.setPlayWhenReady(true);
        mPlayerView.setPlayer(exoPlayer);
//        exoPlayer.setSeekParameters();
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoPlayer.seekTo(5000);
                Log.d("tiktok", "onClick: ------" + getExternalFilesDir(Environment.DIRECTORY_MOVIES) + "/movie/temp.mp4");
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rlv.setLayoutManager(linearLayoutManager);

        hsv.setOnScrollChangeListener(new View.OnScrollChangeListener() {


            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                long duration = exoPlayer.getDuration();
                long contentDuration = exoPlayer.getContentDuration();
                Log.d("tiktok", "onCreate: ----------" + contentDuration + "duration" + duration);
                /*Log.d("tiktok", "onScrollChange: -----scrollX----" + scrollX);
                Log.d("tiktok", "onScrollChange: -----oldScrollX----" + oldScrollX);*/
                int viewWidth = hsv.getWidth();
                int maxScrollX = hsv.getChildAt(0).getMeasuredWidth() - viewWidth;
                float progress = (float) scrollX / maxScrollX;
                Log.d("tiktok", "滑动进度：" + progress);
                float v1 = duration * progress;
                Log.d("tiktok", "onScrollChange: ----duration--" + duration);
                int round = Math.round(v1);
                Log.d("tiktok", "onScrollChange: --------v1----" + v1);
                Log.d("tiktok", "onScrollChange: --------round----" + round);
                toSeekPosition = round;
                exoPlayer.seekTo(round);
            }
        });

        hsv.scrollTo(0,0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String mVideoPath = getExternalFilesDir(Environment.DIRECTORY_MOVIES) + "/football3.mp4";
                // 获取视频文件中的图片帧列表
                List<String> pathList = MediaUtil.getFrameList(VideoActivity.this, Uri.parse(mVideoPath), 0, 15);
                if (pathList != null) {
                    Log.d("tiktok", "run: ----------------" + pathList.get(0));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            RecyPictureAdapter recyPictureAdapter =
                                    new RecyPictureAdapter(VideoActivity.this,pathList);
                            rlv.setAdapter(recyPictureAdapter);
                        }
                    });


                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                float f1 = exoPlayer.getCurrentPosition();
//                                toSeekPosition = round;
                                float f2 = exoPlayer.getDuration();
                                float f3 = f1 / f2;
                                int scrollX = (int) (hsv.getChildAt(0).getWidth() * f3); // 获取HorizontalScrollView的子View的宽度的一半
//                                hsv.scrollTo(scrollX, 0); // 将HorizontalScrollView滚动到指定位置
                                Log.d("ANR", "run: -----f1------" + f1);
                                Log.d("ANR", "run: ------f2-----" + f2);
                                Log.d("ANR", "run: ------f3-----" + f3);
                            }
                        });
                    }
                },600,100);
            }
        }).start();

        /*exoPlayer.addListener(new Player.Listener() {


            @Override
            public void onTimelineChanged(Timeline timeline, int reason) {
                Player.Listener.super.onTimelineChanged(timeline, reason);
                Log.d("ANR", "onTimelineChanged: ------------" + timeline.toString());
            }

            @Override
            public void onPositionDiscontinuity(Player.PositionInfo oldPosition, Player.PositionInfo newPosition, int reason) {
                Player.Listener.super.onPositionDiscontinuity(oldPosition, newPosition, reason);
                Log.d("ANR", "onPositionDiscontinuity: ------------" + newPosition.positionMs);
            }
        });*/

    }
}