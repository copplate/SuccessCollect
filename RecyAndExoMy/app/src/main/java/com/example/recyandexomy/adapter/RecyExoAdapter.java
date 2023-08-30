package com.example.recyandexomy.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyandexomy.PlayerPool;
import com.example.recyandexomy.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;

public class RecyExoAdapter extends RecyclerView.Adapter<RecyExoAdapter.ViewHolder> {
    private Context context;
    List<Integer> numbers;
    private List<String> urls;

    public RecyExoAdapter(Context context, List<Integer> numbers,List<String> urls) {
        this.context = context;
        this.numbers = numbers;
        this.urls = urls;
    }

    @NonNull
    @Override
    public RecyExoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recy_exo, parent, false);
        ViewHolder holder = new ViewHolder(view);
//        Log.d("tiktok", "onCreateViewHolder: ---------" + holder.getAdapterPosition());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvUsername.setText(position + "");
        Log.d("tiktok", "onBindViewHolder: ------------" + holder.getAdapterPosition());
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
//        if (holder.mPlayer == null)
        {//别让他空了，每次都要刷新
            /*holder.mPlayer = new SimpleExoPlayer.Builder(context)
                    .setLoadControl(loadControl)
                    .build();*/
            List<SimpleExoPlayer> playerList = PlayerPool.getPlayerList(context);
//            holder.mPlayer = playerList.get(position / 3);
            int size = urls.size();
            String url = urls.get(position % size);
            holder.prepareVideo(url); // 准备在线视频o
//            prepareVideo(mVideoInfo.getVideo()); // 准备在线视频o
        }
        holder.pvContent.setPlayer(holder.mPlayer);

    }

    @Override
    public int getItemCount() {
        return numbers == null?0:numbers.size();
    }


    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
        Log.d("tiktok", "onViewRecycled: ----------------" + holder.getAdapterPosition());

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        PlayerView pvContent;
        public SimpleExoPlayer mPlayer; //新型播放器对象



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            pvContent = itemView.findViewById(R.id.pv_content);
            mPlayer = new SimpleExoPlayer.Builder(context)
                    .build();
        }
        private void prepareVideo(String videoUrl) {
            // 创建HTTP在线视频的工厂对象
            DataSource.Factory factory = new DefaultHttpDataSourceFactory(Util.getUserAgent(context, context.getString(R.string.app_name)),
                    new DefaultBandwidthMeter.Builder(context).build());
            // 创建指定地址的媒体对象
            MediaItem videoItem = new MediaItem.Builder().setUri(Uri.parse(videoUrl)).build();
            // 基于工厂对象和媒体对象创建媒体来源
            MediaSource videoSource = new ProgressiveMediaSource.Factory(factory).createMediaSource(videoItem);
            mPlayer.setMediaSource(videoSource); // 设置播放器的媒体来源
            // 设置播放器的重播模式，REPEAT_MODE_ALL表示反复重播
            mPlayer.setRepeatMode(SimpleExoPlayer.REPEAT_MODE_ALL);
            mPlayer.prepare(); // 播放器准备就绪
        }

    }

}
