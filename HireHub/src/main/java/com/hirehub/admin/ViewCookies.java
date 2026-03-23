package com.hirehub.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/ViewCookies")
public class ViewCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewCookies() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ServletConfig config = getServletConfig();
        String cname = config.getInitParameter("name");
        
        out.println("<h2>"+cname+"</h2>");

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                out.println("<p>" + c.getName() + " : " + c.getValue() + "</p>");
            }
        } else {
            out.println("<p>No cookies found</p>");
        }
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
