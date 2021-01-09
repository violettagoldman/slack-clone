package com.dao.impl;

import com.bean.Channel;
import com.bean.User;
import com.dao.DAO;
import com.helpers.PasswordHelper;
import org.apache.commons.cli.Option;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User> {

    public Optional<User> find(long id) throws SQLException {

        ResultSet result = this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeQuery(
                "SELECT * FROM user WHERE id = " + id
        );

        if (result.first()) {
            return Optional.of(new User(
                    id,
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("hashed_password"),
                    result.getTimestamp("created_at"),
                    result.getString("icone")
            ));
        }
        return Optional.empty();
    }

    /**
     * Find all registered users
     * @return List of all users
     * @throws SQLException
     */
    public Optional<List<User>> findAll() throws SQLException {

        ResultSet result = this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeQuery(
                "SELECT * FROM user"
        );

        List<User> users = new ArrayList<>();

        while (result.next()) {
            User user = new User(
                    result.getLong("id"),
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("hashed_password"),
                    result.getTimestamp("created_at"),
                    result.getString("icone")
            );
            users.add(user);
        }

        return Optional.of(users);

    }

    /**
     * Find user with email
     * @param email
     * @return User attached to the email
     * @throws SQLException
     */
    public Optional<User> findWithEmail(String email) throws SQLException {

        ResultSet result = this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeQuery(
                "SELECT * FROM user WHERE email = '" + email + "'"
        );

        if (result.first()) {
            return Optional.of(new User(
                    result.getLong("id"),
                    result.getString("username"),
                    email,
                    result.getString("hashed_password"),
                    result.getTimestamp("created_at"),
                    result.getString("icone")

            ));
        }

        return Optional.empty();
    }

    /**
     * Find user with username
     * @param username
     * @return User attached to the username
     * @throws SQLException
     */
    public Optional<User> findWithUsername(String username) throws SQLException {

        ResultSet result = this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeQuery(
                "SELECT * FROM user WHERE username = '" + username + "'"
        );

        if (result.first()) {
            return Optional.of(new User(
                    result.getLong("id"),
                    username,
                    result.getString("email"),
                    result.getString("hashed_password"),
                    result.getTimestamp("created_at"),
                    result.getString("icone")
            ));
        }

        return Optional.empty();

    }

    public Optional<User> create(User userObj) throws SQLException, NoSuchAlgorithmException {

        PreparedStatement prepare = this.connect.prepareStatement(
                "INSERT INTO user (username,email,hashed_password,created_at,icone) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS
        );

        userObj.setPassword(PasswordHelper.hashPassword(userObj.getPassword()));

        prepare.setString(1, userObj.getUsername());
        prepare.setString(2, userObj.getEmail());
        prepare.setString(3, userObj.getPassword());
        prepare.setTimestamp(4, userObj.getCreatedAt());
        prepare.setString(5, userObj.getIcone());

        prepare.executeUpdate();

        ResultSet rs = prepare.getGeneratedKeys();
        if (rs.next()) {
            userObj.setId(rs.getLong(1));
            return Optional.of(userObj);
        }

        return Optional.empty();
    }

    public Optional<User> update(User userObj) throws SQLException {

        this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeUpdate(
                "UPDATE user SET username = '" + userObj.getUsername() + "', "
                        + "email = '" + userObj.getEmail() + "', "
                        + "hashed_password = '" + userObj.getPassword() + "' "
                        + "WHERE id = " + userObj.getId()
        );

        return Optional.of(userObj);
    }

    public Optional<User> updateIcone(User userObj) throws SQLException {
        this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeUpdate(
                "UPDATE user SET icone = '" + userObj.getIcone() + "' "
                    + "WHERE id = " + userObj.getId()
        );

        return Optional.of(userObj);
    }

    public void delete(long id) throws SQLException {
        this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeUpdate(
                "DELETE FROM user WHERE id = " + id
        );
    }
    public Optional<List<User>> findUsersByChannelID(long channelID) throws SQLException{
        ResultSet result = this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeQuery(
                "SELECT * FROM user u WHERE EXISTS (SELECT * from userchannel uch WHERE u.id=uch.user_id && uch.channel_id="+ channelID+")"
        );

        List<User> users = new ArrayList<>();

        while (result.next()) {
            User user = new User(
                    result.getLong("id"),
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("hashed_password"),
                    result.getTimestamp("created_at"),
                    result.getString("icone")
            );
            users.add(user);
        }

        return Optional.of(users);

    }
}