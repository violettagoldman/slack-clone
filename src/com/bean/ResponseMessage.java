package com.bean;
/*
* La classe qui encapsulera l'envoie du serveur(la classe T).
*
* */
public class ResponseMessage<T> {
    private T data;
    private messages message;
    private int status;

    public enum messages { // M Maj
        ERR_BDD,
        ERR_HASHING,
        USER_FIND,
        ALL_USERS_FIND,
        USER_CREATE,
        USER_ALREADY_EXISTS,
        USER_UPDATE,
        ERR_INFO_USER,
        USER_DELETE,
        USER_SIGN_IN,

        CHANNEL_CREATE,
        CHANNEL_ALREADY_EXISTS,
        CHANNEL_UPDATE,
        CHANNEL_FIND,
        ALL_CHANNELS_FIND,
        USER_ADD_TO_A_CHANNEL,
        CHANNEL_DELETE
    }

    public ResponseMessage(T data, messages message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public messages getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String toString(){
        return status+ ":"+message;
    }
}
