package com.dao.impl;

import com.dao.DAO;
<<<<<<< HEAD:src/com/dao/concret/UserChannelDAO.java
import com.bean.UserChannel;
=======
import com.models.UserChannel;
>>>>>>> yoann:src/com/dao/impl/UserChannelDAO.java

import java.sql.*;
import java.util.Optional;

public class UserChannelDAO implements DAO<UserChannel> {

    public Optional<UserChannel> find(long id) throws SQLException{

        ResultSet result = this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeQuery(
                "SELECT * FROM userchannel WHERE id = " + id
        );

        if (result.first()) {
            return Optional.of(new UserChannel(
                    result.getLong("userChannelId"),
                    result.getLong("channel_id"),
                    result.getLong("user_id"),
                    result.getTimestamp("created_at")
            ));
        }
        return Optional.empty();
    }

    public Optional<UserChannel> create(UserChannel userChannelObj) throws SQLException{

            PreparedStatement prepare = this.connect.prepareStatement(
                    "INSERT INTO userchannel (channel_id,user_id,created_at) VALUES(?,?,?)"
            );

            prepare.setLong(1, userChannelObj.getChannelId());
            prepare.setLong(2, userChannelObj.getUserId());
            prepare.setTimestamp(3, userChannelObj.getUserChannelCreatedAt());

            prepare.executeUpdate();

            ResultSet rs = prepare.getGeneratedKeys();
            if (rs.next()){
                userChannelObj.setUserChannelId(rs.getLong(1));
            }
        return Optional.of(userChannelObj);
    }

    public Optional<UserChannel> update(UserChannel userChannelObj) throws SQLException{

            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "UPDATE userchannel SET channel_id = '" + userChannelObj.getChannelId() + "', "
                            + "user_id = '" + userChannelObj.getUserId() + "' "
                            + "WHERE userChannelId = " + userChannelObj.getUserChannelId()
            );

            return this.find(userChannelObj.getChannel_id());
    }

    public void delete(long userChannelId) throws SQLException{
            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "DELETE FROM userchannel WHERE id = " + userChannelId
            );
    }

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
