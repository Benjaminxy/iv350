// Main class in the startup package
package se.kth.iv1350.amazingpos.startup;

import se.kth.iv1350.amazingpos.controller.Controller;

import se.kth.iv1350.amazingpos.view.View;

/**
 * Main entry point for the application.
 */
public class Main {
    /**
     * Initializes and starts the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Create a controller instance to manage application logic
        Controller controller = new Controller();


        View view = new View(controller);

        // Start the application interface
        view.executionInterface();
    }
}
