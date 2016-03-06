package com.raghavpro.bookhive.models.service;

import com.raghavpro.bookhive.models.Review;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Gets the review data from the database and stores it in {@code Review} bean and then returns an {@code ArrayList}.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class ReviewService extends Service<Review> {
    /**
     * Constructs a new {@code Service}
     *
     * @param conn The connection object.
     */
    public ReviewService(Connection conn) {
        super(conn);
    }

    public List<Review> get(Object[] args, String query, Object... queryParams) {
        List<Review> reviews = null;
        ResultSet rs = null;
        try {
            /* Executes the query */
            rs = execute(query, queryParams);
            if (rs == null)
                return null; //null
            reviews = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String isbn = rs.getString("isbn");
                String reviewer = rs.getString("reviewer");
                int stars = rs.getInt("stars");
                String title = rs.getString("title");
                String reviewText = rs.getString("review");
                Date review_date = rs.getDate("review_date");

                Review review = new Review();
                review.setId(id);
                review.setIsbn(isbn);
                review.setReviewer(reviewer);
                review.setStars(stars);
                review.setTitle(title);
                review.setReview(reviewText);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
                String date = sdf.format(review_date);

                review.setReviewDate(date);

                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return reviews;
    }

    @Override
    public boolean dataManipulation(String query, Object... queryParams) {
        int rowsEffected = 0;
        try {
            rowsEffected = executeDML(query, queryParams);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsEffected > 0;
    }
}
