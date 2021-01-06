package com.controllers;

import com.bean.Channel;
import com.dto.ChannelModel;
import com.dto.ResponseMessage;
import com.dao.concret.ChannelDAO;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import static com.helpers.RegexHelper.isChannelNameValid;

public class ChannelController  extends Controller {

    private static final ChannelDAO channelDAO = new ChannelDAO();


    public static ResponseMessage<ChannelModel> createChannel(Channel channel){
        Optional<Channel> optional;
        ChannelModel channelModel;
        if (isChannelNameValid(channel.getName()));
        try{
            optional= channelDAO.create(channel);
        }catch (SQLIntegrityConstraintViolationException e){
           //return new ResponseMessage<>(channel,"Channel created", 200);
        }
        catch (SQLException e){

        }
        return null;
    }

}
