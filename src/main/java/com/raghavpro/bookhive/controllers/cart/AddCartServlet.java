package com.raghavpro.bookhive.controllers.cart;

import com.raghavpro.bookhive.models.CartItem;
import com.raghavpro.bookhive.models.User;
import com.raghavpro.bookhive.models.jdbc.DatabaseConnectivity;
import com.raghavpro.bookhive.models.service.CartService;
import com.raghavpro.bookhive.models.service.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Handles adding of a book to user's cart.
 * Checks if the book is already there in the database. If it is, updates the quantity. If not, creates a new record.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
@WebServlet(
        name = "AddCartServlet",
        urlPatterns = {"/addcart"}
)
public class AddCartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        int quantity = 1;
        User user = (User) request.getSession(false).getAttribute("user");

        Connection conn = DatabaseConnectivity.getConnection();
        Service<CartItem> cartService = new CartService(conn);
        String query = "SELECT quantity FROM user_cart WHERE userid = ? AND isbn = ?";
        Object[] queryParams = {
                user.getUserId(), isbn
        };
        List<CartItem> cartItems = cartService.get(new Object[] {1}, query, queryParams);
        if (cartItems != null && cartItems.size() > 0) {
            query = "UPDATE user_cart SET quantity = ? WHERE isbn = ? AND userid = ?";
            queryParams = new Object[]{
                cartItems.get(0).getQuantity() + 1, isbn, user.getUserId()
            };
        } else {
            query = "INSERT INTO user_cart VALUES(?, ?, ?)";
            queryParams = new Object[]{
                    user.getUserId(), isbn, quantity
            };
        }

        boolean success = cartService.dataManipulation(query, queryParams);
        cartService.close();

        if (!success) {
            //if fails send back to details page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/details?isbn=" + isbn);
            dispatcher.forward(request, response);
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart?isbn=" + isbn);
        dispatcher.forward(request, response);
    }
}
