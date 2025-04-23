package assignments.assignment2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        try {
            Connection conn = LogIn.getConnection();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Username: ");
            String Username = scanner.nextLine();
            System.out.print("Password: ");
            String UserPassword = scanner.nextLine();

            String query = "INSERT INTO userinfo (Username, UserPassword) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, Username);
            ps.setString(2, UserPassword);
            ps.executeUpdate();
            System.out.println("done");

        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }
}
