package com.hirehub.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet("/AdminDashboard")
public class AdminDashboardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		HttpSession session = req.getSession(false);


		if (session == null || session.getAttribute("userEmail") == null) {
			res.sendRedirect("login.html");
			return;
		}
		
		String email = (String) session.getAttribute("userEmail");
		String sessionId = session.getId();

		ServletContext context = getServletContext();
		String webname = context.getInitParameter("webname");
		
		ServletConfig config = getServletConfig(); 
		String name = config.getInitParameter("name");
		
		out.println("<html><body>");

		out.println("<h2>"+webname+"</h2><p>ServletContext</p>");
		out.println("<h3>"+name+" Dashboard</h3><p>ServletConfig</p>");
		out.println("<p>Welcome: " + email + "</p>");
		out.println("<p>Session ID: " + sessionId + "</p>");
		out.println("<a href='logout'>Logout</a>");

		out.println("<hr>");

		out.println("<a href='AdminDashboard'>Dashboard</a><br><br>");
		out.println("<a href='addJob.html'>Add Job</a><br><br>");
		out.println("<a href='ViewJobsServlet'>Manage Jobs</a><br><br>");
		

		out.println("</body></html>");
	}
}