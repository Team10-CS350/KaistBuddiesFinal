package com.example.buddyapp4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum EventType
{
    DINNER, PARTY, SHOPPING, GAMING, SPORTS, STUDY, HANGOUT, MEETING;
}

enum EventStatus
{
    ACTIVE, EXPIRED, DELETED, FULL;
}

public class Event implements Serializable {
    private int id;
    private User author;
    private String title;
    private String description;
    private String dateAndTime;
    private Date eventDate;
    // ToDo: private image thumbnail;
    // The event channel is used to know event participants.
    private Channel channel;
    private ArrayList<EventType> types;
    private EventStatus status;

    public Event(User authr, String ttl, String desc,Date date, ArrayList<EventType> typs, String date_and_time) {
        id = 0; //FixMe
        author = authr;
        title = ttl;
        description = desc;
        eventDate = date;
        channel = new Channel(this);
        types = typs;
        dateAndTime = date_and_time;
        status = EventStatus.ACTIVE;
    }

    public String getTitle() {
        return title;
    }
    public User getAuthor() {
        return author;
    }
    public String getDescription() {
        return description;
    }
    public List<User> getParticipants() { return channel.getUsers(); }
    public String getEventDate() {
        return dateAndTime;
    }
    public ArrayList<EventType> getEventTypes() { return types; }

    /*
    public image getThumbnail() {
        return thumbnail;
    }
    */
    public EventStatus getStatus() {
        return status;
    }
    public Channel getChannel() {
        return channel;
    }
    public Boolean isActive() {
        return (status == EventStatus.ACTIVE);
    }


}
