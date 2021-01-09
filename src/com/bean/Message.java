package com.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Message implements Serializable {
    private long messageID;
    private long transmitterID;
    private long channelID;
    private Timestamp createdAt;
    private String message;
    private boolean isSmiley;

    public Message() {}

    public Message(long messageId, long transmitterID, long channelID, String message) {
        this.messageID = messageId;
        this.transmitterID = transmitterID;
        this.channelID = channelID;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.message = message;
    }

    public Message(long messageId, long transmitterID, long channelID, Timestamp createdAt, String message, boolean isSmiley) {
        this.messageID = messageId;
        this.transmitterID = transmitterID;
        this.channelID = channelID;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.message = message;
        this.isSmiley=isSmiley;
    }
    public Message(long messageId, long transmitterID, long channelID, Timestamp createdAt, String message) {
        this.messageID = messageId;
        this.transmitterID = transmitterID;
        this.channelID = channelID;
        this.createdAt = createdAt;
        this.message = message;
    }

    public long getMessageID() {
        return messageID;
    }

    public long getTransmitterID() {
        return transmitterID;
    }

    public long getChannelID() {
        return channelID;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSmiley() {
        return isSmiley;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageID +
                ", transmitter_id=" + transmitterID +
                ", channel_id=" + channelID +
                ", messageCreatedAt=" + createdAt +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageID, transmitterID, channelID, createdAt, message);
    }

    public void setMessageID(long messageID) {
        this.messageID = messageID;
    }
}
