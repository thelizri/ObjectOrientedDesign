import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleDTO {
    private LocalDateTime dateTime;
    private List<ItemDTO> items;
    private int totalPrice;
    private double totalVAT;
    private int amountPaid;
    private int change;
    private String customerID;

    public SaleDTO(LocalDateTime dateTime, List<Item> items, int totalPrice, double totalVAT, int amountPaid, int change) {
        this.dateTime = dateTime;
        this.items = new ArrayList<ItemDTO>();
        for (Item item : items) {
            this.items.add(item.getItem());
        }
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.amountPaid = amountPaid;
        this.change = change;
    }

    public SaleDTO(LocalDateTime dateTime, List<ItemDTO> items, int totalPrice, double totalVAT, int amountPaid, int change) {
        this.dateTime = dateTime;
        this.items = items;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.amountPaid = amountPaid;
        this.change = change;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public int getChange() {
        return change;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}
