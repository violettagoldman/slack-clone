package com.controllers;

import com.models.Channel;
import com.models.ResponseMessage;
import com.dao.concret.ChannelDAO;

import java.util.ArrayList;

public class ChannelController  extends Controller {

    private final ChannelDAO channelDAO = new ChannelDAO();

    public static ResponseMessage<ArrayList<Channel>> getChannelsByUserID(int userID){
        return null;
    }


}
