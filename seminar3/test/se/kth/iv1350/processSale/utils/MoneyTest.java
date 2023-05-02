package se.kth.iv1350.processSale.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest {

    @Test
    void testAdd() {
        Money money1 = new Money(10);
        Money money2 = new Money(20);
        Money result = money1.add(money2);
        assertEquals(new Money("30"), result);
    }

    @Test
    void testSubtract() {
        Money money1 = new Money(20);
        Money money2 = new Money(10);
        Money result = money1.subtract(money2);
        assertEquals(new Money("10"), result);
    }

    @Test
    void testMultiply() {
        Money money1 = new Money(5);
        Money money2 = new Money(2);
        Money result = money1.multiply(money2);
        assertEquals(new Money("10"), result);
    }
}
