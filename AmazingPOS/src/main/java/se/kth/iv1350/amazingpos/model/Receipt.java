package se.kth.iv1350.amazingpos.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Represents a receipt for a transaction.
 */
public class Receipt {
    private List<PurchasedItem> items;
    private double total;
    private double paid;
    private double discountRate;
    private String paymentMethod;

    /**
     * Constructs a receipt using transaction details.
     * @param data Transaction details to populate the receipt.
     */
    public Receipt(TransactionDetails data) {
        this.items = data.getItems();
        this.total = data.getTotal();
        this.paid = data.getPaid();
        this.discountRate = data.getDiscountRate();
        this.paymentMethod = data.getPaymentMethod();
    }

    /**
     * Generates a formatted receipt string.
     * @return Formatted receipt.
     */
    public String createReceiptString() {
        StringBuilder builder = new StringBuilder();
        builder.append("----- ----------------------------------------\n");
        builder.append("----- -----------------------------------------\n");
        builder.append("----- Receipt -----\n");
        builder.append("Date/Time: ").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\n");
        for (PurchasedItem item : items) {
            builder.append(item.getDescription()).append(" (").append(item.getItemId()).append(") x")
                    .append(item.getQuantity()).append(" - $").append(String.format("%.2f", item.getPrice()))
                    .append(" each, VAT: ").append(String.format("%.2f%%", item.getVatRate() * 100)).append("\n");
        }
        builder.append("Discount: ").append(String.format("%.2f", discountRate * 100)).append("%\n");
        builder.append("Total: $").append(String.format("%.2f", total)).append("\n");
        builder.append("Paid: $").append(String.format("%.2f", paid)).append(" via ").append(paymentMethod).append("\n");
        builder.append("Change: $").append(String.format("%.2f", paid - total)).append("\n");
        builder.append("----- Thank You! -----\n");
        builder.append("----- ----------------------------------------\n");
        builder.append("----- -----------------------------------------\n");
        return builder.toString();
    }
}
