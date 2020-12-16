package com.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class Channel {
    private long channelId;
    private Timestamp channelCreatedAt;
    private long admin_user_id;
    private String channelName;

    public Channel() {}

    public Channel(long channelId, long admin_user_id, String channelName) {
        this.channelId = channelId;
        this.admin_user_id = admin_user_id;
        this.channelName = channelName;
        this.channelCreatedAt = new Timestamp(System.currentTimeMillis());;
    }

    public Channel(long channelId, long admin_user_id, String channelName, Timestamp channelCreatedAt) {
        this.channelId = channelId;
        this.admin_user_id = admin_user_id;
        this.channelName = channelName;
        this.channelCreatedAt = channelCreatedAt;
    }

    public long getChannelId() {
        return channelId;
    }

    public long getAdmin_user_id() {
        return admin_user_id;
    }

    public String getChannelName() {
        return channelName;
    }

    public Timestamp getChannelCreatedAt() {
        return channelCreatedAt;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "channelId=" + channelId +
                ", admin_user_id='" + admin_user_id + '\'' +
                ", channelName='" + channelName + '\'' +
                ", channelCreatedAt=" + channelCreatedAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelId, admin_user_id, channelName, channelCreatedAt);
    }
}
