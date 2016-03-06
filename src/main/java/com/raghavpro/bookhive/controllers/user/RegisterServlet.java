package com.raghavpro.bookhive.controllers.user;

import com.raghavpro.bookhive.models.User;
import com.raghavpro.bookhive.models.jdbc.DatabaseConnectivity;
import com.raghavpro.bookhive.models.service.CountService;
import com.raghavpro.bookhive.models.service.Service;
import com.raghavpro.bookhive.models.service.UserService;
import org.jasypt.util.password.StrongPasswordEncryptor;

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
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
@WebServlet(
        name = "RegisterServlet",
        urlPatterns = {"/register"}
)
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/user/register.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("inputFirstName");
        String lastName = request.getParameter("inputLastName");
        String userName = request.getParameter("inputUsername");
        String password = request.getParameter("inputPassword");
        String email = request.getParameter("inputEmail");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");

        /* Encrypts password */
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        password = passwordEncryptor.encryptPassword(password);

        /* Let's first check if there's not already a user by this username */
        String query = "SELECT COUNT(*) as count FROM public.user WHERE username = ?";

        Connection conn = DatabaseConnectivity.getConnection();
        Service<Integer> countService = new CountService(conn);
        List<Integer> countList = countService.get(null, query, userName);
        int count = 0;
        if (countList != null && countList.size() > 0)
            count = countList.get(0);
        if (count == 0) { //Result of COUNT(*). If zero means no account. Otherwise already exists.
            query = "INSERT INTO public.user (firstname, lastname, username, password, email, address, gender, date)  " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, now())";
            Object[] queryParams = {
                    firstName, lastName, userName, password, email, address, gender
            };
            Service<User> userService = new UserService(conn);
            boolean successful = userService.dataManipulation(query, queryParams);

            if (successful) {
                request.setAttribute("created", true);
            } else {
                request.setAttribute("created", false);
            }
        } else {
            request.setAttribute("already_exist", true);
        }
        request.setAttribute("browser_title", "Register");
        countService.close(); //We're passing the same object so calling only once will close it.

        doGet(request, response);
    }
}
