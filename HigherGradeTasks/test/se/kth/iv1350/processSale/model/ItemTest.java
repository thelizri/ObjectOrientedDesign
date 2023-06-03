package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.utils.Money;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    void testEquality() {
        Item item1 = new Item("milk", new Money(25), "Milk", new Money(0.06));
        Item item2 = new Item("milk", new Money(30), "Milk", new Money(0.06));
        Item item3 = new Item("bread", new Money(23), "Bread", new Money(0.06));

        assertTrue(item1.equals(item2));
        assertFalse(item1.equals(item3));
    }

    @Test
    void testAddQuantity() {
        Item item = new Item("milk", new Money(25), "Milk", new Money(0.06));

        item.setQuantity(10);
        item.addQuantity(5);

        assertEquals(15, item.getQuantity());
    }

    @Test
    void testGetPrice() {
        Item item = new Item("milk", new Money(25), "Milk", new Money(0.1));
        Money expectedPrice = new Money("27.50");

        item.setQuantity(2);
        Money actualPrice = item.getPriceIncludingVAT();

        assertEquals(expectedPrice, actualPrice);
    }
}
