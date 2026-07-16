package mathquest.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * The screen that displays the top scores of previous players.
 */
public class LeaderboardPanel extends JPanel {
    
    private JTextArea scoreDisplayArea;
    private JButton backButton;

    public LeaderboardPanel() {
        // Setup main layout and styling
        setLayout(new BorderLayout(0, 20)); // BorderLayout with 20px vertical gap
        setBackground(new Color(40, 40, 60)); // Match the menu background
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Outer padding

        // 1. TOP: Title
        JLabel titleLabel = new JLabel("TOP SCORES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(new Color(255, 215, 0)); // Gold color
        add(titleLabel, BorderLayout.NORTH);

        // 2. CENTER: Text Area for Scores (inside a ScrollPane)
        scoreDisplayArea = new JTextArea();
        scoreDisplayArea.setEditable(false); // The user cannot type here
        scoreDisplayArea.setFont(new Font("Monospaced", Font.BOLD, 24)); // Monospaced keeps text aligned
        scoreDisplayArea.setBackground(new Color(20, 20, 20));
        scoreDisplayArea.setForeground(Color.WHITE);
        
        // Wrap the text area in a scroll pane in case there are many scores
        JScrollPane scrollPane = new JScrollPane(scoreDisplayArea);
        add(scrollPane, BorderLayout.CENTER);

        // 3. BOTTOM: Back Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false); // Make it transparent so background shows
        
        backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setFocusPainted(false);
        
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // ==========================================
    // Controller Access Methods
    // ==========================================

    /**
     * Updates the text inside the text area. The controller will format the string.
     */
    public void updateLeaderboardText(String text) {
        scoreDisplayArea.setText(text);
    }

    /**
     * Getter for the back button so the Controller can add an action listener.
     */
    public JButton getBackButton() {
        return backButton;
    }
}