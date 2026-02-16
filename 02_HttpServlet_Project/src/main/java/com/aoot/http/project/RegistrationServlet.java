package com.aoot.http.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Use doGet to handle form submission
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Start HTML
        out.println("<html><head><title>Registration Success</title></head><body>");
        out.println("<h2>Registration Successful!</h2>");

        // Start normal HTML table
        out.println("<table border='1' cellpadding='5' cellspacing='0'>");
        out.println("<tr><th>Field</th><th>Value</th></tr>");

        // Loop through all parameters
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);

            // Hide password values
            if (paramName.equalsIgnoreCase("password") || paramName.equalsIgnoreCase("confirmPassword")) {
                paramValue = "********";
            }

            out.println("<tr>");
            out.println("<td>" + paramName + "</td>");
            out.println("<td>" + paramValue + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<br><a href='registration.html'>Back to Registration</a>");
        out.println("</body></html>");
    }

    // Redirect POST requests to GET (so form can use GET or POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
