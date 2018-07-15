package com.andryha.storage.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private static final String URL = "jdbc:mysql://localhost/mysql_storage";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection=null;

    public static Connection openConnection() {
        if (connection==null){
            try {
                connection= DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Проблема соединения");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection(){
       if (connection!=null){
           try {
               connection.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }
}
