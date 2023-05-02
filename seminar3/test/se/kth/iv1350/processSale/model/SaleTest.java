package se.kth.iv1350.processSale.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.processSale.dto.SaleDTO;
import se.kth.iv1350.processSale.utils.Money;

class SaleTest {
    private Sale sale;
    private Item item;
    private Money price;

    @BeforeEach
    void setUp() {
        sale = new Sale();
        price = new Money(100);
        item = new Item("milk", price, "Milk", new Money("0.12"));
    }

    @Test
    void testAddItem() {
        int quantity = 2;
        sale.addItem(item, quantity);
        assertEquals(item.getPrice().multiply(quantity), sale.getTotal(), "Total price should be correct");
        assertEquals(quantity, sale.getSaleDTO().getItemList().get(0).getQuantity(), "Quantity should be correct");
        sale.addItem(item, quantity);
        assertEquals(item.getPrice().multiply(quantity * 2), sale.getTotal(), "Total price should be correct with duplicate item");
        assertEquals(quantity * 2, sale.getSaleDTO().getItemList().get(0).getQuantity(), "Quantity should be correct with duplicate item");
    }

    @Test
    void testApplyDiscount() {
        Money discount = new Money(50);
        sale.addItem(item, 1);
        sale.applyDiscount(discount);
        assertEquals(item.getPrice().subtract(discount), sale.getRemainingAmount(), "Remaining amount should be correct after discount");
        assertEquals(discount, sale.getSaleDTO().getDiscount(), "Discount should be correct");
    }

    @Test
    void testPay() {
        Money payment1 = new Money(50);
        Money payment2 = new Money(30);
        sale.addItem(item, 1);
        sale.pay(payment1);
        assertEquals(payment1, sale.getAmountPaid(), "Amount paid should be correct after first payment");
        assertEquals(item.getPrice().subtract(payment1), sale.getRemainingAmount(), "Remaining amount should be correct after first payment");
        sale.pay(payment2);
        assertEquals(payment1.add(payment2), sale.getAmountPaid(), "Amount paid should be correct after second payment");
        assertEquals(item.getPrice().subtract(payment1).subtract(payment2), sale.getRemainingAmount(), "Remaining amount should be correct after second payment");
    }

    @Test
    void testCloseSale() {
        sale.addItem(item, 1);
        assertFalse(sale.closeSale(), "Sale should not be closed if not fully paid");
        sale.pay(item.getPrice());
        assertTrue(sale.closeSale(), "Sale should be closed if fully paid");
    }
}
