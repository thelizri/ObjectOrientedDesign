package se.kth.iv1350.processSale.controller;

import se.kth.iv1350.processSale.integration.*;
import se.kth.iv1350.processSale.model.*;
import se.kth.iv1350.processSale.dto.*;
import se.kth.iv1350.processSale.utils.Money;

/**
 * The Controller class represents the controller in the point-of-sale system.
 * It is responsible for coordinating interactions between the view, model, and external systems.
 */
public class Controller {
    private ExternalInventorySystem invSys;
    private ExternalAccountingSystem accSys;
    private Printer printer;
    private DiscountDatabase discDb;
    private Sale currentSale;

    /**
     * Constructs a new instance of the Controller class.
     * @param invSys The external inventory system.
     * @param accSys The external accounting system.
     * @param printer The printer for printing receipts.
     * @param discDb The discount database for checking applicable discounts.
     */
    public Controller(ExternalInventorySystem invSys, ExternalAccountingSystem accSys, Printer printer, DiscountDatabase discDb) {
        this.invSys = invSys;
        this.accSys = accSys;
        this.printer = printer;
        this.discDb = discDb;
    }

    /**
     * Creates a new sale.
     */
    public void createNewSale() {
        if (currentSale == null){
            currentSale = new Sale();
        }
    }

    /**
     * Adds an item to the current sale.
     * @param itemID The identifier of the item to add.
     * @param quantity The quantity of the item to add.
     * @return A string representing the added item and total price of the sale so far.
     */
    public String addItem(String itemID, int quantity) {
        Item item = invSys.getItem(itemID);
        currentSale.addItem(item, quantity);
        return item.getDescription()+" "+quantity+"\nTotal: "+currentSale.getTotal()+" kr";
    }

    /**
     * Gets the total price of the current sale.
     * @return The total price of the current sale.
     */
    public Money getTotal() {
        return currentSale.getTotal();
    }

    /**
     * Gets the remaining amount to be paid for the current sale.
     * @return The remaining amount to be paid for the current sale.
     */
    public Money getRemainingAmount() {
        return currentSale.getRemainingAmount();
    }

    /**
     * Requests a discount for the current sale and customer.
     * @param customerID The identifier of the customer.
     * @return The amount of the discount to apply.
     */
    public Money requestDiscount(String customerID) {
        Money amount = discDb.checkDiscounts(currentSale.getSaleDTO(), customerID);
        currentSale.applyDiscount(amount);
        return amount;
    }

    /**
     * Adds a payment to the current sale.
     * @param amount The amount of the payment to add.
     * @return The remaining amount to be paid for the current sale.
     */
    public Money pay(Money amount) {
        currentSale.pay(amount);
        return currentSale.getRemainingAmount();
    }

    /**
     * Closes the current sale, pushing the sale information to external systems.
     * @return true if the sale is closed successfully, false otherwise.
     */
    public boolean closeSale(){
        if (currentSale.closeSale()){
            pushSaleToExternalSystems();
            this.currentSale = null;
            return true;
        }
        return false;
    }

    private void pushSaleToExternalSystems() {
        accSys.logNewSale(currentSale.getSaleDTO());
        invSys.updateInventory(currentSale.getSaleDTO());
        printer.printReceipt(currentSale.getReceipt());
    }
}


