package se.kth.iv1350.amazingpos.model;

/**
 * Handles processing of payments for transactions.
 */
public class Payment {
    private boolean paymentAccepted;

    /**
     * Processes a payment and determines if it is successful based on the amount paid.
     * @param amountPaid The amount of money paid.
     * @param totalSaleAmount The total cost of the sale.
     * @return true if the payment covers the total sale amount, false otherwise.
     */
    public boolean processPayment(double amountPaid, double totalSaleAmount) {
        paymentAccepted = amountPaid >= totalSaleAmount;
        return paymentAccepted;
    }

    /**
     * Checks if the last processed payment was successful.
     * @return true if the last payment was successful, false otherwise.
     */
    public boolean isPaymentAccepted() {
        return paymentAccepted;
    }
}
