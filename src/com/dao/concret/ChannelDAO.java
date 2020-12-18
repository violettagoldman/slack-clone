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

public class ChannelDAO extends DAO<Channel> {

    public ResponseMessage<Channel> create(Channel channelObj) {

        if (this.isChannelNameValid(channelObj.getChannelName())) {

            try {
                PreparedStatement prepare = this.connect.prepareStatement(
                        "INSERT INTO channel (name,admin_user_id,created_at) VALUES(?,?,?)"
                );

                prepare.setString(1,channelObj.getChannelName());
                prepare.setLong(2,channelObj.getAdmin_user_id());
                prepare.setTimestamp(3,channelObj.getChannelCreatedAt());

                prepare.executeUpdate();

                return new ResponseMessage<Channel>(channelObj, ResponseMessage.messages.CHANNEL_CREATE, 200);

            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println(e);
                return new ResponseMessage<Channel>(null, ResponseMessage.messages.CHANNEL_ALREADY_EXISTS, 409);
            } catch (SQLException e) {
                System.out.println(e);
                return new ResponseMessage<Channel>(null, ResponseMessage.messages.ERR_BDD, 500);
            }
        } else {
            return new ResponseMessage<Channel>(null, ResponseMessage.messages.ERR_INFO_USER,000);
        }
    }

    public ResponseMessage<Channel> find(long channelId) {
        Channel channel = new Channel();
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeQuery(
                    "SELECT * FROM channel WHERE id = " + channelId
            );

            if(result.next()) {
                channel = new Channel(
                        channelId,
                        result.getLong("admin_user_id"),
                        result.getString("name"),
                        result.getTimestamp("created_at")
                );
            }

            return new ResponseMessage<Channel>(channel, ResponseMessage.messages.CHANNEL_FIND, 200);

        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<Channel>(null, ResponseMessage.messages.ERR_BDD, 500);
        }
    }

    public ResponseMessage<ArrayList<Channel>> findAll () {

        try {
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

            return new ResponseMessage(channels,ResponseMessage.messages.ALL_CHANNELS_FIND,200);

        }catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage(null, ResponseMessage.messages.ERR_BDD, 500);
        }

    }

    public ResponseMessage<Channel> update(Channel channelObj){
        try {

            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "UPDATE channel SET name = '" + channelObj.getChannelName() + "', "
                            + "admin_user_id = '" + channelObj.getAdmin_user_id() + "' "
                            + "WHERE id = " + channelObj.getChannelId()
            );

            channelObj = this.find(channelObj.getChannelId()).getData();

            return new ResponseMessage<Channel>(channelObj, ResponseMessage.messages.CHANNEL_UPDATE, 200);

        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<Channel>(null, ResponseMessage.messages.ERR_BDD, 500);
        }
    }

    public ResponseMessage<Channel> delete(long channelId) {
        try {

            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "DELETE FROM channel WHERE id = " + channelId
            );

            return new ResponseMessage<Channel>(null, ResponseMessage.messages.CHANNEL_DELETE, 200);

        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<Channel>(null, ResponseMessage.messages.ERR_BDD, 500);
        }
    }

    public static boolean isChannelNameValid(String channelName) {
        String regex = "^[\\w-]{2,20}$";
        return channelName.matches(regex);
    }


}
*/
