package mathquest.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import mathquest.model.Hero;
import mathquest.model.LeaderboardManager;
import mathquest.model.Monster;
import mathquest.model.QuestionGenerator;
import mathquest.view.BattlePanel;
import mathquest.view.GameOverPanel;
import mathquest.view.GameWinPanel; // NEW IMPORT
import mathquest.view.LeaderboardPanel;
import mathquest.view.MainFrame;
import mathquest.view.MenuPanel;

public class GameController {
    
    // Views
    private MainFrame mainFrame;
    private MenuPanel menuPanel;
    private BattlePanel battlePanel;
    private LeaderboardPanel leaderboardPanel;
    private GameOverPanel gameOverPanel;
    private GameWinPanel gameWinPanel; // NEW VIEW

    // Models
    private Hero hero;
    private Monster monster;
    private QuestionGenerator questionGenerator;
    private LeaderboardManager leaderboardManager;

    // Game State Variables
    private Timer gameTimer;
    private int timeRemaining;
    private int currentLevel;
    private int timeLimitPerQuestion;
    private QuestionGenerator.MathQuestion currentQuestion;

    public GameController() {
        mainFrame = new MainFrame();
        menuPanel = new MenuPanel();
        battlePanel = new BattlePanel();
        leaderboardPanel = new LeaderboardPanel();
        gameOverPanel = new GameOverPanel();
        gameWinPanel = new GameWinPanel(); // Initialize Win Panel

        mainFrame.addView(menuPanel, "Menu");
        mainFrame.addView(battlePanel, "Battle");
        mainFrame.addView(leaderboardPanel, "Leaderboard");
        mainFrame.addView(gameOverPanel, "GameOver");
        mainFrame.addView(gameWinPanel, "GameWin"); // Add to CardLayout

        questionGenerator = new QuestionGenerator();
        leaderboardManager = new LeaderboardManager();
        
        // INCREASED MONSTER HEALTH TO 300 so players can reach Level 3
        monster = new Monster("Math Goblin", 300, 50); 

        setupListeners();
        setupTimer();

        mainFrame.showView("Menu");
        mainFrame.setVisible(true);
    }

    private void setupListeners() {
        // Menu Listeners
        menuPanel.getPlayButton().addActionListener(e -> startGame());
        menuPanel.getLeaderboardButton().addActionListener(e -> showLeaderboard());
        menuPanel.getExitButton().addActionListener(e -> System.exit(0));

        // Battle Listeners
        JButton[] options = battlePanel.getOptionButtons();
        for (int i = 0; i < 4; i++) {
            final int index = i; 
            options[i].addActionListener(e -> handleAnswerSelection(index));
        }

        // Leaderboard Listeners
        leaderboardPanel.getBackButton().addActionListener(e -> mainFrame.showView("Menu"));

        // Game Over Listeners
        gameOverPanel.getTryAgainButton().addActionListener(e -> startGame());
        gameOverPanel.getMenuButton().addActionListener(e -> mainFrame.showView("Menu"));

        // Game Win Listeners (NEW)
        gameWinPanel.getPlayAgainButton().addActionListener(e -> startGame());
        gameWinPanel.getExitButton().addActionListener(e -> System.exit(0));
    }

