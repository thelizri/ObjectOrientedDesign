package se.kth.iv1350.processSale.model;

public class Item {
    private final String itemIdentifier;
    private final float price;
    private final String description;
    private final float rateVAT; //There are three different VAT rates: 25%, 12% and 6%.
    private final float amountVAT; //Price*VATrate
    private int quantity;

    public Item(String itemIdentifier, float price, String description, float rateVAT) {
        this.itemIdentifier = itemIdentifier;
        this.price = price;
        this.description = description;
        this.rateVAT = rateVAT;
        this.quantity = 0;
        this.amountVAT = rateVAT*price;
    }
    public String getItemIdentifier() {
        return this.itemIdentifier;
    }

    public float getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }

    public float getRateVAT() {
        return this.rateVAT;
    }

    public float getAmountVAT(){
        return this.amountVAT;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
