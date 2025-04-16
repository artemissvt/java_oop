package swing;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow {
    public static void main(String[] args) {
        JFrame window = new JFrame("Welcome");

        JPanel northPanel = new JPanel();
        JTextField nameField = new JTextField(15);
        northPanel.add(nameField);
        window.add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        JLabel msglabel = new JLabel("Welcome stranger");
        southPanel.add(msglabel);
        window.add(southPanel, BorderLayout.SOUTH);

        JButton clickme = new JButton("Click me");


        JPanel centerPanel = new JPanel();
        centerPanel.add(clickme);
        window.add(centerPanel, BorderLayout.CENTER);
        clickme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                msglabel.setText("Welcome " + name);
            }
        });

        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
