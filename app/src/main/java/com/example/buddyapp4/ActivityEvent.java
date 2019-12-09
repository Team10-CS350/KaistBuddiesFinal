package com.example.buddyapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityEvent extends AppCompatActivity {

    TextView titleView;
    String eventTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent intent  = getIntent();
        eventTitle = intent.getStringExtra("EVENTTITLE");

        titleView = findViewById(R.id.eventPageTitle);
        titleView.setText(eventTitle);


    }
}
