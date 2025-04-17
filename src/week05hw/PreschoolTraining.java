package week05hw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreschoolTraining {
    public static void main(String[] args) {
        JFrame window = new JFrame("Preschool Training");

        JLabel northlabel = new JLabel("Welcome to the picture game");
        window.add(northlabel, BorderLayout.NORTH);
        JButton clickmebtn = new JButton("Click here to continue");
        JPanel centerPanel = new JPanel(new GridLayout(20,20));

        centerPanel.add(clickmebtn);
        window.add(centerPanel, BorderLayout.CENTER);
        clickmebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                centerPanel.removeAll();

                JLabel newlabel = new JLabel("The rules are simple:");
                JLabel ruleone = new JLabel("You write the first letter of the animal you're seeing on the picture.");
                JLabel ruletwo = new JLabel("There is one picture for each letter of the alphabet");
                JButton clicktostart = new JButton("Click here to start");

                window.add(centerPanel, BorderLayout.CENTER);
                centerPanel.add(newlabel);
                centerPanel.add(ruleone);
                centerPanel.add(ruletwo);
                centerPanel.add(clicktostart);

                window.remove(northlabel);
                centerPanel.revalidate();
                centerPanel.repaint();

                JLabel newlabel2 = new JLabel();
                JLabel resultlabel = new JLabel();

                clicktostart.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        centerPanel.removeAll();

                        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                        ImageIcon image = new ImageIcon("images/ant.jpg");
                        newlabel2.setIcon(image);
                        newlabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
                        centerPanel.add(newlabel2);

                        JTextField nameField = new JTextField(15);
                        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
                        centerPanel.add(nameField);

                        JButton checkanswer = new JButton("Check answer");
                        checkanswer.setAlignmentX(Component.CENTER_ALIGNMENT);
                        centerPanel.add(checkanswer);

                        resultlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        centerPanel.add(resultlabel);

                        checkanswer.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (nameField.getText().toLowerCase().equals("a")) {
                                    resultlabel.setText("Correct!");
                                } else {
                                    resultlabel.setText("Wrong!");
                                }
                                JButton continuebutton = new JButton("Continue");
                                continuebutton.setAlignmentX(Component.CENTER_ALIGNMENT);
                                centerPanel.add(continuebutton, BorderLayout.SOUTH);
                                continuebutton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        centerPanel.remove(resultlabel);
                                        centerPanel.remove(checkanswer);
                                        centerPanel.remove(continuebutton);

                                        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                        ImageIcon image = new ImageIcon("images/horse.jpg");
                                        newlabel2.setIcon(image);
                                        newlabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
                                        centerPanel.add(newlabel2);
                                        JTextField nameField = new JTextField(15);
                                    }
                                });
                            }
                        });
                        centerPanel.revalidate();
                        centerPanel.repaint();


                    }
                });
            }
        });


        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBackground(new Color(20, 200, 181));
        window.setVisible(true);
    }
}
