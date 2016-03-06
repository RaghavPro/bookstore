package com.raghavpro.bookhive.models.service;

import com.raghavpro.bookhive.models.Book;
import com.raghavpro.bookhive.models.CartItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Raghav (Email: Raghav.Sharma@Outlook.in) (Roll No.: 1208019346)
 */
public class CartService extends Service<CartItem> {

    /**
     * Constructs a new {@code Service}
     *
     * @param conn The connection object.
     */
    public CartService(Connection conn) {
        super(conn);
    }

    @Override
    public List<CartItem> get(Object[] args, String query, Object... queryParams) {
        ResultSet rs = null;
        List<CartItem> cartItems = null;
        Service<Book> bookService = new BookService(conn);

        List<Book> books = null;
        //If we need all cart items from a user
        if (args != null && args.length > 0 && (args[0] != null || (int) args[0] != 0))
            if (args[0] instanceof String) {
                books = bookService.get(null, (String) args[0], queryParams);
            } else {
                String bookQuery = "SELECT * FROM book where isbn = ?";
                books = bookService.get(args, bookQuery, queryParams[1]);
            }
        if (books == null || books.size() <= 0)
            return null;
        try {
            rs = execute(query, queryParams);
            if (rs == null)
                return null;
            cartItems = new ArrayList<>();
            int i = 0;
            while (rs.next()) {
                int quantity = rs.getInt("quantity");
                Book book = books.get(i);

                CartItem cartItem = new CartItem();
                cartItem.setQuantity(quantity);
                cartItem.setBook(book);

                cartItems.add(cartItem);
                i++;
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

        return cartItems;
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
