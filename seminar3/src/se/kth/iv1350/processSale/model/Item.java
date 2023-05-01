package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.dto.ItemDTO;
import se.kth.iv1350.processSale.utils.Money;

/**
 * Represents an item that can be sold in a sale.
 */
public class Item {
    private final String itemIdentifier;
    private final Money price;
    private final String description;
    private final Money rateVAT; //There are three different VAT rates: 25%, 12% and 6%.
    private final Money amountVAT; //Price*VATrate
    private int quantity;

    /**
     * Creates a new instance of the Item class.
     * @param itemIdentifier The identifier of the item.
     * @param price The price of the item.
     * @param description The description of the item.
     * @param rateVAT The VAT rate of the item.
     */
    public Item(String itemIdentifier, Money price, String description, Money rateVAT) {
        this.itemIdentifier = itemIdentifier;
        this.price = price;
        this.description = description;
        this.rateVAT = rateVAT;
        this.quantity = 0;
        this.amountVAT = price.multiply(rateVAT);
    }

    /**
     * Returns a DTO (data transfer object) for this item, containing only the information needed to represent the item in a sale.
     * @return An ItemDTO instance representing this item.
     */
    public ItemDTO getItemDTO(){
        return new ItemDTO(this.itemIdentifier, this.price, this.description, this.rateVAT, this.amountVAT, this.quantity);
    }

    /**
     * Gets the identifier of the item.
     * @return The identifier of the item.
     */
    public String getItemIdentifier() {
        return this.itemIdentifier;
    }

    /**
     * Gets the price of the item.
     * @return The price of the item.
     */
    public Money getPrice() {
        return this.price;
    }

    /**
     * Gets the description of the item.
     * @return The description of the item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the VAT rate of the item.
     * @return The VAT rate of the item.
     */
    public Money getRateVAT() {
        return this.rateVAT;
    }

    /**
     * Gets the VAT amount of the item.
     * @return The VAT amount of the item.
     */
    public Money getAmountVAT(){
        return this.amountVAT;
    }

    /**
     * Gets the quantity of the item.
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
