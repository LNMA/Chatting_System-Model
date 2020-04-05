package com.louay.projects.model.util.pool;


import com.louay.projects.model.util.queue.MyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.*;

@Component("pool")
@Scope("singleton")
public class MyConnectionPool {

    @Autowired
    @Qualifier("dbConfig")
    private DBConnectionConfig db;

    @Autowired
    @Qualifier("queue")
    private MyList<ConnectionWrapper> connection;

    private ConnectionWrapper wrapper;



    public MyConnectionPool() {
    }


    public ConnectionWrapper getConnection() throws SQLException {
        if (this.connection.isEmpty()) {
            return new ConnectionWrapper(getWrapperConnection(this.db.getUrl(), this.db.getUsername(), this.db.getPassword()));
        } else {
            ConnectionWrapper connectionWrapper = this.connection.dequeue();
            if (connectionWrapper.isAlive()) {
                return connectionWrapper;
            } else {
                connectionWrapper.getConnection().close();
                return getConnection();
            }
        }
    }

    public Connection getWrapperConnection(String url, String username, String password) {
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException | IllegalAccessException | InstantiationException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void release(ConnectionWrapper connectionWrapper){
        this.connection.enqueue(connectionWrapper);
    }
    

    public ResultSet selectResult(String query, Object...key) throws SQLException {
        ResultSet resultSet;
        this.wrapper = this.getConnection();
        PreparedStatement select = this.wrapper.getConnection().prepareStatement(query);
        for (int i = 0; i < key.length; i++) {
            if (key[i] instanceof String) {
                select.setString((i + 1), (String) key[i]);
            } else if (key[i] instanceof Integer) {
                select.setInt((i + 1), (Integer) key[i]);
            } else if (key[i] instanceof Double) {
                select.setDouble((i + 1), (Double) key[i]);
            } else if (key[i] instanceof Long) {
                select.setLong((i + 1), (Long) key[i]);
            } else if (key[i] instanceof java.sql.Date) {
                select.setDate((i + 1), (java.sql.Date) key[i]);
            } else if (key[i] instanceof java.sql.Timestamp) {
                select.setTimestamp((i + 1), (java.sql.Timestamp) key[i]);
            } else if (key[i] instanceof BigDecimal) {
                select.setBigDecimal((i + 1), (BigDecimal) key[i]);
            } else if (key[i] instanceof Boolean) {
                select.setBoolean((i + 1), (Boolean) key[i]);
            } else if (key[i] instanceof java.sql.Blob) {
                select.setBlob((i + 1), (java.sql.Blob) key[i]);
            }
        }

        resultSet = select.executeQuery();
        this.release(this.wrapper);
        return resultSet;
    }

    public int updateQuery(String query,Object...objects) throws SQLException {
        int result;
        this.wrapper = this.getConnection();
        PreparedStatement update = this.wrapper.getConnection().prepareStatement(query);
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof String) {
                update.setString((i + 1), (String) objects[i]);
            } else if (objects[i] instanceof Integer) {
                update.setInt((i + 1), (Integer) objects[i]);
            } else if (objects[i] instanceof java.sql.Date) {
                update.setDate((i + 1), (java.sql.Date) objects[i]);
            } else if (objects[i] instanceof Long) {
                update.setLong((i + 1), (Long) objects[i]);
            } else if (objects[i] instanceof java.sql.Timestamp) {
                update.setTimestamp((i + 1), (java.sql.Timestamp) objects[i]);
            } else if (objects[i] instanceof java.sql.Time) {
                update.setTime((i + 1), (java.sql.Time) objects[i]);
            } else if (objects[i] instanceof Double) {
                update.setDouble((i + 1), (Double) objects[i]);
            } else if (objects[i] instanceof BigDecimal) {
                update.setBigDecimal((i + 1), (BigDecimal) objects[i]);
            } else if (objects[i] instanceof Boolean) {
                update.setBoolean((i + 1), (Boolean) objects[i]);
            } else if (objects[i] instanceof java.sql.Blob) {
                update.setBlob((i + 1), (java.sql.Blob) objects[i]);
            }
        }

        result = update.executeUpdate();
        this.release(this.wrapper);
        return result;
    }

    public java.sql.Blob initBlob(long pos, byte[] bytes){
        java.sql.Blob blob = null;
        try{
            this.wrapper = this.getConnection();
            blob = this.wrapper.getConnection().createBlob();
            blob.setBytes(pos, bytes);
            this.release(wrapper);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return blob;
    }



}
