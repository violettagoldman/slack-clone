package com.dao;

import com.bean.ResponseMessage;
import com.jdbc.ConnectionSQL;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface DAO<T> {

    public Connection connect = ConnectionSQL.getInstance();

    /**
     * Find object with ID
     * @param id
     * @return
     */
    public abstract Optional<T> find(long id) throws SQLException;

    /**
     * Create object in database
     * @param obj
     * @return
     */
<<<<<<< HEAD
    public abstract Optional<T> create(T obj) throws NoSuchAlgorithmException, SQLException;
=======
    public abstract Optional<T> create(T obj) throws SQLException;
>>>>>>> a870586e528964178f59cf0f76024e4a96efb1d4

    /**
     * Update object in database
     * @param obj
     * @return
     */
    public abstract Optional<T> update(T obj) throws SQLException;

    /**
     * Delete object in database
     * @param id
     */
<<<<<<< HEAD
    public abstract void delete(long id) throws SQLException;
=======
    public abstract Optional<T> delete(long id) throws SQLException;
>>>>>>> a870586e528964178f59cf0f76024e4a96efb1d4

}
