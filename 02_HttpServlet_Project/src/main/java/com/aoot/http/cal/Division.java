package com.aoot.http.cal;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Division")
public class Division extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int num1 = Integer.parseInt(request.getParameter("no1"));
		int num2 = Integer.parseInt(request.getParameter("no2"));

		ServletContext context = getServletContext();
		String uni = context.getInitParameter("university");

		ServletConfig config = getServletConfig();
		String pName = config.getInitParameter("dpage");

		String fresult;
		if(num1!=0 || num2!=0)
		{
			int result = num1 / num2;
			fresult =String.valueOf(result);
		}
		else
		{
			fresult = "Not Division";
		}

		out.println("<html><body>");
		out.println("<h1>Univercity : " + uni + "</h1>");
		out.println("<h2>Addition Result : " + fresult + "</h2>");
		out.println("<h3>Program : " + pName + "</h3>");

		out.println("<a href='cal.html'>Back</a>");
		out.println("</body></html>");

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
