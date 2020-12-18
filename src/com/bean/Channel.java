package com.bean;

import java.time.LocalDateTime;

public class Channel {
    private int channelId;
    private LocalDateTime createdAt;
    private int admin_user_id;
    private String channelName;

    public Channel(int channelId, int admin_user_id, String channelName) {
        this.channelId = channelId;
        this.createdAt = LocalDateTime.now();
        this.admin_user_id = admin_user_id;
        this.channelName = channelName;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getAdmin_user_id() {
        return admin_user_id;
    }

    public void setAdmin_user_id(int admin_user_id) {
        this.admin_user_id = admin_user_id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
