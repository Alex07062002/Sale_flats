package org.example.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.example.DBConnection.ConfigPostgreSQL.*;

/**
 * Создание Connection Pool обуславливается
 * 1) Отсутствие необходимости создавать Connection каждый раз, а брать уже готовый (увеличение производительности)
 * 2) Реализация Singleton pattern для Connection pool
 * !!! Также существует реализация с DataSource, но в нашем случае это не интересно, так как база данных одна.
 * Об этом можно почитать: https://www.digitalocean.com/community/tutorials/java-datasource-jdbc-datasource-example
 */

public class ConnectionPool{
    private static volatile ConnectionPool connectionPool;

    private final String databaseURL = "jdbc:"+databaseSQL+"://"+host+":"+port+"/"+database;
    private final String userName = user;
    private final String password = pswd;

    private final int maxCon = 5;
    private int numCon;

    Stack<Connection> freePool = new Stack<>();
    Set<Connection> occupiedPool = new HashSet<>();

    private ConnectionPool(){}

    public static  ConnectionPool getInstance(){
        if (connectionPool == null){
            synchronized (ConnectionPool.class){
                if (connectionPool == null){
                    connectionPool = new ConnectionPool();
                }
            }
        }
        return connectionPool;
    }

    public  Connection getConnection() throws SQLException{
        Connection connection;
        if (isFull()) {
            throw new SQLException("The connection pool is full.");
        }
        connection = getConnectionFromPool();
        if (connection == null){
            connection = createNewConnectionForPool();
        }
        return connection;
    }
    public void returnConnection(Connection connection) throws SQLException{
        if (connection == null){
            throw new NullPointerException();
        }
        if (!occupiedPool.remove(connection)){
            throw new SQLException("Error");
        }
        freePool.push(connection);
    }
    private synchronized boolean isFull(){
        return ((freePool.size() == 0) && (numCon>=maxCon));
    }
    private Connection createNewConnectionForPool() throws SQLException {
        Connection connection = createNewConnection();
        numCon++;
        occupiedPool.add(connection);
        return connection;
    }
    private synchronized Connection createNewConnection() throws SQLException{
        Connection connection;
        connection = DriverManager.getConnection(databaseURL,userName,password);
        return connection;
    }
    private Connection getConnectionFromPool(){
        Connection connection = null;
        if (freePool.size()>0){
            connection = freePool.pop();
            occupiedPool.add(connection);
        }
        return connection;
    }
}
