package com.example.diffutiltest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diffutiltest.R;
import com.example.diffutiltest.bean.Item;
import com.example.diffutiltest.callback.ItemDiffCallback;
import com.example.diffutiltest.callback.ItemDiffCallbackChange;

import java.util.List;
/**
 * 不按照bean的形式来写
 * */
public class ItemAdapterChange extends RecyclerView.Adapter<ItemAdapterChange.ItemViewHolder> {

    private List<String> itemList;

    public ItemAdapterChange(List<String> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rlv, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateItems(List<String> newItems) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ItemDiffCallbackChange(itemList, newItems));
        itemList.clear();
        itemList.addAll(newItems);
        diffResult.dispatchUpdatesTo(this);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.r_name);
        }

        public void bind(String item) {
            nameTextView.setText(item);
        }
    }
}
