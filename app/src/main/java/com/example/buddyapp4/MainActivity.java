package com.example.buddyapp4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.buddyapp4.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity implements ProfileFragment.ProfileFragmentListener {

    long backPressedTime;


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - backPressedTime < 2000) {
            moveTaskToBack(true);
            android.os.Process.killProcess (android.os.Process.myPid());
            System.exit(0);
            return;
        } else {
            toastThis("press back again to exit");
            backPressedTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastThis("Adding a new event");
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void logOutHandler() {
        SharedPreferences preferences = getSharedPreferences("status", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userStatus", "loggedOut");
        editor.apply();
        Intent intent = new Intent (MainActivity.this, ActivityLogIn.class);
        startActivity(intent);
    }

    public void toastThis(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}