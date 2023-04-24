public class ItemDTO {
    private String itemIdentifier;
    private double price;
    private String description;
    private double rateVAT;

    public ItemDTO(String itemIdentifier, double price, String description, double rateVAT) {
        this.itemIdentifier = itemIdentifier;
        this.price = price;
        this.description = description;
        this.rateVAT = rateVAT;
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
}
