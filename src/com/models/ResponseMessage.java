package com.models;
/*
* La classe qui encapsulera l'envoie du serveur(la classe T).
*
* */
public class ResponseMessage<T> {
    private T data;
    private String message;
    private int status;

    public ResponseMessage(T data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
    public String toString(){
        return status+ ":"+message;
    }
}