    private void setupTimer() {
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                battlePanel.updateQuestionAndTimer(currentQuestion.getQuestionText(), timeRemaining);
                
                if (timeRemaining <= 0) {
                    gameTimer.stop();
                    applyPenalty("Time's Up!");
                }
            }
        });
    }

    private void startGame() {
        String playerName = JOptionPane.showInputDialog(mainFrame, "Enter your Hero's name:", "New Game", JOptionPane.PLAIN_MESSAGE);
        
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Unknown Hero";
        }

        hero = new Hero(playerName, 100);
        monster.resetHealth(); // Resets monster health back to 300 for the new game
        currentLevel = 1;
        timeLimitPerQuestion = 15;
        
        battlePanel.setBackground(new Color(20, 100, 150)); // Default sky blue
        updateBattleUI();
        mainFrame.showView("Battle");

        startNextRound();
    }

    private void startNextRound() {
        currentQuestion = questionGenerator.generateQuestion(currentLevel);
        battlePanel.setOptions(currentQuestion.getOptions());
        timeRemaining = timeLimitPerQuestion;
        
        battlePanel.updateQuestionAndTimer(currentQuestion.getQuestionText(), timeRemaining);
        gameTimer.start();
    }

    private void handleAnswerSelection(int buttonIndex) {
        gameTimer.stop();

        String buttonText = battlePanel.getOptionButtons()[buttonIndex].getText();
        int selectedAnswer = Integer.parseInt(buttonText);

        if (selectedAnswer == currentQuestion.getCorrectAnswer()) {
            monster.takeDamage(25);
            hero.addScore(10);
            
            // --- NEW: WIN CONDITION ---
            if (!monster.isAlive()) {
                hero.addScore(monster.getRewardPoints());
                winGame(); // Call the win sequence
            } else {
                checkLevelProgression();
                updateBattleUI();
                startNextRound();
            }
            
        } else {
            applyPenalty("Wrong Answer!");
        }
    }

    private void applyPenalty(String reason) {
        hero.takeDamage(20);
        updateBattleUI();
        
        JOptionPane.showMessageDialog(mainFrame, reason + " You took 20 damage!", "Ouch!", JOptionPane.WARNING_MESSAGE);

        if (!hero.isAlive()) {
            loseGame();
        } else {
            startNextRound();
        }
    }

    private void checkLevelProgression() {
        if (hero.getScore() >= 100 && currentLevel == 2) {
            currentLevel = 3; 
            timeLimitPerQuestion = 7;
            battlePanel.setBackground(new Color(150, 50, 50)); // Red tint for Hard
            
            JOptionPane.showMessageDialog(mainFrame, 
                "🔥🔥 LEVEL UP! You are now in HARD Mode!\nTimer is now 7 seconds! Watch out!", 
                "Level Up!", JOptionPane.WARNING_MESSAGE);
                
        } else if (hero.getScore() >= 40 && currentLevel == 1) {
            currentLevel = 2; 
            timeLimitPerQuestion = 10;
            battlePanel.setBackground(new Color(50, 50, 150)); // Dark blue tint for Medium
            
            JOptionPane.showMessageDialog(mainFrame, 
                "⭐ LEVEL UP! You are now in MEDIUM Mode!\nTimer is now 10 seconds! Keep it up!", 
                "Level Up!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateBattleUI() {
        battlePanel.updateLevelAndScore(currentLevel, hero.getScore());
        battlePanel.updateHeroUI(hero.getName(), hero.getCurrentHealth(), hero.getMaxHealth());
        battlePanel.updateMonsterUI(monster.getName(), monster.getCurrentHealth(), monster.getMaxHealth());
    }

    private void showLeaderboard() {
        List<LeaderboardManager.PlayerScore> topScores = leaderboardManager.getTopScores(10);
        StringBuilder sb = new StringBuilder();
        
        if (topScores.isEmpty()) {
            sb.append("No scores yet. Be the first!");
        } else {
            int rank = 1;
            for (LeaderboardManager.PlayerScore ps : topScores) {
                sb.append(String.format("%2d. %-15s : %d\n", rank, ps.getName(), ps.getScore()));
                rank++;
            }
        }
        
        leaderboardPanel.updateLeaderboardText(sb.toString());
        mainFrame.showView("Leaderboard");
    }

    /**
     * Handles the sequence when the player defeats the monster.
     */
    private void winGame() {
        gameTimer.stop();
        leaderboardManager.saveScore(hero.getName(), hero.getScore());
        
        gameWinPanel.updateWinUI(hero.getScore());
        mainFrame.showView("GameWin");
    }

    /**
     * Handles the sequence when the hero's health reaches 0.
     */
    private void loseGame() {
        gameTimer.stop();
        leaderboardManager.saveScore(hero.getName(), hero.getScore());
        
        gameOverPanel.updateGameOverUI(hero.getScore());
        mainFrame.showView("GameOver");
    }
}
