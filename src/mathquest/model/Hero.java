package mathquest.model;

/**
 * Represents the player's character.
 * Demonstrates Inheritance (extends GameCharacter) and Encapsulation.
 */
public class Hero extends GameCharacter {
    // Encapsulation: Unique property for the Hero to track the player's progress
    private int score;

    /**
     * Constructor for the Hero.
     * Invokes the superclass constructor to initialize shared traits.
     */
    public Hero(String name, int maxHealth) {
        super(name, maxHealth);
        this.score = 0; // Starts with a score of 0
    }

    /**
     * Increases the hero's score when they answer correctly or defeat a monster.
     * @param points The points to add.
     */
    public void addScore(int points) {
        if (points > 0) {
            this.score += points;
        }
    }

    // ==========================================
    // Getters and Setters
    // ==========================================

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if (score >= 0) {
            this.score = score;
        }
    }
}
