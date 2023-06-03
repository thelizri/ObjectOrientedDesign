package se.kth.iv1350.processSale.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.processSale.utils.Money;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TotalRevenueViewTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testDoShowTotalIncome() throws Exception {
        TotalRevenueView totalRevenueView = new TotalRevenueView();
        Money salePrice = new Money(100);  // Assuming a sale of 100
        totalRevenueView.calculateTotalIncome(salePrice);
        totalRevenueView.doShowTotalIncome();

        assertTrue(outputStreamCaptor.toString().contains("100.00"), "System.out should display the total income");
    }

    @Test
    public void testHandleErrors() {
        TotalRevenueView totalRevenueView = new TotalRevenueView();
        Exception testException = new RuntimeException("Test exception");
        assertDoesNotThrow(() -> totalRevenueView.handleErrors(testException), "No exception should be thrown");
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
