package mathquest.view;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main application window.
 * Uses CardLayout to switch between different screens (Menu, Battle, Leaderboard).
 */
public class MainFrame extends JFrame {
    
    private CardLayout cardLayout;
    private JPanel mainContainer;

    /**
     * Constructor sets up the basic window properties.
     */
    public MainFrame() {
        // Window settings
        setTitle("MathQuest: Hero Vs Monster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // 800px width, 600px height
        setLocationRelativeTo(null); // Centers the window on the screen
        setResizable(false); // Prevents resizing to keep UI layout intact

        // Initialize CardLayout and the main container panel
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // Add the container to the frame
        add(mainContainer);
    }

    /**
     * Adds a new panel (screen) to the CardLayout container.
     * @param panel The JPanel representing the screen.
     * @param viewName The unique string identifier for this screen.
     */
    public void addView(JPanel panel, String viewName) {
        mainContainer.add(panel, viewName);
    }

    /**
     * Switches the currently visible screen to the specified view.
     * @param viewName The unique string identifier of the screen to show.
     */
    public void showView(String viewName) {
        cardLayout.show(mainContainer, viewName);
    }
}