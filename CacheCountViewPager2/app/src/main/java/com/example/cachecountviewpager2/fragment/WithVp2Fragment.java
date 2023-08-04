package com.example.cachecountviewpager2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cachecountviewpager2.R;
import com.example.cachecountviewpager2.adapter.WithVp2FragmentAdapter;

import java.util.ArrayList;
import java.util.List;


public class WithVp2Fragment extends Fragment {
    private ViewPager2 vp2;
    private WithContainerViewFragment withContainerViewFragment;
    private WithNumberFragment withVp2Fragment1;
    private WithNumberFragment withVp2Fragment2;
    private WithNumberFragment withVp2Fragment3;


    public WithVp2Fragment() {
        // Required empty public constructor
        Log.d("ANR", "WithVp2Fragment: ------constructor-----");
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("tiktok", "onCreateView: -----WithVp2Fragment-----");
        return inflater.inflate(R.layout.fragment_with_vp2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("tiktok", "onViewCreated: -----WithVp2Fragment-----");
        vp2 = view.findViewById(R.id.vp2);
        withContainerViewFragment = new WithContainerViewFragment();
        withVp2Fragment1 = new WithNumberFragment(1, "WithVp2Fragment");
        withVp2Fragment2 = new WithNumberFragment(2, "WithVp2Fragment");
        withVp2Fragment3 = new WithNumberFragment(3, "WithVp2Fragment");
//        getViewLifecycleOwner().getLifecycle().addObserver(withContainerViewFragment);
        getViewLifecycleOwner().getLifecycle().addObserver(withVp2Fragment1);
        getViewLifecycleOwner().getLifecycle().addObserver(withVp2Fragment2);
        getViewLifecycleOwner().getLifecycle().addObserver(withVp2Fragment3);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(withContainerViewFragment);
        fragmentList.add(withVp2Fragment1);
        fragmentList.add(withVp2Fragment2);
        fragmentList.add(withVp2Fragment3);
        WithVp2FragmentAdapter withVp2FragmentAdapter = new WithVp2FragmentAdapter(getActivity(),fragmentList);
        vp2.setAdapter(withVp2FragmentAdapter);


    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d("tiktok", "onStop: -----WithVp2Fragment------");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("tiktok", "onResume: ------WithVp2Fragment-------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("tiktok", "onDestroyView: -------WithVp2Fragment-------");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("tiktok", "onPause: -------WithVp2Fragment-------");
        int currentItem = vp2.getCurrentItem();
        /*switch (currentItem) {
            case 0:
                withVp2Fragment0.onPause();
                break;
            case 1:
                withVp2Fragment1.onPause();
                break;
            case 2:
                withVp2Fragment2.onPause();
                break;
            case 3:
                withVp2Fragment3.onPause();
                break;
        }*/
    }
}