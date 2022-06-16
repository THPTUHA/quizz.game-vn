package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseKetNoi {
    public static Connection init() throws ClassNotFoundException, SQLException
    {
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        
        String dbName = "wg";
        String dbUsername = "root";
        String dbPassword = "root";
  
        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername, dbPassword);
        return con;
    }
}