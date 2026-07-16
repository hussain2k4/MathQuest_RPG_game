package mathquest.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameWinPanel extends JPanel {
    
    private JLabel scoreLabel;
    private JButton playAgainButton;
    private JButton exitButton;

    public GameWinPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(80, 0, 0, 0));

        // Trophy Emoji for Winning
        JLabel trophyLabel = new JLabel("🏆");
        trophyLabel.setFont(new Font("SansSerif", Font.PLAIN, 100));
        trophyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel winTitle = new JLabel("VICTORY!");
        winTitle.setFont(new Font("Arial", Font.BOLD, 72));
        winTitle.setForeground(new Color(50, 255, 50)); // Bright Green
        winTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel messageLabel = new JLabel("You defeated the Monster!");
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 32));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel("Final Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
        scoreLabel.setForeground(new Color(255, 215, 0)); // Gold
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        playAgainButton = createButton("Play Again");
        exitButton = createButton("Exit Game");

        add(trophyLabel);
        add(Box.createVerticalStrut(10));
        add(winTitle);
        add(Box.createVerticalStrut(20));
        add(messageLabel);
        add(Box.createVerticalStrut(20));
        add(scoreLabel);
        add(Box.createVerticalStrut(50));
        add(playAgainButton);
        add(Box.createVerticalStrut(20));
        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // A celebratory blue-green gradient
        GradientPaint gp = new GradientPaint(0, 0, new Color(10, 80, 80), 0, getHeight(), new Color(10, 40, 40));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 22));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBackground(Color.WHITE);
        return button;
    }

    public void updateWinUI(int score) {
        scoreLabel.setText("Final Score: " + score);
    }

    public JButton getPlayAgainButton() { return playAgainButton; }
    public JButton getExitButton() { return exitButton; }
}