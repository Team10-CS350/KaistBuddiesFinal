package com.example.buddyapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.Serializable;

public class ActivityChatGroup extends AppCompatActivity {

    EditText enteredMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_group);

    }

    public void sendMessage(View v) {
        enteredMessage = findViewById(R.id.messageEntered);

    }
}
