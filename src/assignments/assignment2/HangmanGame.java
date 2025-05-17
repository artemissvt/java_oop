package assignments.assignment2;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class HangmanGame {
    public static void showHangmanGame (JFrame frame) {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.revalidate();
        frame.setLayout(new BorderLayout());

        // random word generator api connection
        try {
            Set<Character> correctLetters = new HashSet<>();
            int[] wrongGuessCount = {0};
            JPanel imagePanel = new JPanel();
            JLabel imageLabel1 = new JLabel(new ImageIcon("hangman0.png"));
            imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
            imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 20));
            imageLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
            imagePanel.add(imageLabel1);


            URL url = new URL("https://random-word-api.vercel.app/api?words=1");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(reader);
            String word = json.get(0).toString();

            // creation of the panel that will contain the word of the game
            JPanel gamewordPanel = new JPanel();
            gamewordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            gamewordPanel.setBorder(BorderFactory.createEmptyBorder(100, 600, 25, 10));

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (i == 0 || i == word.length() - 1) {
                    correctLetters.add(c);
                }
            }

            JLabel gamewordLabel = new JLabel();
            updateDisplay(word, correctLetters, gamewordLabel);

            gamewordPanel.setFont(new Font("Arial", Font.BOLD, 80));
            gamewordPanel.add(gamewordLabel);

            // creation of one panel that contains all of the others
            JPanel entirepanel = new JPanel(new BorderLayout());
            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

            JLabel gamelabel = new JLabel("Guess the word - you have 6 guesses");
            gamelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            centerPanel.add(gamelabel);
            centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

            // print the letters as buttons
            String[] letters = "abcdefghijklmnopqrstuvwxyz".split("");
            JPanel letterPanel = new JPanel();
            letterPanel.setLayout(new BoxLayout(letterPanel, BoxLayout.Y_AXIS));
            letterPanel.setBorder(BorderFactory.createEmptyBorder(50, 600, 25, 10));
            letterPanel.setLayout(new GridLayout(6,4));

            // create a panel for displaying the 'announcement' wrong letters
            JPanel wrongPanel = new JPanel();
            wrongPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            wrongPanel.setBorder(BorderFactory.createEmptyBorder(20, 600, 25, 10));
            JLabel word2 = new JLabel("Wrong letters:");
            wrongPanel.add(word2);

            // create a panel for wrong letters
            JPanel wrongletters = new JPanel();
            wrongletters.setLayout(new FlowLayout(FlowLayout.CENTER));
            wrongletters.setBorder(BorderFactory.createEmptyBorder(20, 600, 25, 10));

            // create a panel to contain the other panels
            JPanel containerPanel = new JPanel();
            containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
            containerPanel.add(gamewordPanel);
            containerPanel.add(letterPanel);
            containerPanel.add(wrongPanel);
            containerPanel.add(wrongletters);

            // display the letters
            for (String letter : letters) {
                JButton b = new JButton(letter);
                letterPanel.add(b);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        char guessedChar = letter.charAt(0);

                        if (!word.contains(letter)) {
                            JLabel letterlabel = new JLabel(letter + " ");
                            wrongletters.add(letterlabel);
                            wrongletters.repaint();
                            wrongletters.revalidate();

                            wrongGuessCount[0]++;

                            if (wrongGuessCount[0] <= 6) {
                                imageLabel1.setIcon(new ImageIcon("hangman" + wrongGuessCount[0] + ".png"));
                            }

                            if (wrongGuessCount[0] == 6) {

                                try {
                                    int userID = WelcomeWindow.Session.getCurrentUserID();

                                    Connection conn = LogIn.getConnection();
                                    conn.setAutoCommit(true);

                                    String sql = "UPDATE userscore SET UserLosses = UserLosses + 1 WHERE UserID = ?";
                                    PreparedStatement stmt = conn.prepareStatement(sql);
                                    stmt.setInt(1, userID);
                                    stmt.executeUpdate();

                                } catch (SQLException se) {
                                    System.out.println(se.getMessage());
                                }
                                JDialog dialog = new JDialog(frame, "Game Over", true);
                                dialog.setLayout(new BorderLayout());
                                dialog.setSize(300, 150);
                                dialog.setLocationRelativeTo(frame);

                                JLabel messageLabel = new JLabel("You lost! The word was: " + word, SwingConstants.CENTER);
                                messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
                                dialog.add(messageLabel, BorderLayout.CENTER);

                                JPanel buttonPanel = new JPanel();
                                JButton playAgainButton = new JButton("Play Again");
                                JButton mainMenuButton = new JButton("Main Menu");

                                buttonPanel.add(playAgainButton);
                                buttonPanel.add(mainMenuButton);
                                dialog.add(buttonPanel, BorderLayout.SOUTH);

                                playAgainButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        dialog.dispose();
                                        HangmanGame.showHangmanGame(frame);
                                    }
                                });
                                mainMenuButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        dialog.dispose();
                                        WelcomeWindow.showWelcomeWindow(frame);
                                    }
                                });

                                dialog.setVisible(true);
                                // update score, play again option
                            }

                        } else {
                            if (!correctLetters.contains(guessedChar)) {
                                correctLetters.add(guessedChar);
                                updateDisplay(word, correctLetters, gamewordLabel);

                                if (hasWon(word, correctLetters)) {
                                    try {
                                        int userID = WelcomeWindow.Session.getCurrentUserID();
                                        System.out.println("Debug: Current User ID = " + userID);

                                        Connection conn = LogIn.getConnection();
                                        conn.setAutoCommit(true);

                                        String sql = "UPDATE userscore SET UserWins = UserWins + 1 WHERE UserID = ?";
                                        PreparedStatement stmt = conn.prepareStatement(sql);
                                        stmt.setInt(1, userID);
                                        stmt.executeUpdate();

                                    } catch (SQLException se) {
                                        System.out.println(se.getMessage());
                                    }
                                    JDialog dialog = new JDialog(frame, "You won", true);
                                    dialog.setLayout(new BorderLayout());
                                    dialog.setSize(300, 150);
                                    dialog.setLocationRelativeTo(frame);

                                    JLabel messageLabel = new JLabel("You won! Please choose one of the following: ");
                                    messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
                                    dialog.add(messageLabel, BorderLayout.CENTER);

                                    JPanel buttonPanel = new JPanel();
                                    JButton playAgainButton = new JButton("Play Again");
                                    JButton mainMenuButton = new JButton("Main Menu");

                                    buttonPanel.add(playAgainButton);
                                    buttonPanel.add(mainMenuButton);
                                    dialog.add(buttonPanel, BorderLayout.SOUTH);

                                    playAgainButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            dialog.dispose();
                                            HangmanGame.showHangmanGame(frame);
                                        }
                                    });
                                    mainMenuButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                            dialog.dispose();
                                            WelcomeWindow.showWelcomeWindow(frame);
                                        }
                                    });

                                    dialog.setVisible(true);
                                    // update score, play again option
                                }
                            }
                        }

                        b.setEnabled(false);
                    }
                });

                letterPanel.add(b);
            }

            entirepanel.add(imagePanel, BorderLayout.WEST);
            entirepanel.add(centerPanel, BorderLayout.CENTER);
            entirepanel.add(containerPanel, BorderLayout.SOUTH);
            frame.add(entirepanel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setVisible(true);

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }


    }
    public static void updateDisplay(String word, Set<Character> correctLetters, JLabel label) {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (correctLetters.contains(c) || i == 0 || i == word.length() - 1) {
                display.append(c).append(" ");
            } else {
                display.append("_ ");
            }
        }
        label.setText(display.toString().trim());
    }

    public static void disableAllButtons(JPanel panel) {
        Component[] components = panel.getComponents();
        for (Component c : components) {
            if (c instanceof JButton) {
                c.setEnabled(false);
            }
        }
    }

    public static boolean hasWon(String word, Set<Character> correctLetters) {
        for (char c : word.toCharArray()) {
            if (!correctLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Hangman Game");
        showHangmanGame(frame);
    }
}
