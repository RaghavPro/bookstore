package com.raghavpro.bookhive.models;

/**
 * Represents an item in cart
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class CartItem {

    /**
     * The quantity of an item.
     */
    private int quantity;

    /**
     * The item, a book in our case.
     */
    private Book book;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


}
