package assignments.assignment2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Delete {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("MobilePhoneLikeKeyboard");

        frame.setLayout(new BorderLayout());

        JPanel entirepanel = new JPanel(new GridBagLayout());
        entirepanel.setLayout(new GridLayout(4, 1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(70, 0, 0, 0);
        gbc.weighty = 2;
        gbc.fill = GridBagConstraints.VERTICAL;



        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

        String [] letters = {"a", "b", "c", "d", "e",
                "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p",
                "q", "r", "s", "t", "u", "v",
                "w", "x", "y", "z"};

        for (int i = 0; i < 8; i++) {
            JButton b = new JButton(letters[i]);
            panel1.add(b, BorderLayout.EAST);
        }
        entirepanel.add(panel1, BorderLayout.EAST);


        for (int i = 8; i < 16; i++) {
            JButton b = new JButton(letters[i]);
            panel2.add(b, BorderLayout.EAST);
        }
        entirepanel.add(panel2, BorderLayout.EAST);

        for (int i = 16; i < 24; i++) {
            JButton b = new JButton(letters[i]);
            panel3.add(b, BorderLayout.EAST);
        }
        entirepanel.add(panel3, BorderLayout.EAST);

        for (int i = 24; i < 26; i++) {
            JButton b = new JButton(letters[i]);
            panel4.add(b, BorderLayout.EAST);
        }
        entirepanel.add(panel4, BorderLayout.EAST);


        frame.add(entirepanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
    }
}