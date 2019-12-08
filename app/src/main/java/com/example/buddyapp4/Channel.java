package com.example.buddyapp4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Channel {
    private int id;
    private Event event;
    private Date createdDate;
    private List<User> users;
    private List<Message> messages;

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

    public int createNewChannelId() {return 1;} //FixMe
}
