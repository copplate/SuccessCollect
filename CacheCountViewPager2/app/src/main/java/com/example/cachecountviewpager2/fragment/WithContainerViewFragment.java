package com.example.cachecountviewpager2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cachecountviewpager2.R;


public class WithContainerViewFragment extends Fragment {

    private FragmentTransaction fragmentTransaction;
    private Button btnReplace;
    private int count = 1;



    public WithContainerViewFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("tiktok", "onCreateView: -----WithContainerViewFragment------");
        return inflater.inflate(R.layout.fragment_with_container_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnReplace = view.findViewById(R.id.btn_replace);
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        InContainerFragment inContainerFragment = new InContainerFragment(100);
        fragmentTransaction.add(R.id.fcv, inContainerFragment);
        fragmentTransaction.commit();
        Log.d("tiktok", "onCreateView: -----WithContainerViewFragment------");

        btnReplace.setOnClickListener(v ->{
            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            InContainerFragment inContainerFragment2 = new InContainerFragment(100 + count);
            count++;
            fragmentTransaction.replace(R.id.fcv, inContainerFragment2).commit();
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("tiktok", "onStop: -----WithContainerViewFragment------");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("tiktok", "onResume: -------WithContainerViewFragment------");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("tiktok", "onDestroyView: -------WithContainerViewFragment-------");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("tiktok", "onPause: --------WithContainerViewFragment------");
    }
}