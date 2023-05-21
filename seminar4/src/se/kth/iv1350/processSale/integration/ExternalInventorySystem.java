package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.dto.SaleDTO;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.utils.Money;
import se.kth.iv1350.processSale.exception.ItemDoesNotExistException;

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
     * Retrieves an Item object representing a specific item from the mock external inventory system, based on the provided item identifier.
     * In the context of a real system, this function would likely interact with the actual APIs and endpoints of the external inventory system.
     *
     * Currently, this method supports the following item identifiers: "milk", "cereal", "cola", "yogurt", "bread", "cheese", "apples", and "juice".
     *
     * If an item identifier is provided that isn't one of the specified identifiers, an ItemDoesNotExistException is thrown.
     *
     * @param itemIdentifier The identifier of the item to retrieve. The identifier is case-insensitive.
     * @return An Item object that represents the item from the external inventory system. Each Item object includes the item's identifier, cost, description, and tax.
     * @throws ItemDoesNotExistException If the provided itemIdentifier doesn't match any of the available item identifiers, this exception is thrown with error code 404 and a "Resource not found" message.
     */
    public Item getItem(String itemIdentifier) throws ItemDoesNotExistException {
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
                throw new ItemDoesNotExistException(404, "Resource not found");
        }
    }

}
