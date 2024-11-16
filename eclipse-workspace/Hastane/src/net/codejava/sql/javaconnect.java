package net.codejava.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class javaconnect {
    public Connection connDB() {
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS01:1433;databaseName=Hastane;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "A2l5p9e4";
        Connection c = null;
        
        try {
            c = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("theres a database connection error:");
            e.printStackTrace();
        }
        
        return c;
    }
}
