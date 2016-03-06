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
 * Removes an item from cart.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
@WebServlet(
        name = "RemoveCartServlet",
        urlPatterns = "/removecart"
)
public class RemoveCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        User user = (User) request.getSession(false).getAttribute("user");

        Connection conn = DatabaseConnectivity.getConnection();
        Service<CartItem> cartService = new CartService(conn);
        String query = "DELETE FROM user_cart WHERE isbn = ? AND userid = ?";
        Object[] queryParams = {
            isbn, user.getUserId()
        };
        boolean successful = cartService.dataManipulation(query, queryParams);
        cartService.close();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart");
        dispatcher.forward(request, response);
    }
}
