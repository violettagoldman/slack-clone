package com.bean;

import java.io.Serializable;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

public class Channel implements Serializable {
    private long ID;
    private Timestamp createdAt;
    private long adminUserId;
    private String name;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();
    public Channel() {}

    public Channel(long ID, long adminUserId, String name) {
        this.ID = ID;
        this.adminUserId = adminUserId;
        this.name = name;
        this.createdAt = new Timestamp(System.currentTimeMillis());;
    }

    public Channel(long ID, long adminUserId, String name, Timestamp channelCreatedAt) {
        this.ID = ID;
        this.adminUserId = adminUserId;
        this.name = name;
        this.createdAt = channelCreatedAt;
    }

    public long getID() {
        return ID;
    }

    public long getAdminUserId() {
        return adminUserId;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "channelId=" + ID +
                ", admin_user_id='" + adminUserId + '\'' +
                ", channelName='" + name + '\'' +
                ", channelCreatedAt=" + createdAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, adminUserId, name, createdAt);
    }

    public void setID(long ID) {
        this.ID = ID;
    }

}
