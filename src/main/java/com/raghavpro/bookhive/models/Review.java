package com.raghavpro.bookhive.models;

import java.sql.Date;

/**
 * Represents a <code>Review</code> of the book by a user.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class Review {

    /**
     * The review id
     */
    private int id;

    /**
     * International Standard Book Number, used to identify a book.
     */
    private String isbn;

    /**
     * Name of the person who gave the review.
     */
    private String reviewer;

    /**
     * Number of stars given to the book.
     */
    private int stars;

    /**
     * The title of the reivew.
     */
    private String title;

    /**
     * The review of the book.
     */
    private String review;

    /**
     * The review date.
     */
    private String reviewDate;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}
