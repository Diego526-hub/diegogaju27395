package com.auca.registration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String query = request.getParameter("query");
        String googleUrl = "https://www.google.com/search?q=" + query;
        
        response.sendRedirect(googleUrl);
    }
}