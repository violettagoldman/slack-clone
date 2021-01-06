package com.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class UserChannel{
    private long userChannelId;
    private long channelId;
    private long userId;
    private Timestamp userChannelCreatedAt;

    public UserChannel() {}

    public UserChannel(long userChannelId, long channelId, long userId) {
        this.userChannelId = userChannelId;
        this.channelId = channelId;
        this.userId = userId;
        this.userChannelCreatedAt = new Timestamp(System.currentTimeMillis());
    }

    public UserChannel(long userChannelId, long channel_id, long user_id, Timestamp userChannelCreatedAt) {
        this.userChannelId = userChannelId;
        this.channelId = channel_id;
        this.userId = user_id;
        this.userChannelCreatedAt = userChannelCreatedAt;
    }

    public long getUserChannelId() {
        return userChannelId;
    }

    public long getChannelId() {
        return channelId;
    }

    public long getUserId() {
        return userId;
    }

    public Timestamp getUserChannelCreatedAt() {
        return userChannelCreatedAt;
    }

    @Override
    public String toString() {
        return "UserChannel{" +
                "userChannelId=" + userChannelId +
                ", channel_id='" + channelId + '\'' +
                ", user_id='" + userId + '\'' +
                ", userChannelCreatedAt=" + userChannelCreatedAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userChannelId, channelId, userId, userChannelCreatedAt);
    }

    public void setUserChannelId(long channelId) {
        this.channelId=channelId;
    }
}
