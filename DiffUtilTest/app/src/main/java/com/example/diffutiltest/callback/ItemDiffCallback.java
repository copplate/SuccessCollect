package com.example.diffutiltest.callback;

import androidx.recyclerview.widget.DiffUtil;

import com.example.diffutiltest.bean.Item;

import java.util.List;

public class ItemDiffCallback extends DiffUtil.Callback {

    private List<Item> oldList;
    private List<Item> newList;

    public ItemDiffCallback(List<Item> oldList, List<Item> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Item oldItem = oldList.get(oldItemPosition);
        Item newItem = newList.get(newItemPosition);
        return oldItem.getName().equals(newItem.getName());
    }
}
