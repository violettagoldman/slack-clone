package com.controllers;

import com.bean.Message;
import com.bean.ResponseMessage;
import com.dao.impl.MessageDAO;
import com.invoker.decorators.ControllerRoute;
import com.invoker.decorators.MethodRoute;
import com.sun.mail.iap.Response;

import java.sql.SQLException;

@ControllerRoute("message")
public class MessageController {
    private static final MessageDAO messageDAO = new MessageDAO();

    @MethodRoute("post")
    public static ResponseMessage postMessage(Message message){
        ResponseMessage<Message> response;
        Message data;
        try{
            data = messageDAO.create(message).get();
            response = new ResponseMessage<Message>(data,ResponseMessage.Messages.MESSAGE_CREATED,200);

        }catch (Exception e){
            response = new ResponseMessage<Message>(null,ResponseMessage.Messages.MESSAGE_CREATED,500);
        }
        return response;
    }
    @MethodRoute("delete")
    public static ResponseMessage delete(long messageID){
        ResponseMessage<Message> response;
        Message data;
        try {
            Message msg = messageDAO.find(messageID).get();
            messageDAO.delete(messageID);
            response = new ResponseMessage<Message>(msg, ResponseMessage.Messages.MESSAGE_DELETED, 200);
        }catch (SQLException e){
            response = new ResponseMessage<Message>(null, ResponseMessage.Messages.BAD_REQUEST, 400);
        }catch (Exception e){
            response = new ResponseMessage<Message>(null,ResponseMessage.Messages.ERROR_SERVER,500);
        }
        return response;
    }

}
