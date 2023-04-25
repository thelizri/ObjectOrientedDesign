package se.kth.iv1350.processSale.controller;

import se.kth.iv1350.processSale.integration.*;
import se.kth.iv1350.processSale.model.*;
import se.kth.iv1350.processSale.dto.*;

public class Controller {
    private ExternalInventorySystem invSys;
    private ExternalAccountingSystem accSys;
    private Printer printer;
    private DiscountDatabase discDb;
    private Sale currentSale;

    public Controller(ExternalInventorySystem invSys, ExternalAccountingSystem accSys, Printer printer, DiscountDatabase discDb) {
        this.invSys = invSys;
        this.accSys = accSys;
        this.printer = printer;
        this.discDb = discDb;
    }

    public void addItem(String itemID, int quantity) {
        Item item = invSys.getItem(itemID);
        currentSale.addItem(item, quantity);
    }

    public int getDiscounts(String customerID) {
        SaleDTO saleDTO = currentSale.getSaleDTO();
        return discDb.checkDiscounts(saleDTO, customerID);
    }

    public void createNewSale() {
        currentSale = new Sale();
    }

    public int endSale() {
        int discount = getDiscounts(currentSale.getSaleDTO().getCustomerID());
        currentSale.applyDiscount(discount);
        int total = currentSale.getTotal() - discount;
        printer.printReceipt(currentSale.getSaleDTO());
        pushSaleToExternalSystems();
        return total;
    }

    public int pay(int amount) {
        currentSale.pay(amount);
        int remaining = currentSale.getTotal() - currentSale.getAmountPaid();
        return remaining;
    }

    private void pushSaleToExternalSystems() {
        accSys.logNewSale(currentSale.getSaleDTO());
        invSys.updateInventory(currentSale.getSaleDTO());
    }
}

