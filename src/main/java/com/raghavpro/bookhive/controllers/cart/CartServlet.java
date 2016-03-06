package com.raghavpro.bookhive.controllers.cart;

import com.raghavpro.bookhive.models.CartItem;
import com.raghavpro.bookhive.models.User;
import com.raghavpro.bookhive.models.jdbc.DatabaseConnectivity;
import com.raghavpro.bookhive.models.service.CartService;
import com.raghavpro.bookhive.models.service.Service;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Displays the cart items of a user.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
@WebServlet(
        name = "CartServlet",
        urlPatterns = {"/cart"}
)
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("user");

        String query = "SELECT quantity from user_cart where userid = ?"; //Rest gets handled in method

        Connection conn = DatabaseConnectivity.getConnection();
        Service<CartItem> cartService = new CartService(conn);
        String bookQuery = "SELECT * FROM book where isbn in (SELECT isbn from user_cart where userid = ?)";
        Object[] arguments = {bookQuery};
        List<CartItem> cartItems = cartService.get(arguments, query, user.getUserId());
        cartService.close();

        int totalPrice = 0;
        if (cartItems != null) {
            for (CartItem cartItem : cartItems)
                totalPrice += cartItem.getBook().getPrice() * cartItem.getQuantity();
            request.getSession().setAttribute("cartItems", cartItems);
            request.getSession().setAttribute("totalItems", cartItems.size());
        } else {
            request.getSession().setAttribute("totalItems", 0);
            request.getSession().setAttribute("cartItems", null);
        }
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("browser_title", user.getFirstName() + "'s cart");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cart/cart.jsp");
        dispatcher.forward(request, response);
    }
}
