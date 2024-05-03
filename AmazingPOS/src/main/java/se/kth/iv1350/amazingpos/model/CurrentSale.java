package se.kth.iv1350.amazingpos.model;


import se.kth.iv1350.amazingpos.integration.ExternaInventorySystem;
import se.kth.iv1350.amazingpos.model.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the current sale, tracking items sold and calculating totals.
 */
public class CurrentSale {
    private List<PurchasedItem> currentSaleItems;
    private ExternaInventorySystem inventory;
    private double totalAmountDue = 0;  // Tracks the total sale amount

    /**
     * Constructs a new CurrentSale linked to an inventory system.
     * @param inventory The inventory system to check and update item availability.
     */
    public CurrentSale(ExternaInventorySystem inventory) {
        this.inventory = inventory;
        this.currentSaleItems = new ArrayList<>();
    }

    /**
     * Adds an item to the sale if it exists and there is sufficient stock.
     * @param itemId ID of the item to add.
     * @param quantity Quantity of the item to be added.
     */
    public void addItem(String itemId, int quantity) {
        ItemDTO item = inventory.getItem(itemId);
        if (item == null) {
            System.out.println("Invalid item ID: " + itemId + ". Not item found.");
            return;
        }
        if (!inventory.updateInventory(itemId, quantity)) {
            System.out.println("Not enough stock available for " + item.getDescription() + " (ID: " + itemId + ").");

            return;
        }
        double itemTotal = item.getPrice() * quantity * (1 + item.getVatRate());
        totalAmountDue += itemTotal;
        currentSaleItems.add(new PurchasedItem(itemId, item.getDescription(), quantity, item.getPrice(), item.getVatRate()));
        System.out.println("Item added: " + item.getDescription() + " (ID: " + itemId + "), Quantity: " + quantity);
    }

    /**
     * Returns a list of all items in the current sale.
     * @return List of purchased items.
     */
    public List<PurchasedItem> getCurrentSaleItems() {
        return currentSaleItems;
    }

    /**
     * Returns the total amount for the current sale.
     * @return Total sale amount.
     */
    public double getTotalAmountDue() {
        return totalAmountDue;
    }

    /**
     * Updates the total sale amount after applying discounts.
     * @param newTotal The new total amount after discounts.
     */
    public void updateTotalAfterDiscounts(double newTotal) {
        totalAmountDue = newTotal;
    }

    /**
     * Clears all items from the current sale, preparing for a new transaction.
     */
    public void startNewSale() {
        currentSaleItems.clear();
    }
}
