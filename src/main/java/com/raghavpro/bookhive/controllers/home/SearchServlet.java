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
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by RaghavFTW on 29/04/15.
 */
@WebServlet(
        name = "SearchServlet",
        urlPatterns = {"/search"},
        initParams = {@WebInitParam(name = "sortBy", value = Constants.RELEVANCE)}
)
public class SearchServlet extends HttpServlet {

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

        Service<Category> categoryService = new CategoryService(conn);
        List<Category> categories = categoryService.get(new Object[]{categoryId}, null);

        String search = request.getParameter("search");
        if (search != null) {
            String query_count;
            Object[] countParams;
            if (categoryId == 0) {  // ffs I hate all this.
                query_count = "SELECT COUNT(*) AS count FROM book WHERE to_tsvector(title || ' ' || author) @@ to_tsquery(?)";
                countParams = new Object[]{search};
            } else {
                query_count = "SELECT COUNT(*) AS count FROM " +
                        "(SELECT * FROM book WHERE isbn IN " +
                        "(SELECT isbn FROM book_category WHERE categoryid IN " +
                        "(SELECT categoryid FROM category WHERE parent = ? OR categoryid = ?))) " +
                        "AS result WHERE to_tsvector(result.title || ' ' || result.author) @@ to_tsquery(?)";
                countParams = new Object[]{
                        categoryId, categoryId, search
                };
            }

            Service<Book> bookService = new BookService(conn);
            Service<Integer> countService = new CountService(conn);

            int count = countService.get(null, query_count, countParams).get(0);

            if (count > 0) {
                String bookQuery = getQuery(sortBy, categoryId);
                Object[] queryParams;
                if (categoryId == 0) {
                    queryParams = new Object[]{
                            search, Constants.RESULTS_PER_PAGE, (page - 1) * Constants.RESULTS_PER_PAGE
                    };
                } else {
                    queryParams = new Object[]{
                            categoryId, categoryId, search, Constants.RESULTS_PER_PAGE, (page - 1) * Constants.RESULTS_PER_PAGE
                    };
                }

                List<Book> books = bookService.get(null, bookQuery, queryParams);

                Pagination pagination = new Pagination(count, page);
                request.setAttribute("books", books);
                request.setAttribute("pagination", pagination);
            }
            bookService.close(); //Since we're passing the same object, only calling one would close it.
        }

        request.setAttribute("page", page);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("categories", categories);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("search", search);
        request.setAttribute("browser_title", "\"" + search + "\"");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/home/search.jsp");
        dispatcher.forward(request, response);
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
            query.append("SELECT * FROM book WHERE to_tsvector(title || ' ' || author) @@ to_tsquery(?)");
        } else {
            query.append("SELECT * FROM " +
                    "(SELECT * FROM book WHERE isbn IN " +
                    "(SELECT isbn FROM book_category WHERE categoryid IN " +
                    "(SELECT categoryid FROM category WHERE parent = ? OR categoryid = ?))) " +
                    "AS result WHERE to_tsvector(result.title || ' ' || result.author) @@ to_tsquery(?)");
        }
        switch (sortBy) {
            case Constants.BESTSELLERS:
                query.append(" ORDER BY rank");
                break;
            case Constants.HIGH_TO_LOW:
                query.append(" ORDER BY price DESC");
                break;
            case Constants.LOW_TO_HIGH:
                query.append(" ORDER BY price");
                break;
            case Constants.NEW:
                query.append(" ORDER BY rank DESC");
                break;
        }
        query.append(" LIMIT ? OFFSET ?;");
        return query.toString();
    }
}
