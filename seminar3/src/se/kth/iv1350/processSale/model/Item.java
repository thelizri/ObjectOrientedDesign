package se.kth.iv1350.processSale.model;

public class Item {
    private String itemIdentifier;
    private double price;
    private String description;
    private double rateVAT;
    private int quantity;

    public Item(String itemIdentifier, double price, String description, double rateVAT) {
        this.itemIdentifier = itemIdentifier;
        this.price = price;
        this.description = description;
        this.rateVAT = rateVAT;
        this.quantity = 0;
    }
    public String getItemIdentifier() {
        return this.itemIdentifier;
    }

    public double getPrice() {
        return this.price;
    }

    public String getDescription() {
        return this.description;
    }

    public double getRateVAT() {
        return this.rateVAT;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
