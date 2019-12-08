package com.example.buddyapp4;

public class BuddyRating {

    private String feedback;
    private int rating;

    public BuddyRating(int rate, String feedback){
        this.rating = rate;
        this.feedback = feedback;
    }

    public int getRating(){
       return rating;
    }
    public String getFeedback() { return feedback; }

}
