package mathquest.model;

/**
 * Represents an enemy character in the game.
 * Demonstrates Inheritance (extends GameCharacter) and Encapsulation.
 */
public class Monster extends GameCharacter {
    // Encapsulation: Unique property for the Monster
    private int rewardPoints;

    /**
     * Constructor for the Monster.
     * Invokes the superclass constructor to initialize shared traits.
     */
    public Monster(String name, int maxHealth, int rewardPoints) {
        super(name, maxHealth);
        this.rewardPoints = rewardPoints;
    }

    // ==========================================
    // Getters and Setters
    // ==========================================

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        if (rewardPoints > 0) {
            this.rewardPoints = rewardPoints;
        }
    }
}