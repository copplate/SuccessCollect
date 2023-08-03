package com.example.cachecountviewpager2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class WithNumberFragment extends Fragment implements LifecycleObserver {
    private TextView tvNumber;
    private int number;
    private String sign = "";
    private Button btnClick;


    public WithNumberFragment(int number) {
        // Required empty public constructor
        this.number = number;
        Log.d("ANR", "WithNumberFragment: ----constructor--------" + number);
    }
    public WithNumberFragment(int number,String sign) {
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
        btnClick = view.findViewById(R.id.btn_click);
        tvNumber.setText(number + "");

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tiktok", "onClick: -------------" + "btnClick" + "   " + number + sign);
            }
        });

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

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void activityStart() {
        Log.d("tiktok", "Lifecycle.Event.ON_RESUME");
        this.onResume();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void activityStop() {
        Log.d("tiktok", "Lifecycle.Event.ON_PAUSE");
        this.onPause();
    }
}