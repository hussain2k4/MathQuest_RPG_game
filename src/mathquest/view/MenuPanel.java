package mathquest.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * The main menu screen of the game.
 * Contains Play, Leaderboard, and Exit buttons.
 */
public class MenuPanel extends JPanel {
    
    private JButton playButton;
    private JButton leaderboardButton;
    private JButton exitButton;

    public MenuPanel() {
        // Setup panel layout and background color
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(40, 40, 60)); // Dark bluish-grey background
        setBorder(new EmptyBorder(100, 0, 0, 0)); // Padding from top

        // Title Label
        JLabel titleLabel = new JLabel("MATHQUEST");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 64));
        titleLabel.setForeground(new Color(255, 215, 0)); // Gold color
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Hero Vs Monster");
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 28));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Initialize Buttons
        playButton = createMenuButton("Play Game");
        leaderboardButton = createMenuButton("Leaderboard");
        exitButton = createMenuButton("Exit");

        // Add components to the panel with some vertical spacing (rigid areas)
        add(titleLabel);
        add(subtitleLabel);
        add(Box.createVerticalStrut(60)); // Space between title and buttons
        add(playButton);
        add(Box.createVerticalStrut(20));
        add(leaderboardButton);
        add(Box.createVerticalStrut(20));
        add(exitButton);
    }

    /**
     * Helper method to style buttons consistently.
     */
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false); // Removes the dotted box when clicked
        return button;
    }

    // ==========================================
    // Getters for the Controller to add ActionListeners
    // ==========================================

    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getLeaderboardButton() {
        return leaderboardButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
