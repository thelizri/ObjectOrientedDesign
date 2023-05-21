package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.utils.Money;
import se.kth.iv1350.processSale.utils.Observer;

public class TotalRevenueFileOutput implements Observer {

    /**
     * This method is called by the observed Subject when a change occurs.
     * The Money object that is passed contains the updated state.
     *
     * @param money the updated state
     */
    @Override
    public void update(Money money) {

    }
}
