package se.kth.iv1350.processSale.utils;

/**
 * The Observer abstract class is a part of the Observer design pattern.
 * It provides a structure for subclasses to override specific operations,
 * making this an example of the Template Method design pattern. The subclasses
 * are responsible for implementing the calculation and displaying of total income,
 * as well as handling potential errors during these processes.
 *
 * The Observer keeps track of all sales and their total income.
 */
public abstract class Observer {

    /**
     * Calculate the total income from all sales since the program started.
     * This method needs to be implemented in any concrete class extending
     * this abstract class.
     *
     * @param priceOfTheSaleThatWasJustMade The cost of the sale that was just completed.
     */
    protected abstract void calculateTotalIncome(Money priceOfTheSaleThatWasJustMade);

    /**
     * Method to be called when a new sale has been made. It will then calculate
     * and show the total income.
     *
     * @param priceOfTheSaleThatWasJustMade The cost of the sale that was just completed.
     */
    public void newSaleWasMade ( Money priceOfTheSaleThatWasJustMade ) {
        calculateTotalIncome ( priceOfTheSaleThatWasJustMade );
        showTotalIncome ();
    }

    /**
     * Displays the total income from all sales. It handles any potential exceptions
     * thrown during the display of the total income.
     */
    private void showTotalIncome () {
        try {
            doShowTotalIncome ();
        } catch ( Exception exception ) {
            handleErrors ( exception );
        }
    }

    /**
     * Displays the total income from all sales. This method needs to be implemented
     * in any concrete class extending this abstract class and can potentially throw an Exception.
     *
     * @throws Exception if an error occurs while attempting to show the total income.
     */
    protected abstract void doShowTotalIncome () throws Exception ;

    /**
     * Handle any potential errors that may occur while calculating or showing
     * the total income. This method needs to be implemented in any concrete
     * class extending this abstract class.
     *
     * @param exception The exception that occurred while calculating or showing the total income.
     */
    protected abstract void handleErrors ( Exception exception );
}
