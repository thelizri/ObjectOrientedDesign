package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.dto.SaleDTO;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.utils.Money;

/**
 * A placeholder class to represent an external inventory system. It would be implemented with the actual system's APIs and endpoints.
 */
public class ExternalInventorySystem {

    /**
     * Constructs a new instance of the ExternalInventorySystem class.
     */
    public ExternalInventorySystem() {

    }

    /**
     * Updates the external inventory system with the saleDTO.
     *
     * @param saleDTO The saleDTO to update the inventory system with.
     */
    public void updateInventory(SaleDTO saleDTO) {

    }

    /**
     * Gets an item from the external inventory system with the specified item identifier.
     * This is just a placeholder method and would be implemented with the actual system's APIs and endpoints.
     *
     * @param itemIdentifier The identifier of the item to get.
     * @return An Item object representing the item.
     */
    public Item getItem(String itemIdentifier) {
        switch (itemIdentifier.toLowerCase()) {
            case "milk":
                return new Item(itemIdentifier, new Money(25), "Milk", new Money(0.06));
            case "cereal":
                return new Item(itemIdentifier, new Money(32), "Cereal", new Money(0.06));
            case "cola":
                return new Item(itemIdentifier, new Money(19), "Coca Cola", new Money(0.12));
            case "yogurt":
                return new Item(itemIdentifier, new Money(21), "Yogurt", new Money(0.06));
            case "bread":
                return new Item(itemIdentifier, new Money(23), "Bread", new Money(0.06));
            case "cheese":
                return new Item(itemIdentifier, new Money(65), "Cheese", new Money(0.12));
            case "apples":
                return new Item(itemIdentifier, new Money(26), "Apples", new Money(0.06));
            case "juice":
                return new Item(itemIdentifier, new Money(30), "Orange Juice", new Money(0.06));
            default:
                return null;
        }
    }

}
