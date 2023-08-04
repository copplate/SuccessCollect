package com.example.cachecountviewpager2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cachecountviewpager2.R;


public class InContainerFragment extends Fragment {
    private TextView tvLetter;
    private int letter = 100;


    public InContainerFragment(int letter) {
        // Required empty public constructor
        this.letter = letter;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("tiktok", "onCreateView: ------InContainerFragment-----" + letter);

        return inflater.inflate(R.layout.fragment_in_container, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("tiktok", "onViewCreated: ------InContainerFragment-----" + letter);

        tvLetter = view.findViewById(R.id.tv_letter);
        tvLetter.setText(letter + " ");

    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d("tiktok", "onStop: ------InContainerFragment-----" + letter);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("tiktok", "onResume: ------InContainerFragment-------"+ letter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("tiktok", "onDestroyView: -----InContainerFragment---------"+ letter);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("tiktok", "onPause: -------InContainerFragment-------"+ letter);
    }
}