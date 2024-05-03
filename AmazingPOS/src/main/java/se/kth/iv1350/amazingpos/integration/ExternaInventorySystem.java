package se.kth.iv1350.amazingpos.integration;

/**
 *
 * @author Mrtes
 */

import se.kth.iv1350.amazingpos.model.dto.ItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages inventory for external systems.
 */
public class ExternaInventorySystem {
    private List<QuantityStock> inventory = new ArrayList<>();

    /**
     * Registers a new item with its initial stock quantity in the inventory.
     * @param itemDTO Item details.
     * @param initialQuantity Initial stock quantity.
     */
    public void registerItem(ItemDTO itemDTO, int initialQuantity) {
        inventory.add(new QuantityStock(itemDTO, initialQuantity));
    }

    /**
     * Retrieves an item from the inventory based on its ID.
     * @param id The ID of the item to retrieve.
     * @return The ItemDTO if found, null otherwise.
     */
    public ItemDTO getItem(String id) {
        for (QuantityStock quantityStock : inventory) {
            if (quantityStock.item.getId().equals(id)) {
                return quantityStock.item;
            }
        }
        return null;
    }

    /**
     * Updates the stock quantity of an item in the inventory.
     * @param id The ID of the item to update.
     * @param quantity The quantity to deduct from the item's stock.
     * @return True if the stock was sufficient and updated, false otherwise.
     */
    public boolean updateInventory(String id, int quantity) {
        for (QuantityStock quantityStock : inventory) {
            if (quantityStock.item.getId().equals(id) && quantityStock.stock >= quantity) {
                quantityStock.stock -= quantity;
                return true;
            }
        }
        return false;
    }

    /**
     * Inner class to hold an item and its stock quantity.
     */
    private static class QuantityStock {
        ItemDTO item;
        int stock;

        QuantityStock(ItemDTO item, int stock) {
            this.item = item;
            this.stock = stock;
        }
    }
}
