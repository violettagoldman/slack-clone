/*
package com.dao.concret;

import com.bean.ResponseMessage;
import com.bean.Channel;
import com.bean.User;
import com.bean.UserChannel;
import com.dao.DAO;
import com.helpers.PasswordHelper;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.controller.ChannelController.isChannelNameValid;

public class ChannelDAO extends DAO<Channel> {

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

    public Optional<Channel> delete(long channelId) throws SQLException{

            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "DELETE FROM channel WHERE id = " + channelId
            );

            return this.find(channelId);
    }
}
*/
