package se.kth.iv1350.processSale.controller;

import se.kth.iv1350.processSale.dto.ItemDTO;
import se.kth.iv1350.processSale.exception.DatabaseFailureException;
import se.kth.iv1350.processSale.exception.ItemDoesNotExistException;
import se.kth.iv1350.processSale.exception.SaleNotPaidException;
import se.kth.iv1350.processSale.integration.*;
import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.Sale;
import se.kth.iv1350.processSale.utils.Money;
import se.kth.iv1350.processSale.utils.Observer;

/**
 * The Controller class represents the controller in the point-of-sale system.
 * It is responsible for coordinating interactions between the view, model, and external systems.
 */
public class Controller {
    private final ExternalInventorySystem invSys;
    private final ExternalAccountingSystem accSys;
    private final Printer printer;
    private final DiscountDatabase discDb;
    private final TotalRevenueFileOutput revenueFileOutput;
    private Sale currentSale;

    /**
     * Constructs a new instance of the Controller class.
     *
     * @param invSys  The external inventory system.
     * @param accSys  The external accounting system.
     * @param printer The printer for printing receipts.
     * @param discDb  The discount database for checking applicable discounts.
     */
    public Controller(ExternalInventorySystem invSys, ExternalAccountingSystem accSys, Printer printer, DiscountDatabase discDb, TotalRevenueFileOutput revenueFileOutput) {
        this.invSys = invSys;
        this.accSys = accSys;
        this.printer = printer;
        this.discDb = discDb;
        this.revenueFileOutput = revenueFileOutput;
    }

    /**
     * Creates a new sale and registers observers to that Sale.
     */
    public void createNewSale(Observer observer) {
        if (currentSale == null) {
            currentSale = new Sale();
            currentSale.register(revenueFileOutput);
            currentSale.register(observer);
        }
    }

    /**
     * Returns the running total of the current sale.
     *
     * @return the running total of the sale.
     */
    public Money getTotal() {
        return currentSale.getTotal();
    }

    /**
     * Adds an item to the current sale.
     *
     * @param itemID   The identifier of the item to add.
     * @param quantity The quantity of the item to add.
     * @throws ItemDoesNotExistException If the provided itemIdentifier doesn't match any of the supported item identifiers, this exception is thrown with error code 404 and a "Resource not found" message.
     * @throws DatabaseFailureException  If the provided itemIdentifier is "failure", this exception is thrown with error code 503 and a "Could not connect to database" message, simulating a database failure.
     * @return A string representing the added item and total price of the sale so far.
     */
    public ItemDTO addItem(String itemID, int quantity) throws ItemDoesNotExistException, DatabaseFailureException {
        Item item = invSys.getItem(itemID);
        if (item == null) return null;
        item = currentSale.addItem(item, quantity);
        return item.getItemDTO();
    }

    /**
     * Gets the remaining amount to be paid for the current sale.
     *
     * @return The remaining amount to be paid for the current sale.
     */
    public Money getRemainingAmount() {
        return currentSale.getRemainingAmount();
    }

    /**
     * Requests a discount for the current sale and customer.
     *
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
     *
     * @param amount The amount of the payment to add.
     * @return The remaining amount to be paid for the current sale.
     */
    public Money pay(Money amount) {
        currentSale.pay(amount);
        return currentSale.getRemainingAmount();
    }

    /**
     * Closes the current sale, pushing the sale information to external systems.
     * @throws SaleNotPaidException is thrown when a Sale is attempted to be closed before being completely paid for.
     * @return true if the sale is closed successfully, false otherwise.
     */
    public void closeSale() throws SaleNotPaidException {
        currentSale.closeSale();
        pushSaleToExternalSystems();
        this.currentSale = null;
    }

    private void pushSaleToExternalSystems() {
        accSys.logNewSale(currentSale.getSaleDTO());
        invSys.updateInventory(currentSale.getSaleDTO());
        printer.printReceipt(currentSale.getReceipt());
    }
}


