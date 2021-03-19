/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zavierdev.crudsiswa.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tech
 */
public class DatabaseConnection {
    private static DatabaseConnection instance = null;
    
    private final String driverClassname = "com.mysql.cj.jdbc.Driver";
    private Connection connection = null;
    
    private final String host = "127.0.0.1";
    private final String port = "3306";
    private final String database = "SchoolDB";
    private final String username = "root";
    private final String password = "";
    
    private final String url = "jdbc:mysql://"+host+":"+port+"/"+database;
    
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized(DatabaseConnection.class) {
                instance = new DatabaseConnection();
            }
        }
        
        return instance;
    }
    
    public Connection get() {
        try {
            Class.forName(driverClassname);
            connection = DriverManager.getConnection(url, username, password);
        } catch(ClassNotFoundException | SQLException e) {
            System.err.println("Database Connection Error");
        }
        
        return connection;
    }
    
    public void close() throws SQLException {
        if(connection != null) {
            connection.close();
        }
    }
}
