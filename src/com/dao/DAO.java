package com.dao;

import com.bean.ResponseMessage;
import com.jdbc.ConnectionSQL;

import java.sql.Connection;

public abstract class DAO<T> {

    public Connection connect = ConnectionSQL.getInstance();

    /**
     * Find object with ID
     * @param id
     * @return
     */
    public abstract ResponseMessage<T> find(long id);

    /**
     * Create object in database
     * @param obj
     * @return
     */
    public abstract ResponseMessage<T> create(T obj);

    /**
     * Update object in database
     * @param obj
     * @return
     */
    public abstract ResponseMessage<T> update(T obj);

    /**
     * Delete object in database
     * @param id
     */
    public abstract ResponseMessage<T> delete(long id);

}
