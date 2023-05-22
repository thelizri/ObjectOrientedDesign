package se.kth.iv1350.processSale.view;

import se.kth.iv1350.processSale.utils.Money;
import se.kth.iv1350.processSale.utils.Observer;

/**
 * This is a concrete Observer class called TotalRevenueView that displays the total revenue.
 * It follows the Observer design pattern and updates the total revenue whenever a change occurs.
 * The change is notified by the Subject.
 * <p>
 * The TotalRevenueView class implements the Observer interface and overrides the update method.
 */
public class TotalRevenueView implements Observer {

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
     * This method is called by the observed Subject when a change occurs.
     * The total revenue is updated with the Money object that is passed which contains the updated state.
     *
     * @param money the Money object containing the amount to be added to the total revenue
     */
    @Override
    public void update(Money money) {
        totalRevenue = totalRevenue.add(money);
    }

    /**
     * This method displays the total revenue to the standard output.
     * The revenue is formatted as a floating-point number with two decimal places.
     */
    public void displayTotalRevenue() {
        System.out.printf("%-20s %10.2f kr\n%n", "Total Revenue: ", totalRevenue.getAmountFloat());
    }
}

