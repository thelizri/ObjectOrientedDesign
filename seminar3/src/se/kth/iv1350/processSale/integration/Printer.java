package se.kth.iv1350.processSale.integration;

import se.kth.iv1350.processSale.dto.SaleDTO;

public class Printer {

    public Printer() {
        // Constructor code goes here, if needed
    }

    public void printReceipt(SaleDTO receipt) {
        // Printing code goes here
        System.out.println("Receipt: " + receipt.toString());
    }

}
