package se.kth.iv1350.processSale.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private LocalDateTime dateTime;
    private List<Item> items;
    private int totalPrice;
    private double totalVAT;
    private int amountPaid;
    private int change;

    public Sale() {
        this.dateTime = LocalDateTime.now();
        this.items = new ArrayList<>();
        this.totalPrice = 0;
        this.totalVAT = 0;
        this.amountPaid = 0;
        this.change = 0;
    }

    public int getTotal() {
        return totalPrice;
    }

    public SaleDTO getSale() {
        return new SaleDTO(dateTime, items, totalPrice, totalVAT, amountPaid, change);
    }

    public SaleDTO getReceipt() {
        return new SaleDTO(dateTime, items, totalPrice - (int)totalVAT, totalVAT, amountPaid, change);
    }

    public void addItem(ItemDTO item, int quantity) {
        items.add(new Item(item, quantity));
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
        change = amountPaid - totalPrice;
    }

    private class Item {
        private ItemDTO item;
        private int quantity;

        public Item(ItemDTO item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        public String getName() {
            return item.getDescription();
        }

        public int getQuantity() {
            return quantity;
        }

        public int getPrice() {
            return (int) (item.getPrice() * quantity);
        }

        public double getVAT() {
            return getPrice() * item.getRateVAT();
        }
    }
}


