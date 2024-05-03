package se.kth.iv1350.amazingpos.model;


import se.kth.iv1350.amazingpos.integration.Printer;

import java.util.List;

/**
 * Holds details of a transaction.
 */
public class TransactionDetails {
    private List<PurchasedItem> items;
    private double total;
    private double paid;
    private double discountRate;
    private String paymentMethod;

    /**
     * Initializes transaction details with specified parameters.
     * @param items List of purchased items.
     * @param total Total cost of transaction.
     * @param paid Amount paid.
     * @param discountRate Discount rate applied.
     * @param paymentMethod Method of payment.
     */
    public TransactionDetails(List<PurchasedItem> items, double total, double paid, double discountRate, String paymentMethod) {
        this.items = items;
        this.total = total;
        this.paid = paid;
        this.discountRate = discountRate;
        this.paymentMethod = paymentMethod;
    }

    /**
     * Returns the items in the transaction.
     * @return List of items.
     */
    public List<PurchasedItem> getItems() {
        return items;
    }

    /**
     * Returns the total transaction cost.
     * @return Total cost.
     */
    public double getTotal() {
        return total;
    }

    /**
     * Returns the amount paid for the transaction.
     * @return Amount paid.
     */
    public double getPaid() {
        return paid;
    }

    /**
     * Returns the discount rate applied to the transaction.
     * @return Discount rate.
     */
    public double getDiscountRate() {
        return discountRate;
    }

    /**
     * Returns the payment method used.
     * @return Payment method.
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Prints the receipt for this transaction using the specified printer.
     * @param printer Printer to use for printing the receipt.
     */
    public void printReceipt(Printer printer) {
        Receipt receipt = new Receipt(this);
        printer.printReceipt(receipt);
    }
}
