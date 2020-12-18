*/package com.dao.concret;

import com.bean.User;
import com.controller.UserController;
import com.dao.DAO;
import com.helpers.PasswordHelper;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
=======
import java.sql.SQLIntegrityConstraintViolationException;
>>>>>>> a870586e528964178f59cf0f76024e4a96efb1d4

public class UserDAO implements DAO<User> {

    public User find(long id) throws SQLException {

        ResultSet result = this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeQuery(
                "SELECT * FROM user WHERE id = " + id
        );

        if (result.first()) {
            return new User(
                    id,
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("hashed_password"),
                    result.getTimestamp("created_at")
<<<<<<< HEAD
            ));
        }
        return Optional.empty();
    }

    /**
     * Find all registered users
     * @return List of all users
     * @throws SQLException
     */
    public Optional<List<User>> findAll () throws SQLException { // OPTIONAL AVEC LIST

        ResultSet result = this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeQuery(
                "SELECT * FROM user"
        );

        List<User> users = new ArrayList<>();

        while(result.next()) {
            User user = new User(
                    result.getLong("id"),
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("hashed_password"),
                    result.getTimestamp("created_at")
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

        if(result.first()) {
            return Optional.of(new User(
                    result.getLong("id"),
                    result.getString("username"),
                    email,
                    result.getString("hashed_password"),
                    result.getTimestamp("created_at")
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

        if(result.first()) {
            return Optional.of(new User(
                    result.getLong("id"),
                    username,
                    result.getString("email"),
                    result.getString("hashed_password"),
                    result.getTimestamp("created_at")
            ));
        }

        return Optional.empty();

    }

    public Optional<User> create(User userObj) throws SQLException, NoSuchAlgorithmException {
=======
            );
        }
        return null;
    }
    public ResponseMessage<User> create(User userObj) {

        if (this.isUsernameValid(userObj.getUsername()) && this.isEmailValid(userObj.getEmail()) && this.isPasswordValid(userObj.getPassword())) {

            try {
                PreparedStatement prepare = this.connect.prepareStatement(
                        "INSERT INTO user (username,email,hashed_password,created_at) VALUES(?,?,?,?)"
                );

                prepare.setString(1,userObj.getUsername());
                prepare.setString(2,userObj.getEmail());
                prepare.setString(3, PasswordHelper.hashPassword(userObj.getPassword()));
                prepare.setTimestamp(4,userObj.getCreatedAt());

                prepare.executeUpdate();
                userObj = this.findWithEmail(userObj.getEmail()).getData();

                return new ResponseMessage<User>(userObj, ResponseMessage.messages.USER_CREATE, 200);

            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println(e);
                return new ResponseMessage<User>(null, ResponseMessage.messages.USER_ALREADY_EXISTS, 409);
            } catch (SQLException e) {
                System.out.println(e);
                return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_BDD, 500);
            } catch (NoSuchAlgorithmException e){
                System.out.println(e);
                return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_HASHING, 500);
            }
        } else {
            return new ResponseMessage<User>(null, ResponseMessage.messages.ERR_INFO_USER,000);
        }
    }
>>>>>>> a870586e528964178f59cf0f76024e4a96efb1d4

        PreparedStatement prepare = this.connect.prepareStatement(
                "INSERT INTO user (username,email,hashed_password,created_at) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS
        );

        prepare.setString(1,userObj.getUsername());
        prepare.setString(2,userObj.getEmail());
        prepare.setString(3, PasswordHelper.hashPassword(userObj.getPassword()));
        prepare.setTimestamp(4,userObj.getCreatedAt());

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

<<<<<<< HEAD
        return this.find(userObj.getId());
    }

    public void delete(long id) throws SQLException {

        this.connect.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        ).executeUpdate(
                "DELETE FROM user WHERE id = " + id
        );

    }
=======

}

>>>>>>> a870586e528964178f59cf0f76024e4a96efb1d4

