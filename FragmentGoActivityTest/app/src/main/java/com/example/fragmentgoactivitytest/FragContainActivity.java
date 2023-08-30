package com.example.fragmentgoactivitytest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class FragContainActivity extends AppCompatActivity {
    private FragmentContainerView fcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_contain2);
        fcv = findViewById(R.id.fcv);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BlankFragment blankFragment = new BlankFragment();
        fragmentTransaction.add(R.id.fcv, blankFragment).commit();

    }
}