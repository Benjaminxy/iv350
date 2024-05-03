package se.kth.iv1350.amazingpos.integration;


import se.kth.iv1350.amazingpos.model.PurchasedItem;
import se.kth.iv1350.amazingpos.model.dto.CustomerDTO;
import se.kth.iv1350.amazingpos.model.dto.ItemDTO;

import java.util.List;

/**
 * Manages accounting and discount calculations for sales.
 */
public class ExternalAccountingSystem {
    private final ExternaInventorySystem inventory;

    /**
     * Initializes the accounting system with a reference to the inventory system.
     * @param inventory Inventory system to access item details.
     */
    public ExternalAccountingSystem(ExternaInventorySystem inventory) {
        this.inventory = inventory;
    }

    /**
     * Records the sale of items in the accounting system.
     * @param soldItems List of items sold.
     * @param total Total amount of the sale.
     */
    public void registerSale(List<PurchasedItem> soldItems, double total) {
        System.out.println("Record sale in accounting system, Total: " + total);
        for (PurchasedItem item : soldItems) {
            System.out.println("Item ID: " + item.getItemId() + ", Quantity: " + item.getQuantity());
        }
    }

    /**
     * Calculates the total discounts applicable based on items sold, total sale amount, and customer details.
     * @param soldItems List of purchased items.
     * @param totalSaleAmount Total amount before discounts.
     * @param customer Customer details for discounts.
     * @param discounts Discount system to apply.
     * @return Total after all discounts.
     */
    public double calculateDiscounts(List<PurchasedItem> soldItems, double totalSaleAmount, CustomerDTO customer, DiscountDatabase discounts) {
        double itemDiscounts = 0.0;
        for (PurchasedItem item : soldItems) {
            ItemDTO dto = inventory.getItem(item.getItemId());
            if (dto != null) {
                double itemDiscountAmount = discounts.calculateItemDiscount(dto) * item.getQuantity();
                itemDiscounts += itemDiscountAmount;
                System.out.println("Discount for item " + dto.getDescription() + " (ID: " + item.getItemId() + "): " + itemDiscountAmount);
            } else {
                System.out.println("Item not found for discount " + item.getItemId());
            }
        }

        double subtotalAfterItemDiscounts = totalSaleAmount - itemDiscounts;
        double saleDiscount = discounts.calculateSaleDiscount(subtotalAfterItemDiscounts);
        double subtotalAfterSaleDiscount = subtotalAfterItemDiscounts - saleDiscount;
        double customerDiscountRate = discounts.getCustomerDiscount(customer);
        double customerDiscount = subtotalAfterSaleDiscount * customerDiscountRate;
        double finalTotal = subtotalAfterSaleDiscount - customerDiscount;

        System.out.println("Total after all discounts: " + finalTotal);

        return finalTotal;
    }
}
