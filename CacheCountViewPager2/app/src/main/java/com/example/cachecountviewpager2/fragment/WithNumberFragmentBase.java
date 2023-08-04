package com.example.cachecountviewpager2.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cachecountviewpager2.R;


public class WithNumberFragmentBase extends Fragment {
    private TextView tvNumber;
    private int number;
    private String sign = "";


    public WithNumberFragmentBase(int number) {
        // Required empty public constructor
        this.number = number;
        Log.d("ANR", "WithNumberFragment: ----constructor--------" + number);
    }
    public WithNumberFragmentBase(int number, String sign) {
        // Required empty public constructor
        this.number = number;
        this.sign = "   " + sign;
        Log.d("ANR", "WithNumberFragment: ----constructor--------" + number + sign);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("tiktok", "onCreateView: ----------------" + number + sign);
        return inflater.inflate(R.layout.fragment_with_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvNumber = view.findViewById(R.id.tv_number);
        tvNumber.setText(number + "");
        Log.d("tiktok", "onViewCreated: ----------" + number + sign);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("tiktok", "onStop: -----------" + number + sign);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("tiktok", "onResume: -------------"+ number + sign);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("tiktok", "onDestroyView: --------------"+ number + sign);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("tiktok", "onPause: --------------"+ number + sign);
    }
}