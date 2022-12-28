package org.example.DBConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.example.DBConnection.ConfigPostgreSQL.*;


public class DBConnection {
    /**
     * Использование ThreadLocal позволяет привести 1 соединение на 1 поток против 1 соединения на все потоки (многопоточность)
     * потокобезопасность не в ущерб производительности (в отличие от synchronized+volatile)
     * - Возможна утечка памяти и утечка информации при синхронизации потоков
     * Подробнее https://www.programmersought.com/article/67986156359/
     */
    public static Connection getConnection()
    {
        Connection con = null;
        String url = "jdbc:"+databaseSQL+"://"+host+":"+port+"/"+database;
        String username = user;
        String password = pswd;


        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection is created.");
        }
        catch (SQLException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
