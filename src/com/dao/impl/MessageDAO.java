package com.dao.impl;

import com.dao.DAO;
import com.bean.Message;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageDAO implements DAO<Message> {

    public Optional<List<Message>> findChannelMessages (long  channelID) throws SQLException{
        ResultSet result = this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeQuery(
                "SELECT * FROM channelmessage WHERE channel_id = " + channelID+";"
        );

        List messages = new ArrayList();
        while(result.next()){
            messages.add(new Message(
                    result.getLong("id"),
                    result.getLong("transmitter_id"),
                    result.getLong("channel_id"),
                    result.getTimestamp("created_at"),
                    result.getString("message"),
                    result.getBoolean("is_smiley"))
            );
        }
        if (messages.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(messages);
    }

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
                    result.getString("message"),
                    result.getBoolean("is_smiley"))
            );
        }
        return Optional.empty();
    }

    public Optional<Message> create(Message messageObj) throws NoSuchAlgorithmException, SQLException {

        PreparedStatement prepare = this.connect.prepareStatement(
                "INSERT INTO channelmessage (transmitter_id,channel_id,created_at,message,is_smiley) VALUES(?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS
                );

        prepare.setLong(1, messageObj.getTransmitterID());
        prepare.setLong(2, messageObj.getChannelID());
        prepare.setTimestamp(3,messageObj.getCreatedAt());
        prepare.setString(4,messageObj.getMessage());
        prepare.setBoolean(5,messageObj.isSmiley());

        prepare.executeUpdate();

        ResultSet rs = prepare.getGeneratedKeys();
        if (rs.next()){
            messageObj.setMessageID(rs.getLong(1));
        }

        return Optional.of(messageObj);
    }

    public Optional<Message> update(Message messageObj) throws SQLException {

        this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeUpdate(
                "UPDATE channelmessage SET transmitter_id = '" + messageObj.getTransmitterID() + "', "
                        + "channel_id = '" + messageObj.getChannelID() + "', "
                        + "message = '" + messageObj.getMessage() + "' "
                        + "WHERE id = " + messageObj.getMessageID()
        );

        return this.find(messageObj.getMessageID());
    }

    public void delete(long messageId) throws SQLException {

        this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeUpdate(
                "DELETE FROM channelmessage WHERE id = " + messageId
        );

    }

    public void deleteAllFromChannel(long channelID) throws SQLException {
        this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeUpdate(
                "DELETE FROM channelmessage WHERE channel_id = " + channelID
        );
    }
}
