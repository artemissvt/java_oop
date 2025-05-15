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
            URL url = new URL("https://random-word-api.herokuapp.com/word");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(reader);
            //System.out.println(json.get(0));

            // creation of the panel that will contain the word of the game
            JPanel gamewordPanel = new JPanel();
            gamewordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            gamewordPanel.setBorder(BorderFactory.createEmptyBorder(100, 600, 25, 10));
            String word = json.get(0).toString();

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (i == 0 || i == word.length() - 1) {
                    correctLetters.add(c); // pre-fill with first and last letters
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

            //JLabel word = new JLabel("ble");
            //word.setAlignmentX(Component.CENTER_ALIGNMENT);
            //centerPanel.add(word);
            //centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

            // print the letters as buttons
            String[] letters = "abcdefghijklmnopqrstuvwxyz".split("");
            JPanel letterPanel = new JPanel();
            letterPanel.setLayout(new BoxLayout(letterPanel, BoxLayout.Y_AXIS));
            letterPanel.setBorder(BorderFactory.createEmptyBorder(50, 600, 25, 10));
            letterPanel.setLayout(new GridLayout(3,9));

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
                        } else {
                            if (!correctLetters.contains(guessedChar)) {
                                correctLetters.add(guessedChar);
                                updateDisplay(word, correctLetters, gamewordLabel);
                            }
                        }

                        b.setEnabled(false); // disable button after press
                    }
                });

                letterPanel.add(b);
            }

            entirepanel.add(centerPanel, BorderLayout.CENTER);
            entirepanel.add(containerPanel, BorderLayout.SOUTH);
            frame.add(entirepanel);

            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hangman Game");
        showHangmanGame(frame);
    }
}
