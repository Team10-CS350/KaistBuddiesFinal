package com.example.buddyapp4;

import java.util.Date;

public class Message {
    private int id;
    private String content;
    private Date createdDate;
    private User author;
    private Boolean deleted;

    public Message (User sender, String message) {
        id = getNewMessageId();
        content = message;
        createdDate = new Date();
        author = sender;
        deleted = false;
    }

    public void delete() {
        deleted = true;
    }
    public User getAuthor() {
        return author;
    }
    public String getContent() {
        return content;
    }
    public Date getCreatedDate() {
        return createdDate;
    }

    public int getNewMessageId() { return 0;} //FixMe

}
