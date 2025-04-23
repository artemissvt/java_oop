package assignments.assignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LogIn {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/hangmangame";
            String user = "hangmangame";
            String password = "hangmangame";
            conn = DriverManager.getConnection(url, user, password);
            return conn;

        }
        catch (SQLException se) {
            System.out.println(se.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
    }
}
