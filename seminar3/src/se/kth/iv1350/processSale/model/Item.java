package se.kth.iv1350.processSale.model;

/**
 * Represents an item that can be sold in a sale.
 */
public class Item {
    private final String itemIdentifier;
    private final float price;
    private final String description;
    private final float rateVAT; //There are three different VAT rates: 25%, 12% and 6%.
    private final float amountVAT; //Price*VATrate
    private int quantity;

    /**
     * Creates a new instance of the Item class.
     * @param itemIdentifier The identifier of the item.
     * @param price The price of the item.
     * @param description The description of the item.
     * @param rateVAT The VAT rate of the item.
     */
    public Item(String itemIdentifier, float price, String description, float rateVAT) {
        this.itemIdentifier = itemIdentifier;
        this.price = price;
        this.description = description;
        this.rateVAT = rateVAT;
        this.quantity = 0;
        this.amountVAT = rateVAT*price;
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
    public float getPrice() {
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
    public float getRateVAT() {
        return this.rateVAT;
    }

    /**
     * Gets the VAT amount of the item.
     * @return The VAT amount of the item.
     */
    public float getAmountVAT(){
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
