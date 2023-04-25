package se.kth.iv1350.processSale.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.processSale.dto.*;

public class Sale {
    private LocalDateTime dateTime;
    private List<Item> itemList;
    private int totalPrice;
    private double totalVAT;
    private int amountPaid;
    private int change;

    public Sale() {
        this.dateTime = LocalDateTime.now();
        this.itemList = new ArrayList<>();
        this.totalPrice = 0;
        this.totalVAT = 0;
        this.amountPaid = 0;
        this.change = 0;
    }

    public int getTotal() {
        return totalPrice;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public SaleDTO getSaleDTO() {
        return new SaleDTO(dateTime, itemList, totalPrice, totalVAT, amountPaid, change);
    }

    public SaleDTO getReceipt() {
        return new SaleDTO(dateTime, itemList, totalPrice - (int)totalVAT, totalVAT, amountPaid, change);
    }

    public void addItem(Item item, int quantity) {
        item.setQuantity(quantity);
        itemList.add(item);
        int itemPrice = (int) (item.getPrice() * quantity);
        totalPrice += itemPrice;
        totalVAT += itemPrice * item.getRateVAT();
    }

    public int applyDiscount(int amount) {
        int maxDiscount = totalPrice;
        int actualDiscount = Math.min(amount, maxDiscount);
        totalPrice -= actualDiscount;
        return actualDiscount;
    }

    public void pay(int amount) {
        amountPaid += amount;
    }
}


