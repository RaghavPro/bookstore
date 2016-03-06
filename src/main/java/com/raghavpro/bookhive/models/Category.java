package com.raghavpro.bookhive.models;

/**
 * Represents a book category
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class Category {

    /**
     * Represents a category id.
     */
    private long categoryId;

    /**
     * Represents a category id of the parent.
     */
    private long parentId;

    /**
     * Name of the category
     */
    private String categoryName;


    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}