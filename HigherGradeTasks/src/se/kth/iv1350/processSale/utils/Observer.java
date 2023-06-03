package se.kth.iv1350.processSale.utils;

/**
 * This is the Observer interface for the Observer design pattern.
 * This interface is implemented by classes that need to observe a Subject
 * and get notified when changes occur.
 */
public interface Observer {

    /**
     * This method is called by the observed Subject when a change occurs.
     * The Money object that is passed contains the updated state.
     *
     * @param money the updated state
     */
    void update(Money money);
}
