package com.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Message implements Serializable {
    private long messageId;
    private long transmitter_id;
    private long channel_id;
    private Timestamp messageCreatedAt;
    private String message;
    private boolean isSmiley;

    public Message() {}

    public Message(long messageId, long transmitter_id, long channel_id, String message) {
        this.messageId = messageId;
        this.transmitter_id = transmitter_id;
        this.channel_id = channel_id;
        this.messageCreatedAt = new Timestamp(System.currentTimeMillis());
        this.message = message;
    }

    public Message(long messageId, long transmitter_id, long channel_id, Timestamp messageCreatedAt, String message) {
        this.messageId = messageId;
        this.transmitter_id = transmitter_id;
        this.channel_id = channel_id;
        this.messageCreatedAt = messageCreatedAt;
        this.message = message;
    }

    public long getMessageId() {
        return messageId;
    }

    public long getTransmitter_id() {
        return transmitter_id;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public Timestamp getMessageCreatedAt() {
        return messageCreatedAt;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", transmitter_id=" + transmitter_id +
                ", channel_id=" + channel_id +
                ", messageCreatedAt=" + messageCreatedAt +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, transmitter_id, channel_id, messageCreatedAt, message);
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
