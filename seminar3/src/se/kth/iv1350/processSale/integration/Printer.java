package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.dto.SaleDTO;

/**
 * The Printer class represents a printer used to print receipts.
 */
public class Printer {

    /**
     * Creates a new instance of the Printer class.
     */
    public Printer() {
        // Constructor code goes here, if needed
    }

    /**
     * Prints the specified receipt.
     * @param receipt The receipt to print.
     */
    public void printReceipt(String receipt) {
        System.out.println(receipt);
    }

}