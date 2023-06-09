package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.dto.SaleDTO;
import se.kth.iv1350.processSale.exception.SaleNotPaidException;
import se.kth.iv1350.processSale.utils.Money;
import se.kth.iv1350.processSale.utils.Observer;
import se.kth.iv1350.processSale.utils.Subject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Sale class represents a sale in the point of sale system.
 * It contains information about the items in the sale, the total price of the sale,
 * discounts applied, amount paid, and change given.
 */
public class Sale implements Subject {
    private final LocalDateTime dateTime;
    private final List<Item> itemList;
    private final List<Observer> observersList;
    private Money totalPrice;
    private Money discount;
    private Money totalVAT;
    private Money amountPaid;

    /**
     * Constructs a new instance of the Sale class with default values.
     */
    public Sale() {
        this.dateTime = LocalDateTime.now();
        this.itemList = new ArrayList<>();
        this.observersList = new ArrayList<>();
        this.totalPrice = new Money();
        this.totalVAT = new Money();
        this.amountPaid = new Money();
        this.discount = new Money();
    }

    /**
     * Gets the total price of the sale.
     *
     * @return The total price of the sale.
     */
    public Money getTotal() {
        return totalPrice;
    }

    /**
     * Gets the total price of the sale, with discount subtracted.
     *
     * @return The total price of the sale, with discount subtracted.
     */
    public Money getTotalPricePaid() {
        return totalPrice.subtract(discount);
    }

    /**
     * Gets the amount paid for the sale.
     *
     * @return The amount paid for the sale.
     */
    public Money getAmountPaid() {
        return amountPaid;
    }

    /**
     * Calculates and returns the remaining amount to be paid for the sale.
     *
     * @return The remaining amount to be paid for the sale.
     */
    public Money getRemainingAmount() {
        Money remaining = totalPrice.subtract(discount).subtract(amountPaid);
        if (remaining.isGreaterThanZero()) {
            return remaining;
        }
        return new Money();
    }

    /**
     * Calculates and returns the change to give the customer.
     *
     * @return The amount of change the customer should receive back.
     */
    public Money getChange() {
        return amountPaid.subtract(totalPrice).subtract(discount);
    }

    /**
     * Gets a SaleDTO object representing the sale.
     *
     * @return A SaleDTO object representing the sale.
     */
    public SaleDTO getSaleDTO() {
        return new SaleDTO(dateTime, itemList, totalPrice, totalVAT, amountPaid, this.getChange(), discount);
    }

    /**
     * Generates a receipt string for the sale.
     *
     * @return A receipt string for the sale.
     */
    public String getReceipt() {
        StringBuilder receiptBuilder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");

        // Add header information
        receiptBuilder.append(String.format("%-10s %23s\n", "Receipt", dateTime.format(formatter)));

        // Add item information
        for (Item item : itemList) {
            Money itemPriceTotal = item.getPriceIncludingVAT().multiply(item.getQuantity());
            receiptBuilder.append(String.format("%-20s %-3d %6.2f kr\n", item.getDescription(), item.getQuantity(), itemPriceTotal.getAmountFloat()));
        }

        // Add summary information
        receiptBuilder.append(String.format("\n%-20s %10.2f kr\n", "Total price:", totalPrice.getAmountFloat()));

        if (discount.isGreaterThanZero()) {
            receiptBuilder.append(String.format("%-20s %10.2f kr\n", "Discount:", discount.negate().getAmountFloat()));
            receiptBuilder.append(String.format("%-20s %10.2f kr\n", "Discounted price:", totalPrice.subtract(discount).getAmountFloat()));
        }
        receiptBuilder.append(String.format("%-20s %10.2f kr\n", "Total VAT:", totalVAT.getAmountFloat()));
        receiptBuilder.append(String.format("%-20s %10.2f kr\n", "Amount paid:", amountPaid.getAmountFloat()));
        receiptBuilder.append(String.format("%-20s %10.2f kr\n", "Change:", this.getChange().getAmountFloat()));

        return receiptBuilder.toString();
    }


    /**
     * Adds an item to the sale with the specified quantity.
     *
     * @param item     The item to add to the sale.
     * @param quantity The quantity of the item to add to the sale.
     */
    public Item addItem(Item item, int quantity) {
        item.setQuantity(quantity);
        totalPrice = totalPrice.add(item.getTotalPrice());
        totalVAT = totalVAT.add(item.getTotalVAT());
        return addItemToList(item, quantity);
    }

    private Item addItemToList(Item item, int quantity) {
        for (Item listItem : itemList) {
            if (item.equals(listItem)) {
                listItem.addQuantity(quantity);
                return listItem;
            }
        }
        itemList.add(item);
        return item;
    }

    /**
     * Applies a discount to the sale.
     *
     * @param amount The amount of the discount to apply.
     */
    public void applyDiscount(Money amount) {
        this.discount = amount;
    }

    /**
     * Closes the sale, indicating that it is complete.
     *
     * @return true if the sale has been closed successfully, false otherwise.
     */
    public void closeSale() throws SaleNotPaidException {
        if (this.getRemainingAmount().isGreaterThanZero()) {
            throw new SaleNotPaidException(400, "Bad Request");
        } else {
            notifyObserver();
        }
    }

    /**
     * Adds a payment to the sale.
     *
     * @param amount The amount of the payment to add to the sale.
     */
    public void pay(Money amount) {
        amountPaid = amountPaid.add(amount);
    }

    /**
     * Registers an observer to be notified of changes.
     *
     * @param observer the observer to register
     */
    @Override
    public void register(Observer observer) {
        observersList.add(observer);
    }

    /**
     * Unregisters an observer so that it will no longer be notified of changes.
     *
     * @param observer the observer to unregister
     */
    @Override
    public void unregister(Observer observer) {
        observersList.remove(observer);
    }

    /**
     * Notifies all registered observers of a change.
     */
    @Override
    public void notifyObserver() {
        for (Observer observer : observersList) {
            observer.update(getTotalPricePaid());
        }
    }
}


