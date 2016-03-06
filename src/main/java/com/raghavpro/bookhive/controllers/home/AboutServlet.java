package com.raghavpro.bookhive.controllers.home;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by RaghavFTW on 28/04/15.
 */
@WebServlet(name = "AboutServlet", urlPatterns = {"/about", "/aboutus"})
public class AboutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("browser_title", "About us");
    }
}
