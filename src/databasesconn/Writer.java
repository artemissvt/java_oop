package databasesconn;

import java.sql.Connection;

public class Writer {
    public static void main(String[] args) {
        Connection conn = DbConnection.getConnection();
        if (conn != null) {
            System.out.println("Database connection established");
        } else {
            System.out.println("Database connection not established");
        }
    }
}
