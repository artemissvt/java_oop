package assignments.assignment2;
import javax.swing.*;
import java.awt.*;


public class HangmanCore {
    public static void main(String[] args) {

        // create the frame of the game and set the layout
        JFrame frame = new JFrame("Hangman");
        frame.setLayout(new BorderLayout());

        // set grid so that everything that appears is aligned and clean
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(40, 0, 0, 0);
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;

        // create center panel that will display the initial welcome label
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // create initial label and add it to the center panel
        JLabel welcome = new JLabel("Welcome to Hangman game - please select log in/ sign up");
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(welcome);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // display the show welcome window class
        WelcomeWindow.showWelcomeWindow(frame);
    }


}
