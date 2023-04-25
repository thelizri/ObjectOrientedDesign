package se.kth.iv1350.processSale.dto;

import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.Sale;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleDTO {
    private LocalDateTime dateTime;
    private List<Item> itemList;
    private int totalPrice;
    private double totalVAT;
    private int amountPaid;
    private int change;
    private String customerID;

    public SaleDTO(LocalDateTime dateTime, List<Item> itemList, int totalPrice, double totalVAT, int amountPaid, int change) {
        this.dateTime = dateTime;
        this.itemList = new ArrayList<Item>();
        for (Item item : itemList) {
            this.itemList.add(item);
        }
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.amountPaid = amountPaid;
        this.change = change;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<Item> getItems() {
        return itemList;
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
