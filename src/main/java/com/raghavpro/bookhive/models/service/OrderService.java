package com.raghavpro.bookhive.models.service;

import com.raghavpro.bookhive.models.Book;
import com.raghavpro.bookhive.models.Order;
import com.raghavpro.bookhive.models.Review;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handle the services related to order management
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public class OrderService extends Service<Order> {

    /**
     * Constructs a new {@code Service}
     *
     * @param conn The connection object.
     */
    public OrderService(Connection conn) {
        super(conn);
    }

    @Override
    public List<Order> get(Object[] args, String query, Object... queryParams) {
        List<Order> orders = null;
        ResultSet rs = null;
        try {
            /* Executes the query */
            rs = execute(query, queryParams);
            if (rs == null)
                return null; // idk?
                orders = new ArrayList<>();
            Service<Book> bookService = new BookService(conn);
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                int id = rs.getInt("orderid");
                int quantity = rs.getInt("quantity");
                int status = rs.getInt("status"); //1-processing, 2-approved, 3-rejected


                query = "SELECT * FROM book WHERE isbn = ?";
                List<Book> bookList = bookService.get(new Object[]{1}, query, isbn);
                if (bookList != null) {
                    Order order = new Order();
                    order.setId(id);
                    order.setQuantity(quantity);
                    order.setBook(bookList.get(0));
                    orders.add(order);
                }
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
        return orders;
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
