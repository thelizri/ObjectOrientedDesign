package se.kth.iv1350.processSale.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processSale.dto.*;

public class Sale {
    private LocalDateTime dateTime;
    private List<Item> itemList;
    private float totalPrice;
    private float discount;
    private float totalVAT;
    private float amountPaid;
    private float change;

    public Sale() {
        this.dateTime = LocalDateTime.now();
        this.itemList = new ArrayList<>();
        this.totalPrice = 0;
        this.totalVAT = 0;
        this.amountPaid = 0;
        this.change = 0;
        this.discount = 0;
    }

    public float getTotal() {
        return totalPrice;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public float getRemainingAmount() {
        return totalPrice - discount - amountPaid;
    }

    public SaleDTO getSaleDTO() {
        return new SaleDTO(dateTime, itemList, totalPrice, totalVAT, amountPaid, change);
    }

    public SaleDTO getReceipt() {
        return new SaleDTO(dateTime, itemList, totalPrice, totalVAT, amountPaid, change);
    }

    public void addItem(Item item, int quantity) {
        item.setQuantity(quantity);
        itemList.add(item);
        int itemPrice = (int) (item.getPrice() * quantity);
        totalPrice += itemPrice;
        totalVAT += itemPrice * item.getRateVAT();
    }

    public void applyDiscount(float amount) {
        this.discount = amount;
    }

    public void pay(float amount) {
        amountPaid += amount;
    }
}


