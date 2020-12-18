package com.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

public class Channel implements Serializable {
    private long ID;
    private Timestamp createdAt;
    private setInt adminUserId;
    private String name;

    public Channel() {}

    public Channel(long ID, int adminUserId, String name) {
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
