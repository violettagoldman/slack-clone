package com.controllers;

import com.bean.Message;
import com.bean.ResponseMessage;
import com.dao.impl.MessageDAO;
import com.invoker.decorators.ControllerRoute;
import com.invoker.decorators.MethodRoute;
import com.sun.mail.iap.Response;
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
}
