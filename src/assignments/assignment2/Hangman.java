package assignments.assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hangman");

        JPanel entirePanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(40, 0, 0, 0);
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel welcome = new JLabel("Welcome to Hangman game - please select log in/ sign up");
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(welcome);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel buttonPanel = new JPanel();
        JButton signupbtn = new JButton("Sign up");
        JButton loginbtn = new JButton("Log in");
        buttonPanel.add(signupbtn);
        buttonPanel.add(loginbtn);
        centerPanel.add(buttonPanel);

        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.revalidate();

                JPanel loginPanel = new JPanel();
                loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
                loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                JLabel usernameLabel = new JLabel("Username:");
                JTextField usernameField = new JTextField(20);
                loginPanel.add(usernameLabel);
                loginPanel.add(usernameField);
                usernameField.setMaximumSize(new Dimension(200, 30));
                loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel passwordLabel = new JLabel("Password:");
                JPasswordField passwordField = new JPasswordField(15);
                loginPanel.add(passwordLabel);
                loginPanel.add(passwordField);
                passwordField.setMaximumSize(new Dimension(200, 30));
                loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JButton submitBtn = new JButton("Submit");
                loginPanel.add(submitBtn);
                submitBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        frame.revalidate();

                    }
                });

                frame.add(loginPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        signupbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.revalidate();

                JPanel signupPanel = new JPanel();
                signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.Y_AXIS));
                signupPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                JLabel usernameLabel = new JLabel("Username:");
                JTextField usernameField = new JTextField(20);
                signupPanel.add(usernameLabel);
                signupPanel.add(usernameField);
                usernameField.setMaximumSize(new Dimension(200, 30));
                signupPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel passwordLabel = new JLabel("Password:");
                JPasswordField passwordField = new JPasswordField(15);
                signupPanel.add(passwordLabel);
                signupPanel.add(passwordField);
                passwordField.setMaximumSize(new Dimension(200, 30));
                signupPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JButton submitBtn = new JButton("Submit");
                signupPanel.add(submitBtn);
                submitBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        frame.revalidate();
                        try {
                            Connection conn = LogIn.getConnection();
                            String Username = usernameField.getText();
                            String UserPassword = passwordField.getText();

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
                });

                frame.add(signupPanel);
                frame.revalidate();
                frame.repaint();
            }
        });
        /*try {
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
        }*/

        entirePanel.add(centerPanel, gbc);
        frame.add(entirePanel);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
