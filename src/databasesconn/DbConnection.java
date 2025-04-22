package databasesconn;

import java.sql.*;

public class DbConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/java_shop";
            String user = "test";
            String password = "test";
            conn = DriverManager.getConnection(url, user, password);
            return conn;

        }
        catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        }
    }
}