package com.raghavpro.bookhive.controllers.cart;

import com.raghavpro.bookhive.models.CartItem;
import com.raghavpro.bookhive.models.User;
import com.raghavpro.bookhive.models.jdbc.DatabaseConnectivity;
import com.raghavpro.bookhive.models.service.CartService;
import com.raghavpro.bookhive.models.service.Service;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Edits the book quantity in a cart.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
@WebServlet(
        name = "EditCartServlet",
        urlPatterns = {"/editcart"}
)
public class EditCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        User user = (User) request.getSession().getAttribute("user");

        Connection conn = DatabaseConnectivity.getConnection();
        Service<CartItem> cartService = new CartService(conn);
        String query = "UPDATE user_cart SET quantity = ? WHERE userid = ? AND isbn = ?";
        Object[] queryParams = new Object[] {
                quantity, user.getUserId(), isbn
        };
        boolean successful = cartService.dataManipulation(query, queryParams);
        cartService.close();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart");
        dispatcher.forward(request, response);
    }
}
