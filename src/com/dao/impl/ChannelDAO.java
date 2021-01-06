package com.dao.impl;

import com.bean.Channel;
import com.dao.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.helpers.RegexHelper.isChannelNameValid;

public class ChannelDAO implements DAO<Channel> {

    public Optional<Channel> find(long channelId) throws SQLException{

        ResultSet result = this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeQuery(
                "SELECT * FROM channel WHERE id = " + channelId
        );

        if(result.first()) {
            return Optional.of(new Channel(
                    channelId,
                    result.getLong("admin_user_id"),
                    result.getString("name"),
                    result.getTimestamp("created_at")
            ));
        }
        return Optional.empty();
    }

    public Optional<List<Channel>> findAll () throws SQLException{

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

    public Optional<Channel> create(Channel channelObj) throws SQLException{

        if (isChannelNameValid(channelObj.getChannelName())) {

                PreparedStatement prepare = this.connect.prepareStatement(
                        "INSERT INTO channel (name,admin_user_id,created_at) VALUES(?,?,?)"
                );

                prepare.setString(1,channelObj.getChannelName());
                prepare.setLong(2,channelObj.getAdmin_user_id());
                prepare.setTimestamp(3,channelObj.getChannelCreatedAt());

                prepare.executeUpdate();

                ResultSet rs = prepare.getGeneratedKeys();
                if (rs.next()){
                    channelObj.setChannelId(rs.getLong(1));
                }
                return Optional.of(channelObj);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Channel> update(Channel channelObj) throws SQLException{

            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "UPDATE channel SET name = '" + channelObj.getChannelName() + "', "
                            + "admin_user_id = '" + channelObj.getAdmin_user_id() + "' "
                            + "WHERE id = " + channelObj.getChannelId()
            );

            return this.find(channelObj.getChannelId());
    }

    public void delete(long channelId) throws SQLException{
            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "DELETE FROM channel WHERE id = " + channelId
            );
    }
}