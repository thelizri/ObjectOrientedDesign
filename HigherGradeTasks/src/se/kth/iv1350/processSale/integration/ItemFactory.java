package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.exception.ItemDoesNotExistException;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.utils.Money;

/**
 * This class acts as a factory for creating Item objects.
 * It separates the responsibility of Item object creation from the ExternalInventorySystem.
 */
public class ItemFactory {

    /**
     * This method creates an Item based on the given itemIdentifier.
     * It supports a predefined set of itemIdentifiers each corresponding to a unique Item.
     *
     * @param itemIdentifier The identifier of the item to create. It's case-insensitive.
     * @return An Item object that corresponds to the specified itemIdentifier.
     * @throws ItemDoesNotExistException If the provided itemIdentifier doesn't match any of the supported item identifiers, this exception is thrown with error code 404 and a "Resource not found" message.
     */
    public Item createItem(String itemIdentifier) throws ItemDoesNotExistException {
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

