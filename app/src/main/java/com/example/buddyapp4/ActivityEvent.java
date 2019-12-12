package com.example.buddyapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class ActivityEvent extends AppCompatActivity {

    TextView titleView, descriptionView, creatorView, eventDateView;
    Button attendButton, openChatGroupButton;

    int eventIndex, userIndex;
    Event event;
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Intent intent  = getIntent();
        eventIndex = intent.getIntExtra("EVENTINDEX", 0);
        userIndex = intent.getIntExtra("USERINDEX", 0);
        event = DemoServer.allEvents.get(eventIndex);
        currentUser = DemoServer.allMembers.get(userIndex);

        titleView = findViewById(R.id.eventPageTitle);
        descriptionView = findViewById(R.id.eventDescription);
        creatorView = findViewById(R.id.eventCreatorName);
        attendButton = findViewById(R.id.attendEventButton);
        openChatGroupButton = findViewById(R.id.openChatGroupButton);
        eventDateView = findViewById(R.id.eventDate);

        if (event.getChannel().getUsers().contains(currentUser) ) {
            attendButton.setText("ATTENDING");
            attendButton.setTextColor(getResources().getColor(R.color.colorRed));
        }

        titleView.setText(event.getTitle());
        descriptionView.setText(event.getDescription());
        creatorView.setText(event.getAuthor().getName());
        eventDateView.setText(event.getEventDate());
        toastThis("opening event as " + currentUser.getName());
        attendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (event.getChannel().getUsers().contains(currentUser) ) {
                    if (DemoServer.allEvents.contains(event) && DemoServer.allMembers.contains(currentUser) ) {
                        toastThis("correct");
                    }
                    //toastThis("You have already chosen to Attend");
                }
                else {
                    if (DemoServer.allEvents.contains(event) && DemoServer.allMembers.contains(currentUser)) {
                        toastThis("correct");
                    }
                    else toastThis("event is not in all events list");
                    event.getChannel().getUsers().add(currentUser);
                    attendButton.setText("ATTENDING");
                    attendButton.setTextColor(getResources().getColor(R.color.colorRed));
//                    toastThis("You have been subscribed to the event!");
                }
            }
        });

        openChatGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (event.getChannel().getUsers().contains(currentUser)) {
                    Intent intent = new Intent (ActivityEvent.this, ActivityChatGroup.class);
                    intent.putExtra("EVENTINDEX", eventIndex);
                    intent.putExtra("USERINDEX", userIndex);
                    startActivity(intent);
                }
                else { toastThis("Please choose attend first!"); }
            }
        });




    }

    public void toastThis(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
