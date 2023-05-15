package com.shangeyun.recytantan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shangeyun.recytantan.R;

import java.util.ArrayList;
import java.util.List;

public class MyCardStackAdapter<T> extends RecyclerView.Adapter {

    private Context context;
    private List<String> titleList;

    public MyCardStackAdapter(Context context) {
        this.context = context;
        titleList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                titleList.add("周淑怡");
            } else {
                titleList.add("小米粥");
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recy, parent, false);
        MyCardStackAdapter.MyViewHolder holder = new MyCardStackAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ImageView iv = holder.itemView.findViewById(R.id.imageView);
        TextView tvTitle = holder.itemView.findViewById(R.id.tvTitle);
        tvTitle.setText(titleList.get(position));
        /*if (position % 2 == 0) {
            tvTitle.setText("周淑怡");
        } else {
            tvTitle.setText("小米粥");
        }*/
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tvTitle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }

    public List<String> getTitleList() {
        return titleList;
    }
}
