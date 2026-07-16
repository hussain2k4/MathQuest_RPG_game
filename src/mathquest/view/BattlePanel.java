package mathquest.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class BattlePanel extends JPanel {
    
    private JLabel levelLabel;
    private JLabel scoreLabel;
    private JLabel heroNameLabel;
    private JProgressBar heroHealthBar;
    private JLabel monsterNameLabel;
    private JProgressBar monsterHealthBar;
    private JLabel timerLabel;
    private JLabel questionLabel;
    private JButton[] optionButtons; 
    
    // Dynamic color for the sky background
    private Color skyColor = new Color(20, 100, 150);

    public BattlePanel() {
        setLayout(new BorderLayout()); 
        
        // 1. TOP PANEL: Level and Score
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // Make transparent to see our custom background
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        levelLabel = new JLabel("Level: 1");
        levelLabel.setForeground(Color.WHITE);
        levelLabel.setFont(new Font("Arial", Font.BOLD, 22));
        
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));
        
        topPanel.add(levelLabel, BorderLayout.WEST);
        topPanel.add(scoreLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // 2. CENTER PANEL: Characters and Health
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 50, 0)); 
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        
        JPanel heroPanel = createCharacterPanel(true, "🦸‍♂️"); // Hero Emoji Avatar
        JPanel monsterPanel = createCharacterPanel(false, "👹"); // Monster Emoji Avatar
        
        centerPanel.add(heroPanel);
        centerPanel.add(monsterPanel);
        add(centerPanel, BorderLayout.CENTER);

        // 3. BOTTOM PANEL: Timer, Question, and Options
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 30, 50));
        
        JPanel questionArea = new JPanel(new GridLayout(2, 1));
        questionArea.setOpaque(false);
        
        timerLabel = new JLabel("⏳ Time: 15s", SwingConstants.CENTER);
        timerLabel.setForeground(new Color(255, 100, 100)); // Light red
        timerLabel.setFont(new Font("Arial", Font.BOLD, 28));
        
        questionLabel = new JLabel("5 + 5 = ?", SwingConstants.CENTER);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 42)); // Bigger question
        
        questionArea.add(timerLabel);
        questionArea.add(questionLabel);
        bottomPanel.add(questionArea, BorderLayout.NORTH);
        
        JPanel optionsArea = new JPanel(new GridLayout(2, 2, 15, 15));
        optionsArea.setOpaque(false);
        optionsArea.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("Arial", Font.BOLD, 28));
            optionButtons[i].setFocusPainted(false);
            optionButtons[i].setBackground(new Color(240, 240, 240));
            optionButtons[i].setPreferredSize(new Dimension(100, 60));
            optionsArea.add(optionButtons[i]);
        }
        
        bottomPanel.add(optionsArea, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Custom painting for a beautiful background!
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Makes the graphics smoother
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw Sky Gradient
        GradientPaint sky = new GradientPaint(0, 0, skyColor, 0, getHeight(), new Color(10, 20, 40));
        g2d.setPaint(sky);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw a "Ground" for characters to stand on (Green oval)
        g2d.setColor(new Color(34, 139, 34)); // Forest green
        g2d.fillOval(-100, getHeight() - 250, getWidth() + 200, 300);
    }

    /**
     * Overrides the background color method to change our custom sky color dynamically.
     */
    @Override
    public void setBackground(Color bg) {
        this.skyColor = bg;
        repaint(); // Tells Java to redraw the screen
    }

    private JPanel createCharacterPanel(boolean isHero, String avatarStr) {
        // Changed to BorderLayout so the health bar doesn't stretch to fill the screen
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        JLabel nameLabel = new JLabel(isHero ? "Hero" : "Monster", SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        // AVATAR (BODY) - Using a huge font size to display an Emoji
        JLabel avatarLabel = new JLabel(avatarStr, SwingConstants.CENTER);
        avatarLabel.setFont(new Font("SansSerif", Font.PLAIN, 120)); // Huge size!
        
        JProgressBar healthBar = new JProgressBar(0, 100);
        healthBar.setPreferredSize(new Dimension(180, 25)); // Fixed smaller size!
        healthBar.setValue(100);
        healthBar.setStringPainted(true);
        healthBar.setFont(new Font("Arial", Font.BOLD, 14));
        healthBar.setForeground(isHero ? new Color(50, 205, 50) : new Color(220, 20, 60)); 
        
        // Wrap the health bar in another panel so it stays in the center and doesn't stretch
        JPanel hpWrapper = new JPanel();
        hpWrapper.setOpaque(false);
        hpWrapper.add(healthBar);
        
        if (isHero) {
            this.heroNameLabel = nameLabel;
            this.heroHealthBar = healthBar;
        } else {
            this.monsterNameLabel = nameLabel;
            this.monsterHealthBar = healthBar;
        }
        
        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(avatarLabel, BorderLayout.CENTER);
        panel.add(hpWrapper, BorderLayout.SOUTH);
        return panel;
    }

    // --- Update Methods remain exactly the same as before ---
    public void updateLevelAndScore(int level, int score) {
        levelLabel.setText("Level: " + level);
        scoreLabel.setText("Score: " + score);
    }
    public void updateHeroUI(String name, int currentHealth, int maxHealth) {
        heroNameLabel.setText(name);
        heroHealthBar.setMaximum(maxHealth);
        heroHealthBar.setValue(currentHealth);
        heroHealthBar.setString(currentHealth + " / " + maxHealth);
    }
    public void updateMonsterUI(String name, int currentHealth, int maxHealth) {
        monsterNameLabel.setText(name);
        monsterHealthBar.setMaximum(maxHealth);
        monsterHealthBar.setValue(currentHealth);
        monsterHealthBar.setString(currentHealth + " / " + maxHealth);
    }
    public void updateQuestionAndTimer(String question, int timeRemaining) {
        questionLabel.setText(question);
        timerLabel.setText("⏳ Time: " + timeRemaining + "s");
    }
    public void setOptions(int[] options) {
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(String.valueOf(options[i]));
        }
    }
    public JButton[] getOptionButtons() {
        return optionButtons;
    }
}