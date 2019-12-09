package com.example.buddyapp4;

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

public class Event {
    private int id;
    private User author;
    private String title;
    private String description;
    private Date eventDate;
    // ToDo: private image thumbnail;
    // The event channel is used to know event participants.
    private Channel channel;
    private ArrayList<EventType> types;
    private EventStatus status;

    public Event(User authr, String ttl, String desc,Date date, ArrayList<EventType> typs) {
        id = 0; //FixMe
        author = authr;
        title = ttl;
        description = desc;
        eventDate = date;
        channel = new Channel(this);
        types = typs;
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
    public Date getEventDate() {
        return eventDate;
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
