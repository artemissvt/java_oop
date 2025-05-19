package assignments.assignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LogIn {
    public static Connection getConnection() {

        // initialize connection with the database
        Connection conn = null;
        try {

            // connect the db url and the user with its password ( already made on the database)
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
