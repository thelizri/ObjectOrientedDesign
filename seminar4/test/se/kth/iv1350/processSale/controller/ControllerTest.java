package se.kth.iv1350.processSale.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.dto.ItemDTO;
import se.kth.iv1350.processSale.exception.DatabaseFailureException;
import se.kth.iv1350.processSale.exception.ItemDoesNotExistException;
import se.kth.iv1350.processSale.integration.*;
import se.kth.iv1350.processSale.utils.Money;
import se.kth.iv1350.processSale.view.TotalRevenueView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ControllerTest {

    private Controller controller;
    private TotalRevenueView totalRevenueView;

    @BeforeEach
    public void setUp() {
        ExternalInventorySystem invSys = new ExternalInventorySystem();
        ExternalAccountingSystem accSys = new ExternalAccountingSystem();
        Printer printer = new Printer();
        DiscountDatabase discDb = new DiscountDatabase();
        TotalRevenueFileOutput revenueFileOutput= new TotalRevenueFileOutput();
        controller = new Controller(invSys, accSys, printer, discDb, revenueFileOutput);
        totalRevenueView = new TotalRevenueView();
    }

    @Test
    public void testCreateNewSale() {
        controller.createNewSale(totalRevenueView);
        assertNotNull(controller.getTotal());
        assertEquals(new Money(), controller.getTotal());
        assertEquals(new Money(0), controller.getTotal());
    }

    @Test
    public void testAddItem() throws ItemDoesNotExistException, DatabaseFailureException {
        controller.createNewSale(totalRevenueView);
        ItemDTO addedItem = controller.addItem("milk", 2);
        assertNotNull(addedItem);
        assertEquals("Milk", addedItem.getDescription());
        addedItem = controller.addItem("milk", 3);
        assertEquals(5, addedItem.getQuantity());
    }
}
