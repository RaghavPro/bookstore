package com.raghavpro.bookhive.models;

import java.util.List;

/**
 * Represents a book.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class Book {

    /**
     * International Standard Book Number, used to identify a book.
     */
    private String isbn;

    /**
     * Title of the book.
     */
    private String title;

    /**
     * Author of the book.
     */
    private String author;

    /**
     * Price of the book.
     */
    private int price;

    /**
     * MRP of the book.
     */
    private int mrp;

    /**
     * Number of pages
     */
    private String pages;

    /**
     * Language of the book.
     */
    private String language;

    /**
     * The book dimensions.
     */
    private String dimensions;

    /**
     * Publisher of the book.
     */
    private String publisher;

    /**
     * A short summary of the book.
     */
    private String summary;

    /**
     * A short note about the author.
     */
    private String aboutAuthor;

    /**
     * Book review, if any.
     */
    private String review;

    /**
     * A <code>List</code> of reviews of this book.
     */
    private List<Review> userReviews;

    /**
     * Number of reviews posted
     */
    private int noOfReviews;

    /**
     * Total number of stars from all the reviews.
     */
    private int totalStars;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMrp() {
        return mrp;
    }

    public void setMrp(int mrp) {
        this.mrp = mrp;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAboutAuthor() {
        return aboutAuthor;
    }

    public void setAboutAuthor(String aboutAuthor) {
        this.aboutAuthor = aboutAuthor;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<Review> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<Review> userReviews) {
        this.userReviews = userReviews;
    }

    public int getNoOfReviews() {
        return noOfReviews;
    }

    public void setNoOfReviews(int no_of_reviews) {
        this.noOfReviews = no_of_reviews;
    }


    public int getTotalStars() {
        return totalStars;
    }

    public void setTotalStars(int totalStars) {
        this.totalStars = totalStars;
    }
}
