package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.dto.SaleDTO;

/**
 * This class is a singleton class that represents a placeholder for an external accounting system.
 * In a real-world scenario, this class would interface with the actual accounting system using its APIs and endpoints.
 * This class follows the Singleton design pattern to ensure only one instance of the ExternalAccountingSystem is created.
 *
 * <p>The ExternalAccountingSystem is created when getInstance() is called for the first time. This single instance is
 * reused whenever getInstance() is called subsequently, ensuring only a single instance of the class exists.</p>
 */
public class ExternalAccountingSystem {

    private static ExternalAccountingSystem instance = null;

    /**
     * A private constructor ensures that the class can't be instantiated from outside the class.
     */
    private ExternalAccountingSystem() {

    }

    /**
     * Provides access to the single instance of the ExternalAccountingSystem class.
     * If the instance doesn't exist, it's created. If it does exist, the existing instance is returned.
     *
     * @return The single instance of the ExternalAccountingSystem class.
     */
    public static ExternalAccountingSystem getInstance() {
        if (instance == null) {
            instance = new ExternalAccountingSystem();
        }
        return instance;
    }

    /**
     * Logs the new sale in the accounting system. This method will be implemented with the actual accounting system's
     * APIs and endpoints.
     *
     * @param saleDTO The SaleDTO object representing the sale.
     */
    public void logNewSale(SaleDTO saleDTO) {

    }
}
