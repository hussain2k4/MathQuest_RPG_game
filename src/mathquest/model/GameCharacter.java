package mathquest.model;

/**
 * Abstract base class representing any character in the game.
 * Demonstrates Inheritance and Encapsulation.
 */
public abstract class GameCharacter {
    // Encapsulation: Private fields accessible only via getters and setters
    private String name;
    private int maxHealth;
    private int currentHealth;

    /**
     * Constructor to initialize shared character traits.
     */
    public GameCharacter(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth; // Start at full health
    }

    /**
     * Applies damage to the character and ensures health doesn't drop below 0.
     * @param damage The amount of health to deduct.
     */
    public void takeDamage(int damage) {
        this.currentHealth -= damage;
        if (this.currentHealth < 0) {
            this.currentHealth = 0;
        }
    }

    /**
     * Checks if the character is still alive.
     * @return true if health is greater than 0, false otherwise.
     */
    public boolean isAlive() {
        return this.currentHealth > 0;
    }

    /**
     * Resets the character's health to maximum (useful for level progression or restarts).
     */
    public void resetHealth() {
        this.currentHealth = this.maxHealth;
    }

    // ==========================================
    // Getters and Setters (Encapsulation Guard)
    // ==========================================

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    protected void setCurrentHealth(int currentHealth) {
        if (currentHealth > maxHealth) {
            this.currentHealth = maxHealth;
        } else if (currentHealth < 0) {
            this.currentHealth = 0;
        } else {
            this.currentHealth = currentHealth;
        }
    }
}
