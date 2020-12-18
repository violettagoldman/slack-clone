/*
package com.dao.concret;

import com.bean.Channel;
import com.bean.ResponseMessage;
import com.bean.UserChannel;
import com.dao.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserChannelDAO extends DAO<UserChannel> {

    public ResponseMessage<UserChannel> findAll(long channelId) {
        try {
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

            return new ResponseMessage(userChannels,ResponseMessage.messages.ALL_CHANNELS_FIND,200);

        }catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage(null, ResponseMessage.messages.ERR_BDD, 500);
        }
    }

    public ResponseMessage<UserChannel> find(long id) {
        return null;
    }

    @Override
    public ResponseMessage<UserChannel> create(UserChannel userChannelObj) {

        try {
            PreparedStatement prepare = this.connect.prepareStatement(
                    "INSERT INTO userchannel (channel_id,user_id,created_at) VALUES(?,?,?)"
            );

            prepare.setLong(1, userChannelObj.getChannel_id());
            prepare.setLong(2, userChannelObj.getUser_id());
            prepare.setTimestamp(3, userChannelObj.getUserChannelCreatedAt());

            prepare.executeUpdate();

            return new ResponseMessage<UserChannel>(userChannelObj, ResponseMessage.messages.USER_ADD_TO_A_CHANNEL, 200);

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            return new ResponseMessage<UserChannel>(null, ResponseMessage.messages.ERR_INFO_USER, 409);
        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<UserChannel>(null, ResponseMessage.messages.ERR_BDD, 500);
        }
    }

    @Override
    public ResponseMessage<UserChannel> update(UserChannel userChannelObj) {
        try {

            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "UPDATE userchannel SET channel_id = '" + userChannelObj.getChannel_id() + "', "
                            + "user_id = '" + userChannelObj.getUser_id() + "' "
                            + "WHERE userChannelId = " + userChannelObj.getUserChannelId()
            );

            userChannelObj = this.find(userChannelObj.getChannelId()).getData();

            return new ResponseMessage<UserChannel>(userChannelObj, ResponseMessage.messages.CHANNEL_UPDATE, 200);

        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<UserChannel>(null, ResponseMessage.messages.ERR_BDD, 500);
        }
    }

    @Override
    public ResponseMessage<UserChannel> delete(long userChannelId) {
        try {

            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "DELETE FROM userchannel WHERE id = " + userChannelId
            );

            return new ResponseMessage<UserChannel>(null, ResponseMessage.messages.CHANNEL_DELETE, 200);

        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<UserChannel>(null, ResponseMessage.messages.ERR_BDD, 500);
        }
    }
}
*/
