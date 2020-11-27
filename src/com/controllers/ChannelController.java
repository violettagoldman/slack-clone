package com.controllers;

import com.models.Channel;
import com.models.ResponseMessage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class ChannelController extends Controller {

    private ChannelController() {
        super();
    }

    public static ResponseMessage<Channel> createChannel(Channel unChannel)  {
        try {
            String createChannelString = "INSERT INTO channel VALUES ('"+unChannel.getCreatedAt()+"','"+unChannel.getAdmin_user_id()+"','"+unChannel.getChannelName()+"');";
            PreparedStatement createChannel = connexion.prepareStatement(createChannelString);
            createChannel.setTimestamp(1, java.sql.Timestamp.valueOf(unChannel.getCreatedAt()));
            createChannel.setInt(2, unChannel.getAdmin_user_id());
            createChannel.setString(3, unChannel.getChannelName());
            createChannel.executeUpdate();

            return new ResponseMessage<Channel>(unChannel, "Channel crée avec succès", 200);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            return new ResponseMessage<Channel>(null, "Channel déjà existant", 409);
        } catch (SQLException e) {
            System.out.println(e);
            return new ResponseMessage<Channel>(null, "Err BDD ", 500);
        }
    }

    public static void updateChannel(Channel unChannel, String newName)  {
        try {
            String updateChannelString = "UPDATE channel SET name = '"+newName+"' WHERE id = '"+unChannel.getChannelId()+"';";
            PreparedStatement updateChannel = connexion.prepareStatement(updateChannelString);
            updateChannel.executeUpdate();

            System.out.println("Channel update avec succès");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void deleteChannel(Channel unChannel)  {
        try {
            String deleteChannelString = "DELETE FROM channel WHERE id = '"+unChannel.getChannelId()+"';";
            PreparedStatement deleteChannel = connexion.prepareStatement(deleteChannelString);
            deleteChannel.executeUpdate();

            System.out.println("Channel supprimé avec succès");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
