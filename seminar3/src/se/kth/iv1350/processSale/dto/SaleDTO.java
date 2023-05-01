package se.kth.iv1350.processSale.dto;

import se.kth.iv1350.processSale.model.Item;
import se.kth.iv1350.processSale.utils.Money;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * SaleDTO represents a data transfer object for a sale.
 */
public class SaleDTO {

    private final LocalDateTime dateTime;
    private final List<ItemDTO> itemList;
    private final Money totalPrice;
    private final Money totalVAT;
    private final Money amountPaid;
    private final Money change;

    /**
     * Constructs a new SaleDTO object with the specified parameters.
     *
     * @param dateTime   The date and time of the sale.
     * @param itemList   The list of items sold in the sale.
     * @param totalPrice The total price of the sale.
     * @param totalVAT   The total value-added tax (VAT) of the sale.
     * @param amountPaid The amount paid for the sale.
     * @param change     The change given to the customer after the sale.
     */
    public SaleDTO(LocalDateTime dateTime, List<Item> itemList, Money totalPrice, Money totalVAT, Money amountPaid, Money change) {
        this.dateTime = dateTime;
        this.itemList = new ArrayList<>();
        for (Item item : itemList) {
            this.itemList.add(item.getItemDTO());
        }
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.amountPaid = amountPaid;
        this.change = change;
    }

    /**
     * Returns the date and time of the sale.
     *
     * @return The date and time of the sale.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns the list of items sold in the sale.
     *
     * @return The list of items sold in the sale.
     */
    public List<ItemDTO> getItems() {
        return itemList;
    }

    /**
     * Returns the total price of the sale.
     *
     * @return The total price of the sale.
     */
    public Money getTotalPrice() {
        return totalPrice;
    }

    /**
     * Returns the total value-added tax (VAT) of the sale.
     *
     * @return The total value-added tax (VAT) of the sale.
     */
    public Money getTotalVAT() {
        return totalVAT;
    }

    /**
     * Returns the amount paid for the sale.
     *
     * @return The amount paid for the sale.
     */
    public Money getAmountPaid() {
        return amountPaid;
    }

    /**
     * Returns the change given to the customer after the sale.
     *
     * @return The change given to the customer after the sale.
     */
    public Money getChange() {
        return change;
    }

}

