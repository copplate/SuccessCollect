package com.example.recyandexomy;

import static android.widget.NumberPicker.OnScrollListener.SCROLL_STATE_IDLE;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_SETTLING;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.recyandexomy.adapter.CustomSGLayoutManager;
import com.example.recyandexomy.adapter.RecyExoAdapter;

import java.util.ArrayList;
import java.util.List;

public class JavaMainActivity extends AppCompatActivity {
    private RecyclerView rlv;
    private List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);
        rlv = findViewById(R.id.rlv);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        CustomSGLayoutManager customSGLayoutManager = new CustomSGLayoutManager(this);
        rlv.setLayoutManager(customSGLayoutManager);
        List<Integer> arrs = new ArrayList<>();
        urls.add("http://124.132.142.251:83/video/96204e4cd3b744938bb5265cc6ac697c.mp4");
        urls.add("http://124.132.142.251:83/video/8419302b9f0b49839a15cb64278e28dd.mp4");
        urls.add("http://124.132.142.251:83/video/5d47b3f23a27454cabeedf32d0ad17bc.mp4");
        urls.add("http://124.132.142.251:83/video/66d11b9fcb654d0991f995b11000aba1.mp4");
        urls.add("http://124.132.142.251:83/video/07fc2bc7beee45bd8da33d86cb466087.mp4");
        urls.add("http://124.132.142.251:83/video/19f3674cf29647028afdc9c7b33de135.mp4");
        urls.add("http://124.132.142.251:83/video/6661e4c8747e4579a4a578f94e71a756.mp4");
        urls.add("http://124.132.142.251:83/video/3e01bc2e870846ab85fd03545f12c845.mp4");
        urls.add("http://124.132.142.251:83/video/b4799d6c425f4e4b8646213f579d81ed.mp4");
        for (int i = 0; i <60 ; i++) {
            arrs.add(i);
        }
        RecyExoAdapter recyExoAdapter = new RecyExoAdapter(this,arrs,urls);
        rlv.setAdapter(recyExoAdapter);
        rlv.setItemViewCacheSize(0);
        rlv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case SCROLL_STATE_IDLE: //滚动停止
                        Log.d("tiktok", "onScrollStateChanged: --FirstCompletelyVisibleItemPosition--" + customSGLayoutManager.findFirstCompletelyVisibleItemPosition());
//                        Log.d("tiktok", "onScrollStateChanged: --LastVisibleItemPosition--" + customSGLayoutManager.findLastVisibleItemPosition());
                        Log.d("tiktok", "onScrollStateChanged: --LastCompletelyVisibleItemPosition--" + customSGLayoutManager.findLastCompletelyVisibleItemPosition());
                        /*if (customSGLayoutManager.findFirstVisibleItemPosition() > 0) {
                            View child = rlv.getChildAt(customSGLayoutManager.findFirstVisibleItemPosition() - 1);
                            RecyExoAdapter.ViewHolder childViewHolder = (RecyExoAdapter.ViewHolder)rlv.getChildViewHolder(child);
                            childViewHolder.mPlayer.pause();

                        }*/
                        break;
                    case SCROLL_STATE_DRAGGING: //手指拖动
                        break;
                    case SCROLL_STATE_SETTLING: //惯性滚动
                        break;
                }

            }
        });
        rlv.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                return false;
            }
        });
        rlv.setRecyclerListener(new RecyclerView.RecyclerListener() {
            @Override
            public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {

            }
        });
        rlv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                TextView tvUsername = view.findViewById(R.id.tv_username);
                String s = tvUsername.getText().toString();
                Log.d("tiktok", "onChildViewAttachedToWindow: ---------" + s);
            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                TextView tvUsername = view.findViewById(R.id.tv_username);
                String s = tvUsername.getText().toString();
                Log.d("tiktok", "onChildViewDetachedFromWindow: ---------" + s);
                RecyExoAdapter.ViewHolder childViewHolder = (RecyExoAdapter.ViewHolder)rlv.getChildViewHolder(view);
                childViewHolder.mPlayer.pause();
            }
        });



    }
}