package se.kth.iv1350.processSale.dto;

/**
 * Represents an item that can be sold in a sale, as a DTO (data transfer object).
 */
public class ItemDTO {
    private final String itemIdentifier;
    private final float price;
    private final String description;
    private final float rateVAT;
    private final float amountVAT;
    private final int quantity;

    /**
     * Creates a new instance of the ItemDTO class.
     * @param itemIdentifier The identifier of the item.
     * @param price The price of the item.
     * @param description The description of the item.
     * @param rateVAT The VAT rate of the item.
     * @param amountVAT The VAT amount of the item.
     * @param quantity The quantity of the item.
     */
    public ItemDTO(String itemIdentifier, float price, String description, float rateVAT, float amountVAT, int quantity) {
        this.itemIdentifier = itemIdentifier;
        this.price = price;
        this.description = description;
        this.rateVAT = rateVAT;
        this.amountVAT = amountVAT;
        this.quantity = quantity;
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
}
