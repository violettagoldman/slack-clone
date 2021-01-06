package com.threads;

import com.bean.ResponseMessage;
import com.bean.User;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.concurrent.Callable;

public class JDBDUserThreadUpdate implements Callable<ResponseMessage<User>> {
    private PreparedStatement preparedStatement;
    private User user;

    public JDBDUserThreadUpdate(PreparedStatement sqlStatement, Connection connection, User data) {
        this.preparedStatement = sqlStatement;
    }

    public ResponseMessage<User> call() throws Exception{
            return new ResponseMessage<User>(null, ResponseMessage.Messages.USER_ALREADY_EXISTS, 409);

    }
}
