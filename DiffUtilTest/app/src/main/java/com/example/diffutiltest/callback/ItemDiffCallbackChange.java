package com.example.diffutiltest.callback;

import androidx.recyclerview.widget.DiffUtil;

import com.example.diffutiltest.bean.Item;

import java.util.List;

public class ItemDiffCallbackChange extends DiffUtil.Callback {

    private List<String> oldList;
    private List<String> newList;

    public ItemDiffCallbackChange(List<String> oldList, List<String> newList) {
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
        return true;
//        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        String oldItem = oldList.get(oldItemPosition);
        String newItem = newList.get(newItemPosition);
        return oldItem.equals(newItem);
    }
}
