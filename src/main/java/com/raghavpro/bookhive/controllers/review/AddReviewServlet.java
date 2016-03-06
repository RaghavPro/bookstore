package com.raghavpro.bookhive.controllers.review;

import com.raghavpro.bookhive.models.Review;
import com.raghavpro.bookhive.models.User;
import com.raghavpro.bookhive.models.jdbc.DatabaseConnectivity;
import com.raghavpro.bookhive.models.service.ReviewService;
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
 * Handles adding of a review to the book.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
@WebServlet(
        name = "AddReviewServlet",
        urlPatterns = {"/addreview"}
)
public class AddReviewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        int stars = Integer.parseInt(request.getParameter("stars"));
        String title = request.getParameter("title");
        String review = request.getParameter("review");
        User user = (User) request.getSession(false).getAttribute("user");

        String query = "INSERT INTO review (isbn, reviewer, stars, title, review, review_date) VALUES(?, ?, ?, ?, ?, now())";
        Object[] queryParams = {
                isbn, user.getFullName(), stars, title, review
        };

        Connection conn = DatabaseConnectivity.getConnection();
        Service<Review> reviewService = new ReviewService(conn);
        boolean successful = reviewService.dataManipulation(query, queryParams);
        reviewService.close();
        if (!successful) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/details?isbn=" + isbn);
        dispatcher.forward(request, response);
    }
}
