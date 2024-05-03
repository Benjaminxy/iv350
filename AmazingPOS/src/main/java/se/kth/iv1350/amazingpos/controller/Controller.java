// Controller class in the controller package
package se.kth.iv1350.amazingpos.controller;

import se.kth.iv1350.amazingpos.integration.*;

import se.kth.iv1350.amazingpos.model.CurrentSale;
import se.kth.iv1350.amazingpos.model.Payment;
import se.kth.iv1350.amazingpos.model.TransactionDetails;
import se.kth.iv1350.amazingpos.model.dto.CustomerDTO;
import se.kth.iv1350.amazingpos.model.dto.ItemDTO;

public class Controller {
    private ExternaInventorySystem inventory;
    private CustomerManagementSystem customerManagement;
    private DiscountDatabase discounts;
    private ExternalAccountingSystem accounting;
    private Payment payment;
    private Printer printer;
    private TransactionDetails transactionDetails;
    private CurrentSale currentSale;

    /**
     * Initializes all necessary components for the sales system.
     */
    public Controller() {
        inventory = new ExternaInventorySystem();
        customerManagement = new CustomerManagementSystem();
        discounts = new DiscountDatabase();
        accounting = new ExternalAccountingSystem(inventory);
        payment = new Payment();
        printer = new Printer();
    }

    /**
     * Registers a new item in the inventory system.
     * @param itemDTO Item details to be registered.
     * @param initialQuantity Initial stock quantity of the item.
     */
    public void registerItem(ItemDTO itemDTO, int initialQuantity) {
        inventory.registerItem(itemDTO, initialQuantity);
    }

    /**
     * Initiates a new sale transaction.
     */
    public void startNewSale() {
        currentSale = new CurrentSale(inventory);
        currentSale.startNewSale();
    }

    /**
     * Registers a customer in the customer management system.
     * @param customerDTO Details of the customer to register.
     */
    public void registerCustomer(CustomerDTO customerDTO) {
        customerManagement.registerCustomer(customerDTO);
    }

    /**
     * Adds an item to the current sale.
     * @param itemId Unique identifier of the item to add.
     * @param quantity Quantity of the item to be added.
     */
    public void addItem(String itemId, int quantity) {
        currentSale.addItem(itemId, quantity);
    }

    /**
     * Completes the sale by applying discounts and recording the transaction.
     * @param customerId Identifier of the customer for discount application.
     */
    public void endSale(String customerId) {
        CustomerDTO customerDTO = customerManagement.getCustomer(customerId);
        if (customerDTO != null) {
            double totalAfterDiscounts = accounting.calculateDiscounts(currentSale.getCurrentSaleItems(), currentSale.getTotalAmountDue(), customerDTO, discounts);
            currentSale.updateTotalAfterDiscounts(totalAfterDiscounts);
            accounting.registerSale(currentSale.getCurrentSaleItems(), totalAfterDiscounts);
        }
    }

    /**
     * Processes payment for the sale.
     * @param amountPaid Amount paid by the customer.
     * @param paymentMethod Method used for payment.
     * @param customerId Customer identifier.
     */
    public void paymentMethod(double amountPaid, String paymentMethod, String customerId) {
        boolean accepted = payment.processPayment(amountPaid, currentSale.getTotalAmountDue());

        if (accepted) {
            CustomerDTO customerDTO = customerManagement.getCustomer(customerId);
            double discountRate = discounts.getCustomerDiscount(customerDTO);
            transactionDetails = new TransactionDetails(currentSale.getCurrentSaleItems(), currentSale.getTotalAmountDue(), amountPaid, discountRate, paymentMethod);
            transactionDetails.printReceipt(printer);
        }
    }

    /**
     * Returns the total sale amount after all transactions are complete.
     * @return Total sale amount.
     */
    public double getTotalSaleAfterSale() {
        return currentSale.getTotalAmountDue();
    }
}
