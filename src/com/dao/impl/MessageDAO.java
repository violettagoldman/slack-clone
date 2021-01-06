package com.dao.impl;

import com.dao.DAO;
import com.models.Message;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessageDAO implements DAO<Message> {

    public Optional<Message> find(long messageId) throws SQLException {

        ResultSet result = this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeQuery(
                "SELECT * FROM channelmessage WHERE id = " + messageId
        );

        if (result.first()) {
            return Optional.of(new Message(
                    messageId,
                    result.getLong("transmitter_id"),
                    result.getLong("channel_id"),
                    result.getTimestamp("created_at"),
                    result.getString("message")
            ));
        }
        return Optional.empty();
    }

    public Optional<Message> create(Message messageObj) throws NoSuchAlgorithmException, SQLException {

        PreparedStatement prepare = this.connect.prepareStatement(
                "INSERT INTO channelmessage (transmitter_id,channel_id,created_at,message) VALUES(?,?,?,?)"
                );

        prepare.setLong(1, messageObj.getTransmitter_id());
        prepare.setLong(2, messageObj.getChannel_id());
        prepare.setTimestamp(3,messageObj.getMessageCreatedAt());
        prepare.setString(4,messageObj.getMessage());

        prepare.executeUpdate();

        ResultSet rs = prepare.getGeneratedKeys();
        if (rs.next()){
            messageObj.setMessageId(rs.getLong(1));
        }

        return Optional.of(messageObj);
    }

    public Optional<Message> update(Message messageObj) throws SQLException {

        this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeUpdate(
                "UPDATE channelmessage SET transmitter_id = '" + messageObj.getTransmitter_id() + "', "
                        + "channel_id = '" + messageObj.getChannel_id() + "', "
                        + "message = '" + messageObj.getMessage() + "' "
                        + "WHERE id = " + messageObj.getMessageId()
        );

        return this.find(messageObj.getMessageId());
    }

    public void delete(long messageId) throws SQLException {

        this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeUpdate(
                "DELETE FROM channelmessage WHERE id = " + messageId
        );

    }
}
