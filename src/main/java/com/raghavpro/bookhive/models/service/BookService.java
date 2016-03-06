package com.raghavpro.bookhive.models.service;

import com.raghavpro.bookhive.Constants;
import com.raghavpro.bookhive.models.Book;
import com.raghavpro.bookhive.models.Review;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Gets the book details and stores it in {@code Book} bean and then returns an {@code ArrayList}.
 *
 * First, the query gets executed and the we get the {@code ResultSet} object. Then we iterate on the {@code ResultSet}
 * and get the desired columns store it in a bean and then add it to an {@code ArrayList}.
 * We also execute a query to get review data from the reviews table for each result.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class BookService extends Service<Book> {

    /**
     * Constructs a new {@code Service}
     *
     * @param conn The connection object.
     */
    public BookService(Connection conn) {
        super(conn);
    }

    @Override
    public List<Book> get(Object[] args, String query, Object... queryParams) {
        List<Book> books = null;
        ResultSet rs = null;
        try {
            /* Executes the query */
            rs = execute(query, queryParams);
            if (rs == null)
                return null; // idk?
            books = new ArrayList<>();
            ReviewService service = new ReviewService(conn);
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int price = rs.getInt("price");
                int mrp = rs.getInt("mrp");
                String pages = rs.getString("pages");
                String language = rs.getString("lang");
                String dimensions = rs.getString("dimensions");
                String publisher = rs.getString("publisher");
                String summary = rs.getString("summary");
                String aboutAuthor = rs.getString("about_author");
                String review = rs.getString("review");

                Book book = new Book();
                book.setIsbn(isbn);
                book.setTitle(title);
                book.setAuthor(author);
                book.setPrice(price);
                book.setMrp(mrp);
                book.setPages(pages);
                book.setLanguage(language);
                book.setDimensions(dimensions);
                book.setPublisher(publisher);
                book.setSummary(summary);
                book.setAboutAuthor(aboutAuthor);
                book.setReview(review);

                query = "SELECT * FROM review WHERE isbn = ?";
                List<Review> user_reviews = service.get(null, query, isbn);
                book.setUserReviews(user_reviews);
                book.setNoOfReviews(user_reviews.size());
                for (Review user_review : user_reviews)
                    book.setTotalStars(book.getTotalStars() + user_review.getStars());

                books.add(book);
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
        return books;
    }

    @Override
    public boolean dataManipulation(String query, Object... queryParams) {
        return false;
    }
}
