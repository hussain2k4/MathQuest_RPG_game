package mathquest.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Handles saving and loading player scores to a local text file.
 * Demonstrates File I/O and Collections sorting.
 */
public class LeaderboardManager {
    
    private static final String DIR_PATH = "data";
    private static final String FILE_PATH = DIR_PATH + "/leaderboard.txt";

    /**
     * Inner class representing a player's score record.
     * Implements Comparable to allow easy sorting from highest to lowest score.
     */
    public static class PlayerScore implements Comparable<PlayerScore> {
        private String name;
        private int score;

        public PlayerScore(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() { return name; }
        public int getScore() { return score; }

        @Override
        public int compareTo(PlayerScore other) {
            // Sorts in descending order (highest score first)
            return Integer.compare(other.score, this.score);
        }
    }

    public LeaderboardManager() {
        ensureDirectoryExists();
    }

    /**
     * Creates the "data" folder if it doesn't already exist.
     */
    private void ensureDirectoryExists() {
        File dir = new File(DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Saves the player's name and score to the text file.
     */
    public void saveScore(String playerName, int score) {
        // Using try-with-resources to automatically close the file writer
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Remove any commas from the name so it doesn't break our CSV format
            String safeName = playerName.replace(",", "");
            writer.write(safeName + "," + score);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving score: " + e.getMessage());
        }
    }

    /**
     * Reads the leaderboard file and returns a sorted list of the top scores.
     * @param limit How many top scores to return.
     */
    public List<PlayerScore> getTopScores(int limit) {
        List<PlayerScore> scores = new ArrayList<>();
        File file = new File(FILE_PATH);
        
        if (!file.exists()) {
            return scores; // Return empty list if no one has played yet
        }

        // Using try-with-resources for reading
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    try {
                        String name = parts[0];
                        int score = Integer.parseInt(parts[1]);
                        scores.add(new PlayerScore(name, score));
                    } catch (NumberFormatException e) {
                        // Ignore lines where the score isn't a valid number
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading leaderboard: " + e.getMessage());
        }

        // Sort the list based on our compareTo method (descending)
        Collections.sort(scores);

        // Return only up to the requested limit (e.g., Top 5 or Top 10)
        if (scores.size() > limit) {
            return scores.subList(0, limit);
        }
        return scores;
    }
}