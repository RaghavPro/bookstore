package com.raghavpro.bookhive.models;

/**
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class Order {

    /**
     * Book the user ordered.
     */
    private Book book;

    /**
     * The user
     */
    private int id;

    private int quantity;

    /**
     * Status of order. 1 - processing, 2 - approved, 3 - rejected
     */
    private int status;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
