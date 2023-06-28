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

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;

    public ItemAdapter(List<Item> itemList) {
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
        Item item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateItems(List<Item> newItems) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ItemDiffCallback(itemList, newItems));
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

        public void bind(Item item) {
            nameTextView.setText(item.getName());
        }
    }
}
