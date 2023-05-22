package se.kth.iv1350.processSale.utils;

/**
 * This is the Subject interface for the Observer design pattern.
 * This interface is implemented by classes that can have observers
 * and need to notify them of changes.
 */
public interface Subject {

    /**
     * Registers an observer to be notified of changes.
     *
     * @param observer the observer to register
     */
    void register(Observer observer);

    /**
     * Unregisters an observer so that it will no longer be notified of changes.
     *
     * @param observer the observer to unregister
     */
    void unregister(Observer observer);

    /**
     * Notifies all registered observers of a change.
     */
    void notifyObserver();
}

