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
    private String customerID;

    public Controller(ExternalInventorySystem invSys, ExternalAccountingSystem accSys, Printer printer, DiscountDatabase discDb) {
        this.invSys = invSys;
        this.accSys = accSys;
        this.printer = printer;
        this.discDb = discDb;
    }

    public void createNewSale() {
        currentSale = new Sale();
    }

    public void addItem(String itemID, int quantity) {
        Item item = invSys.getItem(itemID);
        currentSale.addItem(item, quantity);
    }

    public float endSale() {
        return currentSale.getTotal();
    }

    public void requestDiscount(String customerID) {
        this.customerID = customerID;
        float amount = discDb.checkDiscounts(currentSale.getSaleDTO(), customerID);
        currentSale.applyDiscount(amount);
    }

    public float pay(float amount) {
        currentSale.pay(amount);
        return currentSale.getRemainingAmount();
    }

    private void pushSaleToExternalSystems() {
        accSys.logNewSale(currentSale.getSaleDTO());
        invSys.updateInventory(currentSale.getSaleDTO());
    }
}

