package se.kth.iv1350.processSale.utils;

/**
 * This is the Observer interface for the Observer design pattern.
 * This interface is implemented by classes that need to observe a Subject
 * and get notified when changes occur.
 */
public abstract class Observer {

    protected abstract void calculateTotalIncome(Money priceOfTheSaleThatWasJustMade);

    public void newSaleWasMade ( Money priceOfTheSaleThatWasJustMade ) {
        calculateTotalIncome ( priceOfTheSaleThatWasJustMade ); // Calculate
        // the total amount paid since the program started .
        showTotalIncome ();
    }

    private void showTotalIncome () {
        try {
            doShowTotalIncome ();
        } catch ( Exception exception ) {
            handleErrors ( exception );
        }
    }

    protected abstract void doShowTotalIncome () throws Exception ;

    protected abstract void handleErrors ( Exception e );
}

