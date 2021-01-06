package com.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class UserChannel {
    private long userChannelId;
    private long channel_id;
    private long user_id;
    private Timestamp userChannelCreatedAt;

    public UserChannel() {}

    public UserChannel(long userChannelId, long channel_id, long user_id) {
        this.userChannelId = userChannelId;
        this.channel_id = channel_id;
        this.user_id = user_id;
        this.userChannelCreatedAt = new Timestamp(System.currentTimeMillis());;
    }

    public UserChannel(long userChannelId, long channel_id, long user_id, Timestamp userChannelCreatedAt) {
        this.userChannelId = userChannelId;
        this.channel_id = channel_id;
        this.user_id = user_id;
        this.userChannelCreatedAt = userChannelCreatedAt;
    }

    public long getUserChannelId() {
        return userChannelId;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public Timestamp getUserChannelCreatedAt() {
        return userChannelCreatedAt;
    }

    @Override
    public String toString() {
        return "UserChannel{" +
                "userChannelId=" + userChannelId +
                ", channel_id='" + channel_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", userChannelCreatedAt=" + userChannelCreatedAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userChannelId, channel_id, user_id, userChannelCreatedAt);
    }

    public void setUserChannelId(long userChannelId) {
        this.userChannelId = userChannelId;
    }
}
