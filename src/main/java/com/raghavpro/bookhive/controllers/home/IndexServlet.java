package com.raghavpro.bookhive.controllers.home;

import com.raghavpro.bookhive.Constants;
import com.raghavpro.bookhive.models.Book;
import com.raghavpro.bookhive.models.Category;
import com.raghavpro.bookhive.models.Pagination;
import com.raghavpro.bookhive.models.jdbc.DatabaseConnectivity;
import com.raghavpro.bookhive.models.service.BookService;
import com.raghavpro.bookhive.models.service.CategoryService;
import com.raghavpro.bookhive.models.service.CountService;
import com.raghavpro.bookhive.models.service.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Index servlet.
 * Get book results based on the sort selected, handles pagination and dispatches to index.jsp
 * <p>
 * Raghav (Email: Raghav.Sharma@Outlook.in)
 */
@WebServlet(
        name = "IndexServlet",
        urlPatterns = {"/books", "/index"},
        initParams = {@WebInitParam(name = "sortBy", value = Constants.BESTSELLERS)}
)
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        long categoryId = 0;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            categoryId = Long.parseLong(request.getParameter("category"));
        } catch (NumberFormatException e) {
        }

        String sortBy = request.getParameter("sortBy");
        if (sortBy == null || sortBy.isEmpty())
            sortBy = this.getInitParameter("sortBy");

        Connection conn = DatabaseConnectivity.getConnection();

        String bookQuery = getQuery(sortBy, categoryId);
        Object[] queryParams, countQueryParams = null;
        String countQuery = "SELECT COUNT(*) AS count FROM book";
        if (categoryId == 0) {
            queryParams = new Object[] {
                    Constants.RESULTS_PER_PAGE, (page - 1) * Constants.RESULTS_PER_PAGE
            };
        } else { // If viewing a particular category send different parameters. @see getQuery(sortBy, categoryId)
            queryParams = new Object[] {
                    categoryId, categoryId,  Constants.RESULTS_PER_PAGE, (page - 1) * Constants.RESULTS_PER_PAGE
            };
            countQueryParams = new Object[] {
                    categoryId, categoryId
            };
            countQuery += " WHERE isbn IN (SELECT isbn FROM book_category WHERE categoryid in " +
                    "(SELECT categoryid FROM category where parent = ? or categoryid = ?))";
        }
        Service<Book> bookService = new BookService(conn);
        List<Book> books = bookService.get(null, bookQuery, queryParams);


        Service<Category> categoryService = new CategoryService(conn);
        List<Category> categories = categoryService.get(new Object[] {categoryId}, null);


        Service<Integer> countService = new CountService(conn);
        int count = countService.get(null, countQuery, countQueryParams).get(0);


        Pagination pagination = new Pagination(count, page);

        request.setAttribute("page", page);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("books", books);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("categories", categories);
        request.setAttribute("pagination", pagination);
        request.setAttribute("browser_title", "Welcome");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/home/index.jsp");
        dispatcher.forward(request, response);

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * Selects the query on the basis of sortBy
     *
     * @param sortBy The sort used has selected
     * @return {@code String} query to execute on the database
     */
    private String getQuery(String sortBy, long categoryId) {
        StringBuilder query = new StringBuilder();
        if (categoryId == 0) {
            query.append("SELECT * FROM book");
        } else {
            query.append("SELECT * FROM book where isbn in " +
                    "(SELECT isbn FROM book_category where categoryid in " +
                    "(SELECT categoryid FROM category where parent = ? or categoryid = ?))");
        }
        switch (sortBy) {
            case Constants.LOW_TO_HIGH:
                query.append(" ORDER BY price");
                break;
            case Constants.HIGH_TO_LOW:
                query.append(" ORDER BY price DESC");
                break;
            case Constants.NEW:
                query.append(" ORDER BY rank");
                break;
            default: //Sort by popular/rank
                query.append(" ORDER BY rank");
                break;
        }
        query.append(" LIMIT ? OFFSET ?");
        return query.toString();
    }
}
