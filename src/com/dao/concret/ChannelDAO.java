package com.dao.concret;

import com.models.Channel;
import com.dao.DAO;

import java.sql.*;
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

    public Optional<Channel> create(Channel channelObj) throws SQLException{

        if (!isChannelNameValid(channelObj.getName())) {
            return Optional.empty();
        }
        PreparedStatement prepare = this.connect.prepareStatement(
                "INSERT INTO channel (name,admin_user_id,created_at) VALUES(?,?,?)"
        );

        prepare.setString(1,channelObj.getName());
        prepare.setLong(2,channelObj.getAdminUserId());
        prepare.setTimestamp(3,channelObj.getCreatedAt());

        prepare.executeUpdate();

        ResultSet rs = prepare.getGeneratedKeys();
        if (rs.next()){
            channelObj.setID(rs.getLong(1));
        }

        return Optional.of(channelObj);

    }

    public Optional<Channel> update(Channel channelObj) throws SQLException{

            this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            ).executeUpdate(
                    "UPDATE channel SET name = '" + channelObj.getName() + "', "
                            + "admin_user_id = '" + channelObj.getAdminUserId() + "' "
                            + "WHERE id = " + channelObj.getID()
            );

            return this.find(channelObj.getID());
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