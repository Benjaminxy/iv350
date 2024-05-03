package se.kth.iv1350.amazingpos.integration;


import se.kth.iv1350.amazingpos.model.dto.CustomerDTO;
import se.kth.iv1350.amazingpos.model.dto.ItemDTO;

/**
 * Manages discount calculations for items and sales based on various criteria.
 */
public class DiscountDatabase {

    /**
     * Calculates the discount for a specific item.
     * @param item The item to calculate the discount for.
     * @return Discount amount based on item description.
     */
    public double calculateItemDiscount(ItemDTO item) {
        return switch (item.getDescription().toLowerCase()) {
            case "apple" -> 0.10 * item.getPrice();  // 10% discount on apples
            case "banana" -> 0.05 * item.getPrice();  // 5% discount on bananas
            case "orange" -> 0.07 * item.getPrice();  // 7% discount on oranges
            default -> 0.0;  // No discount for items not listed
        };
    }

    /**
     * Calculates a discount based on the total sale amount.
     * @param totalSaleAmount Total amount of the sale.
     * @return Discount percentage for the sale.
     */
    public double calculateSaleDiscount(double totalSaleAmount) {
    if (totalSaleAmount > 100) {
        return totalSaleAmount * 0.10;  // Calculate 10% of the total sale amount
    } else {
        return 0.0;
    }
}


    /**
     * Determines the discount rate for a customer based on membership status.
     * @param customer The customer whose discount rate is to be determined.
     * @return Discount rate based on customer's membership status.
     */
    public double getCustomerDiscount(CustomerDTO customer) {
        return customer.isMember() ? 0.15 : 0.0;  // 15% discount for members
    }
}
