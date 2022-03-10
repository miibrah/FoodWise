package com.example.foodwise;

import com.google.firebase.Timestamp;

public class Item {

    private String name;
    private String expdate;
    private String userID;
    private Timestamp expTimestamp;
    private Timestamp createdAt;

    public Item() {}

    public Item(final String name, final String expdate, final String userID, final Timestamp expTimestamp,
                final Timestamp createdAt) {
        this.name = name;
        this.expdate = expdate;
        this.userID = userID;
        this.expTimestamp = expTimestamp;
        this.createdAt = createdAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(final String expdate) {
        this.expdate = expdate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(final String userID) {
        this.userID = userID;
    }

    public Timestamp getExpTimestamp() {
        return expTimestamp;
    }

    public void setExpTimestamp(final Timestamp expTimestamp) {
        this.expTimestamp = expTimestamp;
    }
}
