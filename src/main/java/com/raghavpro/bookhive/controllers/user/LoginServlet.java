package com.raghavpro.bookhive.controllers.user;

import com.raghavpro.bookhive.models.User;
import com.raghavpro.bookhive.models.jdbc.DatabaseConnectivity;
import com.raghavpro.bookhive.models.service.CountService;
import com.raghavpro.bookhive.models.service.Service;
import com.raghavpro.bookhive.models.service.UserService;
import org.jasypt.util.password.StrongPasswordEncryptor;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/login"}
)
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/user/login.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String query = "SELECT * FROM public.user WHERE LOWER(username) = LOWER(?)";

        Connection conn = DatabaseConnectivity.getConnection();
        Service<User> userService = new UserService(conn);
        List<User> users = userService.get(null, query, username);
        User user = null;
        if (users != null && users.size() > 0)
            user = users.get(0);

        if (user == null) { //Does not exist
            request.setAttribute("not_found", true);
            doGet(request, response);
            return;
        }

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        boolean authenticate = passwordEncryptor.checkPassword(password, user.getPassword());
        if (!authenticate) { //Wrong password
            request.setAttribute("wrong_pass", true);
            request.setAttribute("username", username); //Set value of textbox to username since pass is wrong, so retry.
            request.setAttribute("browser_title", "Login");
            doGet(request, response);
            return;
        }
        query = "SELECT COUNT(*) FROM user_cart WHERE userid = ?";
        Service<Integer> countService = new CountService(conn);
        List<Integer> countList = countService.get(null, query, user.getUserId());
        userService.close();
        int count = 0;
        if (countList != null)
            count = countList.get(0);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        if (count > 0)
            session.setAttribute("totalItems", count);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books");
        dispatcher.forward(request, response);
    }
}
