package com.raghavpro.bookhive.filters;

import com.raghavpro.bookhive.models.User;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

/**
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
@WebFilter(
        urlPatterns = {
                "/addcart",
                "/cart",
                "/removecart",
                "/editcart",
                "/addreview",
                "/logout" //incase a user tries to do"/logout" when he's not even logged in.
        }
)
public class UserLoginFilter extends LoginFilter {


    @Override
    protected boolean isAuth(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return user != null;
    }

    @Override
    public void destroy() {
        System.out.println("Servlet destroyed");
    }
}
