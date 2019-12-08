package com.example.buddyapp4;

public class Feedback {
    private String comment;
    private User author;

    public Feedback(User auth, String com) {
        author = author;
        comment = com;
    }
    public String getComment() {
        return comment;
    }
    public User getAuthor() {
        return author;
    }
}
