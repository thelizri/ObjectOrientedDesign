package se.kth.iv1350.processSale.integration;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.exception.ItemDoesNotExistException;
import se.kth.iv1350.processSale.model.Item;

import static org.junit.jupiter.api.Assertions.*;

public class ExternalInventorySystemTest {

    @Test
    void testGetItem() {
        ExternalInventorySystem inventorySystem = new ExternalInventorySystem();

        try {
            Item item1 = inventorySystem.getItem("milk");
            assertEquals("Milk", item1.getDescription());

            Item item2 = inventorySystem.getItem("cereal");
            assertEquals("Cereal", item2.getDescription());

            Item item3 = inventorySystem.getItem("cola");
            assertEquals("Coca Cola", item3.getDescription());

            Item item4 = inventorySystem.getItem("yogurt");
            assertEquals("Yogurt", item4.getDescription());

            Item item5 = inventorySystem.getItem("bread");
            assertEquals("Bread", item5.getDescription());

            Item item6 = inventorySystem.getItem("cheese");
            assertEquals("Cheese", item6.getDescription());

            Item item7 = inventorySystem.getItem("apples");
            assertEquals("Apples", item7.getDescription());

            Item item8 = inventorySystem.getItem("juice");
            assertEquals("Orange Juice", item8.getDescription());
        }
        catch (ItemDoesNotExistException exception) {
            fail("Unexpected exception was thrown");
        }
        try {
            Item item9 = inventorySystem.getItem("nonexistentitem");
            fail("Expected exception was not thrown");
        }
        catch(ItemDoesNotExistException exception){
            assertEquals("Resource not found", exception.getMessage());
        }
    }
}
