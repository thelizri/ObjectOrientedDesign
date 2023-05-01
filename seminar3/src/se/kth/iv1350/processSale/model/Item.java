package se.kth.iv1350.processSale.model;

import se.kth.iv1350.processSale.dto.ItemDTO;
import se.kth.iv1350.processSale.utils.Money;

/**
 * Represents an item that can be sold in a sale.
 */
public class Item {
    private final String itemIdentifier;
    private final Money price; //Price for a single unit
    private final Money priceExcludingVAT;
    private final String description;
    private final Money rateVAT; //There are three different VAT rates: 25%, 12% and 6%.
    private final Money amountVAT; //VAT for a single unit
    private int quantity;

    /**
     * Creates a new instance of the Item class.
     *
     * @param itemIdentifier    The identifier of the item.
     * @param priceExcludingVAT The price of the item excluding VAT.
     * @param description       The description of the item.
     * @param rateVAT           The VAT rate of the item.
     */
    public Item(String itemIdentifier, Money priceExcludingVAT, String description, Money rateVAT) {
        this.itemIdentifier = itemIdentifier;
        this.priceExcludingVAT = priceExcludingVAT;
        this.description = description;
        this.rateVAT = rateVAT;
        this.quantity = 0;
        this.amountVAT = priceExcludingVAT.multiply(rateVAT);
        this.price = priceExcludingVAT.add(amountVAT);
    }

    /**
     * Gets the price of the item excluding VAT.
     *
     * @return The price of the item excluding VAT.
     */
    public Money getPriceExcludingVAT() {
        return priceExcludingVAT;
    }

    /**
     * Returns a DTO (data transfer object) for this item, containing only the information needed to represent the item in a sale.
     *
     * @return An ItemDTO instance representing this item.
     */
    public ItemDTO getItemDTO() {
        return new ItemDTO(this.itemIdentifier, this.price, this.description, this.rateVAT, this.amountVAT, this.quantity, this.getTotalPrice(), this.getAmountVAT());
    }

    /**
     * Gets the identifier of the item.
     *
     * @return The identifier of the item.
     */
    public String getItemIdentifier() {
        return this.itemIdentifier;
    }

    /**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */
    public Money getPrice() {
        return this.price;
    }

    /**
     * Gets the description of the item.
     *
     * @return The description of the item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the VAT rate of the item.
     *
     * @return The VAT rate of the item.
     */
    public Money getRateVAT() {
        return this.rateVAT;
    }

    /**
     * Gets the VAT amount of the item.
     *
     * @return The VAT amount of the item.
     */
    public Money getAmountVAT() {
        return this.amountVAT;
    }

    /**
     * Gets the quantity of the item.
     *
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item.
     *
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates the total price of the item based on the price and quantity.
     *
     * @return The total price of the item, as a Money instance.
     */
    public Money getTotalPrice() {
        return this.price.multiply(this.quantity);
    }

    /**
     * Calculates the total VAT amount of the item based on the price, quantity, and VAT rate.
     *
     * @return The total VAT amount of the item, as a Money instance.
     */
    public Money getTotalVAT() {
        return this.amountVAT.multiply(this.quantity);
    }
}
