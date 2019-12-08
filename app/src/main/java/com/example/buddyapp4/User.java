package com.example.buddyapp4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private int id;
    private Date createDate;
    private String name;
    private String email;
    private boolean buddy;
    private List<Integer> ratings;
    private String password;


    public User(String name, String email, String paswrd, boolean isBuddy){
        this.name = name;
        this.email = email;
        this.buddy = isBuddy;
        this.password = paswrd;
        Date curDate = new Date();
        this.createDate = curDate;
        this.id = 0; //FixMe: How to create an id for a user
        this.ratings = new ArrayList<Integer>(); //FixMe: Should this be initialized
    }


    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public double getRating() {
        return calculateAverage(ratings);
    }
    public Boolean isBuddy()
    {
        return buddy;
    }
    public Boolean passwordMatches(String enteredPassword) {
        return password.equals(enteredPassword);
    }
    public void subscribeToEvent (Event event) {
        event.getParticipants().add(this);
    }
    public void sendMessage(Event event, String messageString) {
        Message message = new Message(this, messageString);
        event.getChannel().appendMessage(message);
    }
    public Event createEvent (String ttl, String desc,Date date, EventType typ) {
        return new Event(this, ttl, desc, date, typ);
    }

    public void deleteMessage(Event event, Message message) {
        message.delete();
        event.getChannel().removeMessage(message);
    }
    public void updateRating(int rating) {
        ratings.add(rating);
    }

    private double calculateAverage(List<Integer> ratings) {
        Integer total = 0;
        if(!ratings.isEmpty()) {
            for (Integer rating : ratings) {
                total += rating;
            }
            return total.doubleValue() / ratings.size();
        }
        return total;
    }
}
