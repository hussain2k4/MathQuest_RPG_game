package mathquest.main;

import javax.swing.SwingUtilities;
import mathquest.controller.GameController;

/**
 * The entry point of the MathQuest application.
 */
public class Main {
    
    public static void main(String[] args) {
        /*
         * SwingUtilities.invokeLater is used to ensure that all GUI-related 
         * tasks are executed on the Event Dispatch Thread (EDT).
         * This is a best practice for Java Swing applications to prevent bugs.
         */
        SwingUtilities.invokeLater(() -> {
            // Instantiate the controller, which sets up the Models, Views, and Game Loop
            new GameController();
        });
    }
}
