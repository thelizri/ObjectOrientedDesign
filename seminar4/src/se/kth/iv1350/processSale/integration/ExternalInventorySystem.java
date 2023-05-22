package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.dto.SaleDTO;
import se.kth.iv1350.processSale.exception.DatabaseFailureException;
import se.kth.iv1350.processSale.exception.ItemDoesNotExistException;
import se.kth.iv1350.processSale.model.Item;

/**
 * A placeholder class to represent an external inventory system. It would be implemented with the actual system's APIs and endpoints.
 * It uses the ItemFactory to create Item objects.
 */
public class ExternalInventorySystem {

    private final ItemFactory itemFactory;

    /**
     * Constructs a new instance of the ExternalInventorySystem class.
     */
    public ExternalInventorySystem() {
        itemFactory = new ItemFactory();
    }

    /**
     * Updates the external inventory system with the saleDTO.
     *
     * @param saleDTO The saleDTO to update the inventory system with.
     */
    public void updateInventory(SaleDTO saleDTO) {

    }

    /**
     * Retrieves an Item object representing a specific item from the external inventory system, based on the provided item identifier.
     * In a real-world application, this method would interface with the actual APIs and endpoints of the external inventory system.
     *
     * @param itemIdentifier The identifier of the item to retrieve. It's case-insensitive.
     * @return An Item object that represents the specified item in the external inventory system.
     * @throws ItemDoesNotExistException If the provided itemIdentifier doesn't match any of the supported item identifiers, this exception is thrown with error code 404 and a "Resource not found" message.
     * @throws DatabaseFailureException  If the provided itemIdentifier is "failure", this exception is thrown with error code 503 and a "Could not connect to database" message, simulating a database failure.
     */
    public Item getItem(String itemIdentifier) throws ItemDoesNotExistException, DatabaseFailureException {
        if (itemIdentifier.equalsIgnoreCase("failure")) {
            throw new DatabaseFailureException(503, "Could not connect to database");
        }
        return itemFactory.createItem(itemIdentifier);
    }

}
