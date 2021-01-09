package com.controllers;

import com.bean.Channel;
import com.bean.User;
import com.bean.UserChannel;
import com.dao.impl.ChannelDAO;
import com.bean.ResponseMessage;
import com.dao.impl.UserChannelDAO;
import com.dao.impl.UserDAO;
import com.invoker.decorators.ControllerRoute;
import com.invoker.decorators.MethodRoute;
import com.mysql.cj.x.protobuf.MysqlxExpr;
import com.sun.mail.iap.Response;
import org.apache.commons.cli.Option;

import javax.sound.midi.Soundbank;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bean.ResponseMessage.Messages.*;
import static com.helpers.RegexHelper.isChannelNameValid;

@ControllerRoute("channels")
public class ChannelController extends Controller {

    private final static ChannelDAO channelDAO = new ChannelDAO();
    private final static UserChannelDAO userChannelDAO = new UserChannelDAO();
    private final static UserDAO userDAO = new UserDAO();

    @MethodRoute("find")
    public static ResponseMessage find(long id) throws SQLException {
        Optional channelOp = channelDAO.find(id);

        if (channelOp.isEmpty()) {
            return new ResponseMessage(null, CHANNEL_NOT_FOUND, 400);
        }

        return new ResponseMessage(channelOp.get(), CHANNEL_FOUND, 200);

    }



    @MethodRoute("findAll")
    public static ResponseMessage findAll() throws SQLException {

        List<Channel> channelsOp = channelDAO.findAll().get();

        if (channelsOp.size() < 1) {
            return new ResponseMessage(null, NO_CHANNEL_IN_DB, 400);
        }

        return new ResponseMessage(channelsOp, ALL_CHANNELS_FOUND, 200);

    }

    @MethodRoute("getchannelsbyuserid")
    public static  ResponseMessage getChannelsByUserId(long id) throws SQLException {
        Optional channelOp = channelDAO.findChannelsbyUserId(id);

        if (channelOp.isEmpty()) {
            return new ResponseMessage(null, CHANNEL_NOT_FOUND, 400);
        }

        return new ResponseMessage(channelOp.get(), CHANNEL_FOUND, 200);
    }


    @MethodRoute("create")
    public static  ResponseMessage create(Channel channelObj) throws SQLException, NoSuchAlgorithmException {
        Optional channelOp;
        try{
            channelOp = channelDAO.create(channelObj);
            if (channelOp.isEmpty()) {
                return new ResponseMessage(null, ERROR_CREATION_CHANNEL, 400);
            }
            Channel channel = (Channel)channelOp.get();
            userChannelDAO.create(new UserChannel(
                channel.getID(),channel.getAdminUserId(),channel.getCreatedAt()
            ));
            return new ResponseMessage(channel, CHANNEL_CREATED, 200);
        }catch (Exception e){
            return new ResponseMessage(null, ERROR_CREATION_CHANNEL, 400);
        }
    }

    @MethodRoute("delete")
    public static ResponseMessage delete(long id) throws SQLException {
        channelDAO.delete(id);
        userChannelDAO.deleteByChannelID(id);
        return new ResponseMessage(null, CHANNEL_DELETED, 200);
    }

    @MethodRoute("updateinformation")
    public ResponseMessage updateInformation(Channel actualChannel, String name, long adminId) throws SQLException {

            // We check if the information is valid
        if (!isChannelNameValid(name)) {
            return new ResponseMessage(null, CHANNELNAME_NOT_VALID, 400);
        }

            // We check if values to update are null
        if (name == null) {
            name = actualChannel.getName();
        }
        if (adminId == 0) {
            adminId = actualChannel.getAdminUserId();
        }

            // We update the user in DB
        Channel channelToUpdate = new Channel(actualChannel.getID(), adminId, name);
        Optional updatedChannel = channelDAO.update(channelToUpdate);

        if (updatedChannel.isEmpty()) {
            return new ResponseMessage(null, ERROR_UPDATE_CHANNEL, 400);
        }

        return new ResponseMessage(updatedChannel.get(), INFORMATION_CHANNEL_UPDATED, 200);
    }

    @MethodRoute("addusertochannel")
    public ResponseMessage addUserToChannel(String nickname, long channelID ) throws SQLException{
//        ResponseMessage responseMessage; = new ResponseMessage<Channel>();
        try{
            Optional<Channel> channelOp =channelDAO.find(channelID);
            if(channelOp.isEmpty()){
                return new ResponseMessage(channelID,CHANNEL_NOT_FOUND,404);
            }
            Optional<User> userOp = userDAO.findWithUsername(nickname);
            if(userOp.isEmpty()){
                return new ResponseMessage(channelID,USER_NOT_FOUND,404);
            }
            UserChannel userChannel = new UserChannel((userOp.get()).getId(), channelID);
            userChannelDAO.create(userChannel);
            Channel channel = channelOp.get();
            List<User> users=userDAO.findUsersByChannelID(channelID).get();
            channel.setUsers((ArrayList<User>)users);
            return new ResponseMessage(channel,USERCHANNEL_CREATED,200);
        }catch (SQLIntegrityConstraintViolationException e){
            return new ResponseMessage(channelID,USERCHANNEL_EXISTS,400);
        }catch (Exception e){
            return new ResponseMessage(channelID,ERROR_SERVER,500);
        }
    }

}
