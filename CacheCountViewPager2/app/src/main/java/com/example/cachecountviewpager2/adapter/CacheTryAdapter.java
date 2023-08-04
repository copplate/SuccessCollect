package com.example.cachecountviewpager2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cachecountviewpager2.fragment.WithNumberFragmentBase;
import com.example.cachecountviewpager2.fragment.WithVp2Fragment;

public class CacheTryAdapter extends FragmentStateAdapter {
    public CacheTryAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new WithVp2Fragment();
        } else {
            return new WithNumberFragmentBase(position);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }


}
