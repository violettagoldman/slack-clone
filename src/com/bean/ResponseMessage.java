package com.bean;

import java.io.Serializable;

public class ResponseMessage<T> implements Serializable {
    private T data;
    private Messages message;
    private int status;

    public enum Messages {
        ALL_USERS_FOUND,
        EMAIL_ALREADY_TAKEN,
        EMAIL_NOT_VALID,
        ERROR_CREATION_USER,
        ERROR_UPDATE_USER,
        INCORRECT_PASSWORD,
        INFORMATION_USER_UPDATED,
        NO_USER_IN_DB,
        PASSWORD_NOT_VALID,
        USER_ALREADY_EXISTS,
        USER_CREATED,
        USER_DELETED,
        USER_FOUND,
        USER_IDENTIFIED,
        USER_NOT_FOUND,
        USERNAME_ALREADY_TAKEN,
        USERNAME_NOT_VALID,

        ALL_CHANNELS_FOUND,
        CHANNEL_CREATED,
        CHANNEL_DELETED,
        CHANNEL_FOUND,
        CHANNELNAME_NOT_VALID,
        CHANNEL_NOT_FOUND,
        ERROR_CREATION_CHANNEL,
        ERROR_UPDATE_CHANNEL,
        INFORMATION_CHANNEL_UPDATED,
        NO_CHANNEL_IN_DB,

        USER_CHANNEL_FOUND,
        USER_CHANNEL_NOT_FOUND
    }

    public ResponseMessage(T data, Messages message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public Messages getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String toString(){
        return status+ ":"+message;
    }

}