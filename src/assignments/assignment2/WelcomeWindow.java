package assignments.assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class WelcomeWindow {
    public static Component showWelcomeWindow(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();

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

        // create the buttons for the following activities
        JButton signupbtn = new JButton("Sign up");
        JButton loginbtn = new JButton("Log in");
        JButton leaderboard = new JButton("View Leaderboard");
        JButton userscore = new JButton("User Progress");

        // and add them to the button panel
        buttonPanel.add(userscore);
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

                // create the outer panel that will contain the next panels
                JPanel outerPanel = new JPanel();
                outerPanel.setLayout(new GridBagLayout());

                // create the log in panel and align it on the center
                JPanel loginPanel = new JPanel();
                loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
                loginPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                // create the labels and the text fields for the username,
                // password input from the user
                // add them to the login panel
                // and align it on the center
                JLabel usernameLabel = new JLabel("Username:");
                usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JTextField usernameField = new JTextField(20);
                usernameField.setMaximumSize(new Dimension(200, 30));
                usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
                loginPanel.add(usernameLabel);
                loginPanel.add(usernameField);
                loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                JLabel passwordLabel = new JLabel("Password:");
                passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JPasswordField passwordField = new JPasswordField(15);
                passwordField.setMaximumSize(new Dimension(200, 30));
                passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
                loginPanel.add(passwordLabel);
                loginPanel.add(passwordField);
                loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                // create the submit button and add it to the login panel
                JButton submitBtn = new JButton("Submit");
                submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                loginPanel.add(submitBtn);
                outerPanel.add(loginPanel);

                // add the outer panel to frame, revalidate and repaint
                frame.getContentPane().add(outerPanel);
                frame.add(loginPanel);
                frame.revalidate();
                frame.repaint();

                // add action listener to the submit button
                submitBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // get username and password
                        String inputUsername = usernameField.getText();
                        String inputPassword = new String(passwordField.getPassword());

                        // in case one of the fields are empty pop up a dialogue window to let the user know that
                        // they have inputted invalid data
                        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid username/password");
                        } else {
                            // in case everything is correct start the program
                            try {
                                Connection conn = LogIn.getConnection();
                                // get user password from the userinfo table created on the db
                                String query = "SELECT UserPassword FROM userinfo WHERE Username = ?";
                                PreparedStatement q = conn.prepareStatement(query);
                                q.setString(1, inputUsername);
                                ResultSet rs = q.executeQuery();

                                if (rs.next()) {
                                    // get the password from the db
                                    String dbPassword = rs.getString("UserPassword");
                                    // if the userpassword that the user inputted matches the userpassword that exists on the db
                                    // complete the login

                                    if (dbPassword.equals(inputPassword)) {
                                        Session.setCurrentUsername(inputUsername);
                                        String userIdQuery = "SELECT UserID FROM userinfo WHERE Username = ?";
                                        PreparedStatement idStmt = conn.prepareStatement(userIdQuery);
                                        idStmt.setString(1, inputUsername);
                                        ResultSet idRs = idStmt.executeQuery();
                                        if (idRs.next()) {
                                            Session.setCurrentUserID(idRs.getInt("UserID"));
                                        }
                                        idRs.close();
                                        idStmt.close();

                                        // create the pop up dialogue window
                                        JDialog dialog = new JDialog(frame, "Login", true);
                                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                                        // and the panel that will include the message
                                        JPanel dialogPanel = new JPanel();
                                        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
                                        dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                        // print that the login was successful
                                        JLabel messageLabel = new JLabel("You're successfully logged in");
                                        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                                        dialogPanel.add(messageLabel);
                                        dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                        // add return to main menu choice button
                                        JButton returnbtn = new JButton("Return to main menu");
                                        returnbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                                        dialogPanel.add(returnbtn);

                                        // and return to main menu when pressed
                                        returnbtn.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                frame.getContentPane().removeAll();
                                                WelcomeWindow.showWelcomeWindow(frame);
                                                dialog.dispose();
                                            }
                                        });

                                        // create the continue button to play game
                                        JButton continueButton = new JButton("Play game");
                                        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                                        dialogPanel.add(continueButton);

                                        // add action listener for continue button
                                        continueButton.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                dialog.dispose();

                                                frame.getContentPane().removeAll();
                                                frame.repaint();
                                                frame.revalidate();

                                                // create the panel that will continue to the game
                                                JPanel gamePanel = new JPanel();
                                                gamePanel.add(HangmanGame.showHangmanGame(frame));

                                                frame.add(gamePanel);
                                                frame.revalidate();
                                                frame.repaint();
                                            }
                                        });

                                        dialog.getContentPane().add(dialogPanel);
                                        dialog.pack();
                                        dialog.setLocationRelativeTo(frame);
                                        dialog.setVisible(true);
                                    } else {
                                        // if the password doesnt match then print wrong password
                                        JOptionPane.showMessageDialog(null, "Wrong password");
                                    }
                                } else {

                                    // if the username doesnt match print user not found
                                    JDialog dialog = new JDialog(frame, "Fail", true);
                                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

                                    // and the panel that will include the message
                                    JPanel dialogPanel = new JPanel();
                                    dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
                                    dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                    // print that the login failed
                                    JLabel messageLabel = new JLabel("User not found - sign up");
                                    messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                                    dialogPanel.add(messageLabel);
                                    dialogPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                                    JButton returnbtn = new JButton("Return to main menu");
                                    returnbtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                                    dialogPanel.add(returnbtn);

                                    returnbtn.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            frame.getContentPane().removeAll();
                                            WelcomeWindow.showWelcomeWindow(frame);
                                            dialog.dispose();
                                        }
                                    });
                                    dialog.setContentPane(dialogPanel);
                                    dialog.pack();
                                    dialog.setLocationRelativeTo(frame); // center dialog
                                    dialog.setVisible(true); //
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

        // add action listener for the sign up button
        signupbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.revalidate();

                JPanel outerPanel = new JPanel();
                outerPanel.setLayout(new GridBagLayout());

                JPanel signupPanel = new JPanel();
                signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.Y_AXIS));
                signupPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                signupPanel.add(Box.createRigidArea(new Dimension(0, 30)));

                JLabel usernameLabel = new JLabel("Username:");
                usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JTextField usernameField = new JTextField(20);
                usernameField.setMaximumSize(new Dimension(200, 30));
                usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
                signupPanel.add(usernameLabel);
                signupPanel.add(usernameField);
                signupPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                JLabel passwordLabel = new JLabel("Password:");
                passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JPasswordField passwordField = new JPasswordField(15);
                passwordField.setMaximumSize(new Dimension(200, 30));
                passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
                signupPanel.add(passwordLabel);
                signupPanel.add(passwordField);
                signupPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                JLabel confirmationpasswordLabel = new JLabel("Confirm Password:");
                confirmationpasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                JPasswordField confirmationpasswordField = new JPasswordField(15);
                confirmationpasswordField.setMaximumSize(new Dimension(200, 30));
                confirmationpasswordField.setAlignmentX(Component.CENTER_ALIGNMENT);
                signupPanel.add(confirmationpasswordLabel);
                signupPanel.add(confirmationpasswordField);
                signupPanel.add(Box.createRigidArea(new Dimension(0, 10)));

                JButton submitBtn = new JButton("Submit");
                submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                signupPanel.add(submitBtn);

                outerPanel.add(signupPanel);

                frame.getContentPane().add(outerPanel);
                frame.add(signupPanel);
                frame.revalidate();
                frame.repaint();

                submitBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.getContentPane().removeAll();
                        frame.repaint();
                        frame.revalidate();
                        String inputUsername = usernameField.getText();
                        if (Arrays.equals(passwordField.getPassword(), confirmationpasswordField.getPassword())) {
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
                                        dialogPanel.setVisible(true);

                                        continueButton.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                dialog.dispose();
                                                frame.getContentPane().removeAll();
                                                frame.repaint();
                                                frame.revalidate();

                                                JPanel gamePanel = new JPanel();
                                                gamePanel.add(WelcomeWindow.showWelcomeWindow(frame));

                                                frame.getContentPane().removeAll();
                                                frame.add(gamePanel, BorderLayout.CENTER);
                                                frame.revalidate();
                                                frame.repaint();

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
                        } else {
                            JOptionPane.showMessageDialog(null, "Wrong duplicate password. Try again");
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

                        JLabel usernameLabel = new JLabel("Username: " + username);
                        JLabel winsLabel = new JLabel("Wins: " + wins);

                        userPanel.add(usernameLabel);
                        userPanel.add(winsLabel);

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

        userscore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.revalidate();

                JPanel userscorePanel = new JPanel();
                userscorePanel.setLayout(new BoxLayout(userscorePanel, BoxLayout.Y_AXIS));
                userscorePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
                userscorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                int userID = Session.getCurrentUserID();
                if (userID == 0) {
                    // Not logged in dialog
                    JDialog notlogin = new JDialog(frame, "Login failure", true);
                    notlogin.setLayout(new BorderLayout());
                    notlogin.setSize(300, 150);
                    notlogin.setLocationRelativeTo(frame);

                    JLabel messageLabel = new JLabel("You are not logged in", SwingConstants.CENTER);
                    messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
                    notlogin.add(messageLabel, BorderLayout.CENTER);

                    JPanel buttonPanel = new JPanel();
                    JButton mainMenuButton = new JButton("Main Menu");
                    buttonPanel.add(mainMenuButton);
                    notlogin.add(buttonPanel, BorderLayout.SOUTH);

                    mainMenuButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            notlogin.dispose();
                            WelcomeWindow.showWelcomeWindow(frame);
                        }
                    });

                    notlogin.setVisible(true);
                    return;
                }

                // User is logged in
                JLabel title = new JLabel("User Progress");
                title.setAlignmentX(Component.CENTER_ALIGNMENT);
                userscorePanel.add(title);
                userscorePanel.add(Box.createRigidArea(new Dimension(0, 10)));

                try {
                    Connection conn = LogIn.getConnection();
                    String sql = "SELECT us.UserWins, us.UserLosses, ui.Username " +
                            "FROM userscore us " +
                            "JOIN userinfo ui ON us.UserID = ui.UserID " +
                            "WHERE us.UserID = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, userID);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        String username = rs.getString("Username");
                        int wins = rs.getInt("UserWins");
                        int losses = rs.getInt("UserLosses");

                        JLabel usernameLabel = new JLabel("Username: " + username);
                        JLabel winsLabel = new JLabel("Wins: " + wins);
                        JLabel lossesLabel = new JLabel("Losses: " + losses);

                        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        winsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        lossesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                        userscorePanel.add(usernameLabel);
                        userscorePanel.add(winsLabel);
                        userscorePanel.add(lossesLabel);
                    }

                    // Add back button
                    JButton backBtn = new JButton("Back");
                    backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
                    userscorePanel.add(Box.createRigidArea(new Dimension(0, 20)));
                    userscorePanel.add(backBtn);

                    backBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            WelcomeWindow.showWelcomeWindow(frame);
                        }
                    });

                } catch (SQLException se) {
                    System.out.println(se.getMessage());
                }

                frame.getContentPane().add(userscorePanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        entirePanel.add(centerPanel, gbc);
        frame.add(entirePanel);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return null;
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

