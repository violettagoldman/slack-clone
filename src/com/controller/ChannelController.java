package com.controller;

import com.bean.Channel;
import com.bean.ResponseMessage;
import com.dao.concret.ChannelDAO;
import com.jdbc.ConnectionSQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChannelController {

    private final ChannelDAO channelDAO = new ChannelDAO();
    public Connection connect = ConnectionSQL.getInstance();

    public Optional<List<Channel>> findAllChannel () throws SQLException{

            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeQuery(
                    "SELECT * FROM channel"
            );

            List<Channel> channels = new ArrayList<>();

            while(result.next()) {
                Channel channel = new Channel(
                        result.getLong("id"),
                        result.getLong("admin_user_id"),
                        result.getString("name"),
                        result.getTimestamp("created_at")
                );
                channels.add(channel);
            }

            return Optional.of(channels);



    }

    public static boolean isChannelNameValid(String channelName) {
        String regex = "^[\\w-]{2,20}$";
        return channelName.matches(regex);
    }

}
