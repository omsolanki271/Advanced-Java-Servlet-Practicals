package com.aoot.http.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextParamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = getServletContext();
        String university = context.getInitParameter("university");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h1>ServletContext Example</h1>");
        out.println("<p>University: " + university + "</p>");
    }
}
