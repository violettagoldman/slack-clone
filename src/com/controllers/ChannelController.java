package com.controllers;

import com.bean.Channel;
import com.bean.ResponseMessage;
import com.dao.impl.ChannelDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.bean.ResponseMessage.Messages.*;
import static com.helpers.RegexHelper.isChannelNameValid;

public class ChannelController extends Controller {

    private final ChannelDAO channelDAO = new ChannelDAO();

    public ResponseMessage find(long id) throws SQLException {
        Optional channelOp = channelDAO.find(id);

        if (channelOp.isEmpty()) {
            return new ResponseMessage(null, CHANNEL_NOT_FOUND, 400);
        }

        return new ResponseMessage(channelOp.get(), CHANNEL_FOUND, 200);

    }

    public ResponseMessage findAll() throws SQLException {

        List<Channel> channelsOp = channelDAO.findAll().get();

        if (channelsOp.size() < 1) {
            return new ResponseMessage(null, NO_CHANNEL_IN_DB, 400);
        }

        return new ResponseMessage(channelsOp, ALL_CHANNELS_FOUND, 200);

    }

    public ResponseMessage create(Channel channelObj) throws SQLException {
        Optional channelOp = channelDAO.create(channelObj);

        if (channelOp.isEmpty()) {
            return new ResponseMessage(null, ERROR_CREATION_CHANNEL, 400);
        }

        return new ResponseMessage(channelOp.get(), CHANNEL_CREATED, 200);
    }

    public ResponseMessage delete(long id) throws SQLException {
        channelDAO.delete(id);

        return new ResponseMessage(null, CHANNEL_DELETED, 200);
    }

    public ResponseMessage updateInformation(Channel actualChannel, String name, long adminId) throws SQLException {

            // We check if the information is valid
        if (!isChannelNameValid(name)) {
            return new ResponseMessage(null, CHANNELNAME_NOT_VALID, 400);
        }

            // We check if values to update are null
        if (name == null) {
            name = actualChannel.getChannelName();
        }
        if (adminId == 0) {
            adminId = actualChannel.getAdmin_user_id();
        }

            // We update the user in DB
        Channel channelToUpdate = new Channel(actualChannel.getChannelId(), adminId, name);
        Optional updatedChannel = channelDAO.update(channelToUpdate);

        if (updatedChannel.isEmpty()) {
            return new ResponseMessage(null, ERROR_UPDATE_CHANNEL, 400);
        }

        return new ResponseMessage(updatedChannel.get(), INFORMATION_CHANNEL_UPDATED, 200);
    }

}