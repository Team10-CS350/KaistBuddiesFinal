package com.example.buddyapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class ActivityCreateEvent extends AppCompatActivity {

    EditText titleText, descriptionText, eventDate, eventTime;
    CheckBox dinnerCheck, partyCheck, shoppingCheck, gamingCheck, sportsCheck, studyCheck;
    CheckBox hangOutCheck, meetingCheck;
    Button postEventButton;


    User authr;
    String title, description;
    Date date;
    ArrayList<EventType> typs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        typs = new ArrayList<EventType> ();

        titleText = findViewById(R.id.eventTitleEntered);
        descriptionText = findViewById(R.id.eventDescEntered);
        eventDate = findViewById(R.id.eventDateEntered);
        eventTime = findViewById(R.id.eventTimeEntered);
        dinnerCheck = findViewById(R.id.checkDinner);
        partyCheck = findViewById(R.id.checkParty);
        shoppingCheck = findViewById(R.id.checkShopping);
        gamingCheck = findViewById(R.id.checkGaming);
        sportsCheck = findViewById(R.id.checkSports);
        studyCheck = findViewById(R.id.checkStudy);
        hangOutCheck = findViewById(R.id.checkHangOut);
        meetingCheck = findViewById(R.id.checkMeeting);
        postEventButton = findViewById(R.id.postEventButton);


        postEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toastThis("Your event is created");
                if (dinnerCheck.isChecked()) typs.add(EventType.DINNER);
                if (partyCheck.isChecked()) typs.add(EventType.PARTY);
                if (shoppingCheck.isChecked()) typs.add(EventType.SHOPPING);
                if (gamingCheck.isChecked()) typs.add(EventType.GAMING);
                if (sportsCheck.isChecked()) typs.add(EventType.SPORTS);
                if (studyCheck.isChecked()) typs.add(EventType.STUDY);
                if (hangOutCheck.isChecked()) typs.add(EventType.HANGOUT);
                if (meetingCheck.isChecked()) typs.add(EventType.MEETING);

                authr = DemoServer.user1;
                title = titleText.getText().toString();
                description = descriptionText.getText().toString();
                date = new Date();
                DemoServer.allEvents.add(authr.createEvent(title,description,date,typs));

                Intent intent = new Intent (ActivityCreateEvent.this, MainActivity.class);
                startActivity(intent);


            }
        });



    }

    public void toastThis(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
