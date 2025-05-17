package assignments.assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hangman");

        frame.setLayout(new BorderLayout());

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
        JButton leaderboard = new JButton("View Leaderboard");

        buttonPanel.add(leaderboard);
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
                        String inputUsername = usernameField.getText();
                        String inputPassword = new String(passwordField.getPassword());
                        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid username/password");
                        } else {
                            try {
                                Connection conn = LogIn.getConnection();
                                String query = "SELECT UserPassword FROM userinfo WHERE Username = ?";
                                PreparedStatement q = conn.prepareStatement(query);
                                q.setString(1, inputUsername);
                                ResultSet rs = q.executeQuery();

                                if (rs.next()) {
                                    String dbPassword = rs.getString("UserPassword");
                                    if (dbPassword.equals(inputPassword)) {

                                        String userIdQuery = "SELECT UserID FROM userinfo WHERE Username = ?";

                                        PreparedStatement idStmt = conn.prepareStatement(userIdQuery);
                                        idStmt.setString(1, inputUsername);
                                        ResultSet idRs = idStmt.executeQuery();

                                        if (idRs.next()) {
                                            Session.setCurrentUserID(idRs.getInt("UserID"));
                                        }
                                        idRs.close();
                                        idStmt.close();

                                        JDialog dialog = new JDialog(frame, "Login", true);
                                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                                        JPanel dialogPanel = new JPanel();
                                        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
                                        dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                        JLabel messageLabel = new JLabel("You're successfully logged in");
                                        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                                        dialogPanel.add(messageLabel);

                                        dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                        JButton continueButton = new JButton("Continue");
                                        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                                        dialogPanel.add(continueButton);

                                        continueButton.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                dialog.dispose();

                                                frame.getContentPane().removeAll();
                                                frame.repaint();
                                                frame.revalidate();

                                                int userID = -1;
                                                try  (Connection conn = LogIn.getConnection();
                                                    PreparedStatement q = conn.prepareStatement("SELECT UserID FROM userinfo WHERE Username = ? AND UserPassword = ?")) {
                                                    q.setString(1, inputUsername);
                                                    q.setString(2, inputPassword);
                                                    ResultSet rs = q.executeQuery();

                                                    if (rs.next()) {
                                                        userID = rs.getInt("UserID");
                                                        WelcomeWindow.Session.setCurrentUserID(userID);
                                                        WelcomeWindow.Session.setCurrentUsername(inputUsername);
                                                    }
                                                } catch (SQLException se) {
                                                    System.out.println(se.getMessage());
                                                }

                                                if (userID > 0) {
                                                    HangmanGame.showHangmanGame(frame);
                                                }

                                                frame.revalidate();
                                                frame.repaint();
                                            }
                                        });

                                        dialog.getContentPane().add(dialogPanel);
                                        dialog.pack();
                                        dialog.setLocationRelativeTo(frame);
                                        dialog.setVisible(true);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Wrong password");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "User not found");
                                }

                            } catch (SQLException se) {
                                System.out.println(se.getMessage());
                            }
                        }
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

                frame.add(signupPanel);
                frame.revalidate();
                frame.repaint();

                submitBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        frame.revalidate();
                        String inputUsername = usernameField.getText();
                        try {
                            Connection conn = LogIn.getConnection();
                            String query = "SELECT UserPassword FROM userinfo WHERE Username = ?";
                            PreparedStatement q = conn.prepareStatement(query);
                            q.setString(1, inputUsername);
                            ResultSet rs = q.executeQuery();
                            if (rs.next()) {
                                JOptionPane.showMessageDialog(null, "This username already exists");
                            } else {
                                try {
                                    String Username = usernameField.getText();
                                    String UserPassword = passwordField.getText();

                                    String query2 = "INSERT INTO userinfo (Username, UserPassword) VALUES (?,?)";
                                    PreparedStatement ps = conn.prepareStatement(query2);
                                    ps.setString(1, Username);
                                    ps.setString(2, UserPassword);
                                    ps.executeUpdate();


                                    String querygetid = "SELECT UserID FROM userinfo WHERE Username = ?";
                                    PreparedStatement getidStmt = conn.prepareStatement(querygetid);
                                    getidStmt.setString(1, Username);
                                    ResultSet idRs = getidStmt.executeQuery();

                                    int userID = -1;
                                    if (idRs.next()) {
                                        userID = idRs.getInt("UserID");
                                    }
                                    idRs.close();
                                    getidStmt.close();

                                    if (userID != -1) {
                                        String query3 = "INSERT INTO userscore (UserWins, UserLosses, UserID) VALUES (0, 0, ?)";
                                        PreparedStatement q3 = conn.prepareStatement(query3);
                                        q3.setInt(1, userID);
                                        q3.executeUpdate();
                                        q3.close();
                                    }

                                    JDialog dialog = new JDialog(frame, "Sign up", true);
                                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                                    JPanel dialogPanel = new JPanel();
                                    dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
                                    dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                    JLabel messageLabel = new JLabel("You're successfully signed up");
                                    messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                                    dialogPanel.add(messageLabel);

                                    dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                    JButton continueButton = new JButton("Back to main menu for log in");
                                    continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                                    dialogPanel.add(continueButton);


                                    continueButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            dialog.dispose();
                                            WelcomeWindow.showWelcomeWindow(frame);
                                        }
                                    });

                                    dialog.getContentPane().add(dialogPanel);
                                    dialog.pack();
                                    dialog.setLocationRelativeTo(frame);
                                    dialog.setVisible(true);

                                } catch (SQLException se) {
                                    System.out.println(se.getMessage());
                                }
                            }
                        } catch (SQLException se) {
                            System.out.println(se.getMessage());
                        }

                        frame.add(signupPanel);
                        frame.revalidate();
                        frame.repaint();
                    }
                });
            }
        });

        leaderboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.revalidate();

                JPanel leaderboardPanel = new JPanel();
                leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));
                leaderboardPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                leaderboardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                JLabel title = new JLabel("Leaderboard - top five players");
                title.setAlignmentX(Component.CENTER_ALIGNMENT);
                leaderboardPanel.add(title);
                leaderboardPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                try {
                    Connection conn = LogIn.getConnection();
                    String sql = "SELECT ui.Username, us.UserWins, us.UserLosses " +
                            "FROM userscore us " +
                            "JOIN userinfo ui ON us.UserID = ui.UserID " +
                            "ORDER BY us.UserWins DESC, us.UserLosses ASC " +
                            "LIMIT 5";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        JPanel userPanel = new JPanel();
                        userPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

                        String username = rs.getString("Username");
                        int wins = rs.getInt("UserWins");
                        int losses = rs.getInt("UserLosses");


                        JLabel usernameLabel = new JLabel("Username: " + username);
                        JLabel winsLabel = new JLabel("Wins: " + wins);
                        JLabel lossesLabel = new JLabel("Losses: " + losses);

                        userPanel.add(usernameLabel);
                        userPanel.add(winsLabel);
                        userPanel.add(lossesLabel);

                        leaderboardPanel.add(userPanel);
                        leaderboardPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                    }
                } catch (SQLException se) {
                    System.out.println(se.getMessage());
                }

                JButton backBtn = new JButton("Back");
                backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                leaderboardPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                leaderboardPanel.add(backBtn);

                backBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        WelcomeWindow.showWelcomeWindow(frame);
                    }
                });
                frame.add(leaderboardPanel, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
            }
        });

        entirePanel.add(centerPanel, gbc);
        frame.add(entirePanel);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public class Session {
            private static String currentUsername;
            private static int currentUserID;

            public static String getCurrentUsername() {
                return currentUsername;
            }

            public static void setCurrentUsername(String username) {
                currentUsername = username;
            }

            public static void setCurrentUserID(int userID) {
                currentUserID = userID;
            }

            public static int getCurrentUserID() {
                return currentUserID;
            }

    }

}

