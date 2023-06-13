package com.shangeyun.spinnerprac1.adaptervp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

//关注与推荐那个左右滑动的vp2的适配器
public class HomePageAdapter extends FragmentStateAdapter {
    private List<Fragment> mFragmentList;

    public HomePageAdapter(@NonNull Fragment fragment, List<Fragment> mFragmentList) {
        super(fragment);
        this.mFragmentList = mFragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragmentList == null ? null : mFragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragmentList == null ? 0 : mFragmentList.size();
    }
}
