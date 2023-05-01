package se.kth.iv1350.processSale.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.controller.Controller;
import se.kth.iv1350.processSale.dto.ItemDTO;
import se.kth.iv1350.processSale.integration.DiscountDatabase;
import se.kth.iv1350.processSale.integration.ExternalAccountingSystem;
import se.kth.iv1350.processSale.integration.ExternalInventorySystem;
import se.kth.iv1350.processSale.integration.Printer;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.utils.Money;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {
        ExternalInventorySystem invSys = new ExternalInventorySystem();
        ExternalAccountingSystem accSys = new ExternalAccountingSystem();
        Printer printer = new Printer();
        DiscountDatabase discDb = new DiscountDatabase();
        controller = new Controller(invSys, accSys, printer, discDb);
    }

    @Test
    public void testCreateNewSale() {
        controller.createNewSale();
        assertNotNull(controller.getTotal());
        assertEquals(new Money(), controller.getTotal());
        assertEquals(new Money(0), controller.getTotal());
    }

    @Test
    public void testAddItem() {
        controller.createNewSale();
        ItemDTO addedItem = controller.addItem("milk", 2);
        assertNotNull(addedItem);
        assertEquals("Milk", addedItem.getDescription());
        addedItem = controller.addItem("milk", 3);
        assertEquals(5, addedItem.getQuantity());
    }
}
