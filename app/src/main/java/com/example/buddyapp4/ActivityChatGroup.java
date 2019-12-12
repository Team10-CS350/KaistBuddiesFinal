package com.example.buddyapp4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ActivityChatGroup extends AppCompatActivity {

    int eventIndex, userIndex;
    Event event;
    User currentUser;

    ListView messageBoard;
    EditText enteredMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_group);

        Intent intent = getIntent();
        eventIndex = intent.getIntExtra("EVENTINDEX", 0);
        userIndex = intent.getIntExtra("USERINDEX", 0);
        event = DemoServer.allEvents.get(eventIndex);
        currentUser = DemoServer.allMembers.get(userIndex);

        messageBoard = findViewById(R.id.messagesBoard);
        MessageAdapter adapter = new MessageAdapter(ActivityChatGroup.this, event.getChannel());
        if (adapter == null || messageBoard == null) toastThis ("adapter is null");
        else messageBoard.setAdapter(adapter);

    }

    public void sendMessage(View v) {
        enteredMessage = findViewById(R.id.messageEntered);
        messageBoard = findViewById(R.id.messagesBoard);

        MessageAdapter adapter = (MessageAdapter) messageBoard.getAdapter();
        String messageText = enteredMessage.getText().toString();
        if (messageText.length() > 0) {
            Message message = new Message (currentUser, messageText);
            event.getChannel().appendMessage(message);
            enteredMessage.getText().clear();
            adapter.notifyDataSetChanged();
            adapter.add("new message added");

        }

    }

    class MessageAdapter extends ArrayAdapter<String> {
        Context context;
        Channel channel;
        MessageAdapter(Activity activity, Channel eventChannel) {
            super(activity.getApplicationContext(), R.layout.their_message, R.id.mainTitle,eventChannel.getAuthorNames());
            this.context = context;
            this.channel = event.getChannel();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View messageBlock = layoutInflater.inflate(R.layout.their_message, parent, false);
            TextView sendersNameField = messageBlock.findViewById(R.id.sendersName);
            TextView messageField = messageBlock.findViewById(R.id.message_body);

//            image.setImageResource(rImages[position]);
            sendersNameField.setText(event.getChannel().getAuthorNames().get(position));
            messageField.setText(event.getChannel().getMessageStrings().get(position));

            return messageBlock;
        }

    }

    public void toastThis(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
