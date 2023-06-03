package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.utils.ExceptionLogger;
import se.kth.iv1350.processSale.utils.Money;
import se.kth.iv1350.processSale.utils.Observer;

import java.util.logging.Level;

/**
 * This is a concrete Observer class called TotalRevenueView that displays the total revenue.
 * It follows the Observer design pattern and updates the total revenue whenever a change occurs.
 * The change is notified by the Subject.
 * <p>
 * The TotalRevenueView class implements the Observer interface and overrides the update method.
 */
public class TotalRevenueView extends Observer {

    private Money totalRevenue;

    /**
     * Constructs a new TotalRevenueView object with total revenue set to zero.
     * Represents the total revenue as Money object.
     * It is updated whenever the update method is called.
     */
    public TotalRevenueView() {
        totalRevenue = new Money();
    }

    /**
     * Calculates the total income by adding the price of the sale that was just made to the total revenue.
     *
     * @param priceOfTheSaleThatWasJustMade The price of the sale that was just completed.
     */
    @Override
    protected void calculateTotalIncome(Money priceOfTheSaleThatWasJustMade) {
        totalRevenue = totalRevenue.add(priceOfTheSaleThatWasJustMade);
    }

    /**
     * This method displays the total revenue to the standard output.
     * The revenue is formatted as a floating-point number with two decimal places.
     */
    @Override
    public void doShowTotalIncome(){
        System.out.printf("%-20s %10.2f kr\n%n", "Total Revenue: ", totalRevenue.getAmountFloat());
    }

    /**
     * Handles any errors that occur during the calculation or display of total income. The error is logged as a severe error.
     *
     * @param exception The exception that occurred during the calculation or display of total income.
     */
    @Override
    protected void handleErrors(Exception exception) {
        ExceptionLogger.logException(exception, Level.SEVERE, "Could not output total revenue.");
    }
}

