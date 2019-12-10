package com.example.buddyapp4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.buddyapp4.ui.main.SectionsPagerAdapter;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements ProfileFragment.ProfileFragmentListener {

    long backPressedTime;
    User currentUser;

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

        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra("CURRENTUSER");

        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), currentUser);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent creatingEvent = new Intent (MainActivity.this, ActivityCreateEvent.class);
                creatingEvent.putExtra("CURRENTUSER", (Serializable)currentUser);
                startActivity(creatingEvent);

//                toastThis("Adding a new event by user " + currentUser.getName());
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

    public void openEventPage(Event event) {
        Intent intent = new Intent (MainActivity.this, ActivityEvent.class);
        intent.putExtra("EVENT", (Serializable) event);
        intent.putExtra("CURRENTUSER", (Serializable)currentUser);
        startActivity(intent);
    }
}