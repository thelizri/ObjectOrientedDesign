package se.kth.iv1350.processSale.integration;
import se.kth.iv1350.processSale.dto.*;
import se.kth.iv1350.processSale.utils.Money;

/**
 * A placeholder class to represent a discount database. It would be implemented with the actual database's APIs and endpoints.
 */
public class DiscountDatabase {

    /**
     * Constructs a new instance of the DiscountDatabase class.
     */
    public DiscountDatabase() {
        // constructor implementation
    }

    /**
     * Checks for applicable discounts for the current sale and customer.
     * @param currentSale The current sale to check for discounts.
     * @param customerID The ID of the customer to check for discounts.
     * @return The amount of discount, if applicable.
     */
    public Money checkDiscounts(SaleDTO currentSale, String customerID) {
        // implementation to check for applicable discounts for the current sale and customer
        return new Money(10);
    }
}


