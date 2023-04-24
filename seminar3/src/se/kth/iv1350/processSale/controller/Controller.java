package se.kth.iv1350.processSale.controller;

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
        ItemDTO item = invSys.getItem(itemID);
        currentSale.addItem(item, quantity);
        invSys.updateInventory(itemID, -quantity);
    }

    public int getDiscounts(String customerID) {
        return discDb.getDiscount(customerID);
    }

    public void createNewSale() {
        currentSale = new Sale();
    }

    public int endSale() {
        int discount = getDiscounts(currentSale.getSale().getCustomerID());
        currentSale.applyDiscount(discount);
        int total = currentSale.getTotal() - discount;
        printer.printReceipt(currentSale.getReceipt());
        pushSaleToExternalSystems();
        return total;
    }

    public int pay(int amount) {
        currentSale.pay(amount);
        int change = currentSale.getTotal() - currentSale.getPaid();
        return change;
    }

    private void pushSaleToExternalSystems() {
        accSys.bookSale(currentSale.getSale());
        invSys.updateInventory();
    }
}

