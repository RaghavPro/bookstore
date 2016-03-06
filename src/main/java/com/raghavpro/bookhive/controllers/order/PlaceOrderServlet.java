package com.raghavpro.bookhive.controllers.order;

import com.raghavpro.bookhive.models.CartItem;
import com.raghavpro.bookhive.models.Order;
import com.raghavpro.bookhive.models.User;
import com.raghavpro.bookhive.models.jdbc.DatabaseConnectivity;
import com.raghavpro.bookhive.models.service.OrderService;
import com.raghavpro.bookhive.models.service.Service;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Places an order.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
@WebServlet(name = "PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /* Two parts. First add items in database. Second retrieve it and send it to jsp */


        List<CartItem> cartItems = (List<CartItem>) request.getSession().getAttribute("cartItems");
        User user = (User) request.getSession().getAttribute("user");
        String query = "INSERT INTO orders(isbn, userid, quantity) VALUES(?, ?, ?)";


        Connection conn = DatabaseConnectivity.getConnection();
        Service<Order> order = new OrderService(conn);
        List<Boolean> successful = new ArrayList<>(cartItems.size());
        for (CartItem cartItem : cartItems)
            successful.add(order.dataManipulation(query, cartItem.getBook().getIsbn(),
                                                        user.getUserId(), cartItem.getQuantity()));

        query = "SELECT * FROM orders where userid = ?";
        order.get(new Object[]{cartItems.size()}, query, user.getUserId());
    }
}
