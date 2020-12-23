package com.models;

public class ResponseMessage<T> {
 private T data;
 private String message;
 private int status;

    public ResponseMessage(T data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

}
