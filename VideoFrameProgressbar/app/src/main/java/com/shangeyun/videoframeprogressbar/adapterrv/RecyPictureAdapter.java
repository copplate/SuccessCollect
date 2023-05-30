package com.shangeyun.videoframeprogressbar.adapterrv;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shangeyun.videoframeprogressbar.R;

import java.util.List;

/**
 * 通用主页
 * */
public class RecyPictureAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<String> coverPaths;

    public RecyPictureAdapter(Context context, List<String> coverPaths) {
        this.context = context;
        this.coverPaths = coverPaths;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video_cover_recy, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        ImageView iv = holder.itemView.findViewById(R.id.iv_cover);
//        iv.setImageResource(R.mipmap.yezishu);
        iv.setImageURI(Uri.parse(coverPaths.get(position)));
    }


    @Override
    public int getItemCount() {
        return coverPaths.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_cover);
        }


    }
}
