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

public class GameOverPanel extends JPanel {
    
    private JLabel messageLabel;
    private JLabel scoreLabel;
    private JButton tryAgainButton;
    private JButton menuButton;

    public GameOverPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(80, 0, 0, 0));

        // Big Skull Emoji for kids to know they lost
        JLabel skullLabel = new JLabel("💀");
        skullLabel.setFont(new Font("SansSerif", Font.PLAIN, 100));
        skullLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel gameOverTitle = new JLabel("GAME OVER!");
        gameOverTitle.setFont(new Font("Arial", Font.BOLD, 72));
        gameOverTitle.setForeground(new Color(255, 80, 80)); // Bright Red
        gameOverTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        messageLabel = new JLabel("Good effort, Hero!");
        messageLabel.setFont(new Font("Arial", Font.ITALIC, 32));
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel("Final Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
        scoreLabel.setForeground(new Color(255, 215, 0)); 
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        tryAgainButton = createButton("Try Again (Play)");
        menuButton = createButton("Main Menu");

        add(skullLabel);
        add(Box.createVerticalStrut(10));
        add(gameOverTitle);
        add(Box.createVerticalStrut(20));
        add(messageLabel);
        add(Box.createVerticalStrut(20));
        add(scoreLabel);
        add(Box.createVerticalStrut(50));
        add(tryAgainButton);
        add(Box.createVerticalStrut(20));
        add(menuButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // A dark red gradient to make it feel like "Game Over"
        GradientPaint gp = new GradientPaint(0, 0, new Color(80, 10, 10), 0, getHeight(), new Color(20, 0, 0));
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

    public void updateGameOverUI(int score) {
        scoreLabel.setText("Final Score: " + score);
        if (score > 100) {
            messageLabel.setText("Amazing job, Math Master!");
        } else if (score >= 50) {
            messageLabel.setText("Great effort, Hero!");
        } else {
            messageLabel.setText("Keep practicing! You can do it.");
        }
    }

    public JButton getTryAgainButton() { return tryAgainButton; }
    public JButton getMenuButton() { return menuButton; }
}