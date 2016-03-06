package com.raghavpro.bookhive.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A login filter to check authentication of a user
 *
 * @author Raghav (Email: Raghav.Sharma@Outlook.in)
 */
public abstract class LoginFilter implements javax.servlet.Filter {

    protected ServletContext servletContext;

    public void init(FilterConfig filterConfig) {
        servletContext = filterConfig.getServletContext();
    }

    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        if (!isAuth(session)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/view/user/login.jsp");
            requestDispatcher.forward(request, response);
            return; //break filter chain, requested JSP/servlet will not be executed
        }

        chain.doFilter(request, response);
    }

    /**
     * Logic to accept or reject access to the page, check log in status
     *
     * @return {@code true} when authentication is deemed valid
     */
    protected abstract boolean isAuth(HttpSession session);

}