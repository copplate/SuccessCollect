package com.example.cachecountviewpager2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WithNumberFragment extends Fragment {
    private TextView tvNumber;
    private int number;


    public WithNumberFragment(int number) {
        // Required empty public constructor
        this.number = number;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("tiktok", "onCreateView: ----------------" + number);
        return inflater.inflate(R.layout.fragment_with_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvNumber = view.findViewById(R.id.tv_number);
        tvNumber.setText(number + "");
        Log.d("tiktok", "onViewCreated: ----------" + number);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("tiktok", "onStop: -----------" + number);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("tiktok", "onResume: -------------"+ number);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("tiktok", "onDestroyView: --------------"+ number);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("tiktok", "onPause: --------------"+ number);
    }
}