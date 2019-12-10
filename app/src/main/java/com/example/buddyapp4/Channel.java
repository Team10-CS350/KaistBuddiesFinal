package com.example.buddyapp4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Channel implements Serializable {
    private int id;
    private Event event;
    private Date createdDate;
    private ArrayList<User> users;
    private ArrayList<Message> messages;

    public Channel (Event creatorEvent) {
        id = createNewChannelId();
        event = creatorEvent;
        createdDate = new Date();
        users = new ArrayList<User>();
        messages = new ArrayList<Message>();
    }

    public Event getEvent() {
        return event;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public List<User> getUsers() {
        return users;
    }
    public void appendMessage(Message message) {
        messages.add(message);
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

    public ArrayList<Message> getMessages() { return messages;}

    public ArrayList<String> getAuthorNames() {
        ArrayList<String> authorNames = new ArrayList<String>();
        for (Message message: messages) {
            authorNames.add(message.getAuthor().getName());
        }
        return authorNames;
    }

    public ArrayList<String> getMessageStrings() {
        ArrayList<String> messageStrings = new ArrayList<String>();
        for (Message message: messages) {
            messageStrings.add(message.getContent());
        }
        return messageStrings;
    }



    public int createNewChannelId() {return 1;} //FixMe
}
