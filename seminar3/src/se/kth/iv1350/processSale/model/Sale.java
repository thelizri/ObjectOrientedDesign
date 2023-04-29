package se.kth.iv1350.processSale.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processSale.dto.*;

/**
 The Sale class represents a sale in the point of sale system.
 It contains information about the items in the sale, the total price of the sale,
 discounts applied, amount paid, and change given.
 */
public class Sale {
    private LocalDateTime dateTime;
    private List<Item> itemList;
    private float totalPrice;
    private float discount;
    private float totalVAT;
    private float amountPaid;
    private float change;

    /**
     * Constructs a new instance of the Sale class with default values.
     */
    public Sale() {
        this.dateTime = LocalDateTime.now();
        this.itemList = new ArrayList<>();
        this.totalPrice = 0;
        this.totalVAT = 0;
        this.amountPaid = 0;
        this.change = 0;
        this.discount = 0;
    }

    /**
     * Gets the total price of the sale.
     * @return The total price of the sale.
     */
    public float getTotal() {
        return totalPrice;
    }

    /**
     * Gets the amount paid for the sale.
     * @return The amount paid for the sale.
     */
    public float getAmountPaid() {
        return amountPaid;
    }

    /**
     * Calculates and returns the remaining amount to be paid for the sale.
     * @return The remaining amount to be paid for the sale.
     */
    public float getRemainingAmount() {
        return totalPrice - discount - amountPaid;
    }

    /**
     * Gets a SaleDTO object representing the sale.
     * @return A SaleDTO object representing the sale.
     */
    public SaleDTO getSaleDTO() {
        return new SaleDTO(dateTime, itemList, totalPrice, totalVAT, amountPaid, change);
    }

    /**
     * Generates a receipt string for the sale.
     * @return A receipt string for the sale.
     */
    public String getReceipt() {
        StringBuilder receiptBuilder = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");

        // Add header information
        receiptBuilder.append(String.format("%-10s %23s\n", "Receipt", dateTime.format(formatter)));

        // Add item information
        for (Item item : itemList) {
            receiptBuilder.append(String.format("%-20s %-3d %6.2f kr\n", item.getDescription(), item.getQuantity(), item.getPrice() * item.getQuantity()));
        }

        // Add summary information
        receiptBuilder.append(String.format("\n%-20s %10.2f kr\n", "Total price:", totalPrice));
        receiptBuilder.append(String.format("%-20s %10.2f kr\n", "Discount:", discount));
        receiptBuilder.append(String.format("%-20s %10.2f kr\n", "Total VAT:", totalVAT));
        receiptBuilder.append(String.format("%-20s %10.2f kr\n", "Amount paid:", amountPaid));
        receiptBuilder.append(String.format("%-20s %10.2f kr\n", "Change:", (amountPaid - totalPrice + discount)));

        return receiptBuilder.toString();
    }


    /**
     * Adds an item to the sale with the specified quantity.
     * @param item The item to add to the sale.
     * @param quantity The quantity of the item to add to the sale.
     */
    public void addItem(Item item, int quantity) {
        item.setQuantity(quantity);
        itemList.add(item);
        int itemPrice = (int) (item.getPrice() * quantity);
        totalPrice += itemPrice;
        totalVAT += itemPrice * item.getRateVAT();
    }

    /**
     * Applies a discount to the sale.
     * @param amount The amount of the discount to apply.
     */
    public void applyDiscount(float amount) {
        this.discount = amount;
    }

    /**
     * Closes the sale, indicating that it is complete.
     * @return true if the sale has been closed successfully, false otherwise.
     */
    public boolean closeSale(){
        if (getRemainingAmount() > 0){
            return false;
        }
        return true;
    }

    /**
     * Adds a payment to the sale.
     * @param amount The amount of the payment to add to the sale.
     */
    public void pay(float amount) {
        amountPaid += amount;
    }
}


