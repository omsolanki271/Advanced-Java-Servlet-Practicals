package com.aoot.http.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RequestInfo")
public class RequestInfo extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		out.println("<html><body>");
		out.println("<h2>HttpServletRequest Methods</h2>");

		out.println("Context Path (Project name) : " + req.getContextPath() + "<br><br>");
		out.println("Servlet Path (Servlet name) : " + req.getServletPath() + "<br><br>");
		out.println("Request Method : " + req.getMethod() + "<br><br>");
		out.println("Request URL : " + req.getRequestURL() + "<br><br>");
		out.println("Request URI : " + req.getRequestURI() + "<br><br>");
		out.println("Protocol : " + req.getProtocol() + "<br><br>");
		out.println("Server Name : " + req.getServerName() + "<br><br>");
		out.println("Server Port : " + req.getServerPort() + "<br><br>");
		out.println("Client IP : " + req.getRemoteAddr() + "<br><br>");
		out.println("Client Host : " + req.getRemoteHost() + "<br><br>");
		
		out.println("Query String : " + req.getQueryString() + "<br><br>");

		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		doGet(req, res);
	}
}