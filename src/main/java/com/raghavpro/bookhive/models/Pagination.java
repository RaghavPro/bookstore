package com.raghavpro.bookhive.models;

import com.raghavpro.bookhive.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a pagination object.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class Pagination {

    /**
     * If the previous page exists. {@code false} if currentPage is 1 else {@code true}.
     */
    private boolean hasPrevious = true;

    /**
     * If the next page exists. {@code false} if currentPage is lastPage else {@code true}.
     */
    private boolean hasNext = true;

    /**
     * The current page, user is viewing.
     */
    private int currentPage;

    /**
     * The total number of pages to view all the books.
     */
    private int totalPages;

    /**
     * A list of page numbers to display.
     */
    private List<Integer> displayPages;

    /**
     * Constructs new {@code Pagination}
     *
     * @param totalBooks Total number of book entries in the database.
     * @param currentPage The current page user is viewing. Can be seen in the query string.
     *                    We get it using {@code HttpServletRequest}'s getParameter() method.
     */

    public Pagination(float totalBooks, int currentPage) {
        totalPages = (int) Math.ceil(totalBooks / Constants.RESULTS_PER_PAGE);
        if (currentPage == 1)
            hasPrevious = false;
        if (currentPage == totalPages)
            hasNext = false;
        this.currentPage = currentPage;

        int start = Math.max(currentPage - 4, 1);
        if (start <= 3)
            start = 1;
        int end = currentPage + 4;
        if (end > totalPages)
            end = totalPages;
        displayPages = new ArrayList<>(Math.max(end-start, 0));
        for (int i = start; i <= end; i++) {
            displayPages.add(i);
        }

    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Integer> getDisplayPages() {
        return displayPages;
    }

    public void setDisplayPages(List<Integer> displayPages) {
        this.displayPages = displayPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
