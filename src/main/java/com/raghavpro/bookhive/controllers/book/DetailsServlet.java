package com.raghavpro.bookhive.controllers.book;

import com.raghavpro.bookhive.models.Book;
import com.raghavpro.bookhive.models.jdbc.DatabaseConnectivity;
import com.raghavpro.bookhive.models.service.BookService;

import java.io.File;
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
 * Handles the details page of a book.
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
@WebServlet(
        name = "DetailsServlet",
        urlPatterns = {"/details"}
)
public class DetailsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");

        String query = "SELECT * FROM book WHERE isbn = ?";
        Object[] args = {1}; //Used to set the intialCapacity of ArrayList

        Connection conn = DatabaseConnectivity.getConnection();
        BookService bookService = new BookService(conn);
        List<Book> books = bookService.get(args, query, isbn);
        bookService.close();
        if (books == null || books.size() == 0) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Book book = books.get(0); //There's only one book here, to display details.
        request.setAttribute("book", book);

        int noOfCovers = 0;
        for (int i = 1; i <= 4; i++) {
            File f = new File(getServletContext().getRealPath("/") + "static/images/covers/" + isbn + "-" + i + ".jpg");
            if (f.exists())
                noOfCovers++;
            else
                break;
        }
        request.setAttribute("noOfCovers", noOfCovers);
        request.setAttribute("browser_title", book.getTitle());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/book/details.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
