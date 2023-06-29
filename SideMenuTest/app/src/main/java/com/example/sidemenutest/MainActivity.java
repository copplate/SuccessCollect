package com.example.sidemenutest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sidemenutest.fragment_.BlankFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private Toolbar mToolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.nv_drawer_menu);

        mToolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        setHomePage();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_study:
                        BlankFragment blankFragment = BlankFragment.newInstance("单词学习", "");
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fcv, blankFragment)
                                .commit();
                        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                            mDrawerLayout.closeDrawers();
                        }
                        break;
                    case R.id.menu_pass:
                        BlankFragment blankFragmentPass = BlankFragment.newInstance("单词闯关", "");
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fcv, blankFragmentPass)
                                .commit();
                        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                            mDrawerLayout.closeDrawers();
                        }
                        break;
                }




                return true;
            }
        });



    }

    private void setHomePage() {
        BlankFragment blankFragment = BlankFragment.newInstance("首页", "");
        getSupportFragmentManager().beginTransaction().
                replace(R.id.fcv, blankFragment)
                .commit();
        mNavigationView.setCheckedItem(R.id.menu_study);
    }
}