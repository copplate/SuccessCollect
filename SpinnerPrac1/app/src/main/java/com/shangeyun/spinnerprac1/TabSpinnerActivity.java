package com.shangeyun.spinnerprac1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.shangeyun.spinnerprac1.fragment.Vp2ContainerFragment;

public class TabSpinnerActivity extends AppCompatActivity {
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_spinner);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Vp2ContainerFragment recommendFragment = new Vp2ContainerFragment();
        fragmentTransaction.add(R.id.fcv, recommendFragment);
        fragmentTransaction.commit();

    }
}