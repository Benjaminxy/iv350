package se.kth.iv1350.amazingpos.view;

import se.kth.iv1350.amazingpos.controller.Controller;
import se.kth.iv1350.amazingpos.model.dto.CustomerDTO;
import se.kth.iv1350.amazingpos.model.dto.ItemDTO;


/**
 * Manages the user interface interactions for the application.
 * This class uses hardcoded values to demonstrate the functionality
 * without user input.
 */
public class View {
    private final Controller controller;

    /**
     * Constructs a view with the specified controller.
     * @param controller the controller that handles the business logic
     */
    public View(Controller controller) {
        this.controller = controller;
    }

    /**
     * Executes the user interface logic. This method simulates a sale
     * transaction by using hardcoded item and customer details.
     */
    public void executionInterface() {
        // Setup and register an item
        ItemDTO newItem = new ItemDTO("003", "apple", 2.0, 0.15);
        controller.registerItem(newItem, 10);

        // Register a customer
        CustomerDTO customerDTO = new CustomerDTO("12345", true);
        controller.registerCustomer(customerDTO);

        // Start a new sale
        controller.startNewSale();

        // Add items to the sale
        controller.addItem("003", 2);

        // Complete the sale
        controller.endSale("12345");

        // Display total and process payment
        System.out.println("Total due: " + controller.getTotalSaleAfterSale());
        double amountPaid = 5.0;  //  payment amount
        String paymentMethod = "cash";  //  payment method
        controller.paymentMethod(amountPaid, paymentMethod, "12345");
    }
}
