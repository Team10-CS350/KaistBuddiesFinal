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

    Event event;
    User currentUser;

    ArrayList<String> authors;
    ArrayList<String> messages;

    ListView messageBoard;
    EditText enteredMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_group);

        Intent intent = getIntent();
        event = (Event) intent.getSerializableExtra("EVENT");
        currentUser = (User) intent.getSerializableExtra("CURRENTUSER");

        authors = event.getChannel().getAuthorNames();
        messages = event.getChannel().getMessageStrings();

        messageBoard = findViewById(R.id.messagesBoard);
        MessageAdapter adapter = new MessageAdapter(ActivityChatGroup.this, authors, messages);
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
            authors.add(message.getAuthor().getName());
            messages.add(message.getContent());
            enteredMessage.getText().clear();
            adapter.notifyDataSetChanged();
        }

    }

    class MessageAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> authors;
        ArrayList<String> messages;

        MessageAdapter(Activity activity, ArrayList<String> authors, ArrayList<String> messages) {
            super(activity.getApplicationContext(), R.layout.their_message, R.id.mainTitle,authors);
            this.context = context;
            this.authors = authors;
            this.messages = messages;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View messageBlock = layoutInflater.inflate(R.layout.their_message, parent, false);
            TextView sendersNameField = messageBlock.findViewById(R.id.sendersName);
            TextView messageField = messageBlock.findViewById(R.id.message_body);

//            image.setImageResource(rImages[position]);
            sendersNameField.setText(authors.get(position));
            messageField.setText(messages.get(position));

            return messageBlock;
        }

    }

    public void toastThis(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
