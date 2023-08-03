package com.example.cachecountviewpager2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cachecountviewpager2.WithNumberFragment;
import com.example.cachecountviewpager2.WithVp2Fragment;

import java.util.List;

public class WithVp2FragmentAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;

    public WithVp2FragmentAdapter(@NonNull FragmentActivity fragmentActivity,List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList == null ? null : fragmentList.get(position);

    }

    @Override
    public int getItemCount() {
        return fragmentList == null ?0: fragmentList.size();
    }


}
