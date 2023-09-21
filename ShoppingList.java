package ex1;

import java.util.Date;

public class ShoppingList {
    private String codeBill;
    private Customer customer;
    private Item item;
    private int quantity;
    private Date purchaseDate;

    public ShoppingList(String codeBill, Customer customer, Item item, int quantity, Date purchaseDate) {
        this.codeBill = codeBill;
        this.customer = customer;
        this.item = item;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }
}
