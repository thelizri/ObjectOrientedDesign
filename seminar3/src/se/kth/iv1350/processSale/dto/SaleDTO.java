package se.kth.iv1350.processSale.dto;

import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.model.Sale;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaleDTO {
    private LocalDateTime dateTime;
    private List<Item> itemList;
    private float totalPrice;
    private float totalVAT;
    private float amountPaid;
    private float change;

    public SaleDTO(LocalDateTime dateTime, List<Item> itemList, float totalPrice, float totalVAT, float amountPaid, float change) {
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

    public float getTotalPrice() {
        return totalPrice;
    }

    public float getTotalVAT() {
        return totalVAT;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public float getChange() {
        return change;
    }

}
