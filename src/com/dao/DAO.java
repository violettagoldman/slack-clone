package com.dao;

import com.jdbc.ConnectionSQL;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface DAO<T> {

    Connection connect = ConnectionSQL.getInstance();

    /**
     * Find object with ID
     * @param id
     * @return optional
     */
    Optional<T> find(long id) throws SQLException;

    /**
     * Create object in database
     * @param obj
     * @return optional
     */
    Optional<T> create(T obj) throws NoSuchAlgorithmException, SQLException;

    /**
     * Update object in database
     * @param obj
     * @return optional
     */
    Optional<T> update(T obj) throws SQLException;

    /**
     * Delete object in database
     * @param id
     */
    void delete(long id) throws SQLException;

}
