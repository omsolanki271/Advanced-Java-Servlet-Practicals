package com.aoot.http.cal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/Addition")
public class Addition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Addition() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int num1 = Integer.parseInt(request.getParameter("no1"));
		int num2 = Integer.parseInt(request.getParameter("no2"));
		int result = num1 + num2;
		
		 ServletConfig config = getServletConfig();
	     String pName = config.getInitParameter("apage");

	     ServletContext context = getServletContext();
	     String uni = context.getInitParameter("university");

	     
	     out.println("<html><body>");
	     out.println("<h1>Univercity : "+uni+"</h1>");
	     out.println("<h2>Addition Result : " + result + "</h2>");
	     out.println("<h3>Program : " + pName + "</h3>");
	     
	     out.println("<a href='cal.html'>Back</a>");
	     out.println("</body></html>");
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
