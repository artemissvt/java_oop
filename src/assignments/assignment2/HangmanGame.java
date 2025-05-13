package assignments.assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanGame {
    //public static HangmanGame getHangmanGame() {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hangman");
        frame.setLayout(new BorderLayout());

        JPanel entirepanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel word = new JLabel("ble");
        word.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(word);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        JPanel letterPanel = new JPanel();
        letterPanel.setLayout(new GridLayout(3, 9));
        for (String letter : letters) {
            JButton b = new JButton(letter);
            letterPanel.add(b);
        }

        entirepanel.add(centerPanel, BorderLayout.CENTER);
        entirepanel.add(letterPanel, BorderLayout.SOUTH);

        frame.add(entirepanel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

    }

   // }

}
