package com.controller;

import com.bean.ResponseMessage;
import com.bean.UserChannel;
import com.dao.concret.ChannelDAO;
import com.dao.concret.UserChannelDAO;
import com.jdbc.ConnectionSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserChannelController {

    private final UserChannelDAO userChannelDAO = new UserChannelDAO();
    public Connection connect = ConnectionSQL.getInstance();

    public Optional<List<UserChannel>> findAllUserFromAChannel(long channelId) throws SQLException{

            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeQuery(
                    "SELECT * FROM userchannel WHERE channel_id =" + channelId
            );

            List<UserChannel> userChannels = new ArrayList<>();

            while(result.next()) {
                UserChannel userChannel = new UserChannel(
                        result.getLong("id"),
                        result.getLong("channel_id"),
                        result.getLong("user_id"),
                        result.getTimestamp("created_at")
                );
                userChannels.add(userChannel);
            }

            return Optional.of(userChannels);
    }
}
