package com.hirehub.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//@WebServlet("/ViewJobsServlet")
public class ViewJobsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false); 
		
		if (session == null || session.getAttribute("userEmail") == null) {
			response.sendRedirect("login.html");
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
		
		Connection conn = null;
		Statement st = null;        
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/jobportal",
					"root",
					"abc123");
			
			st = conn.createStatement();
			
			rs = st.executeQuery("SELECT * FROM jobs");
			
			out.println("<a href='AdminDashboard'>Go to Dashboard</a>");
			String msg = (String) request.getAttribute("msg");

			if(msg != null){
			    out.println("<p style='color:green;'>" + msg + "</p>");
			}
			out.println("<h2 style='display:flex;justify-content:center;'>Job List</h2>");
			out.println("<table border='1'>");
			out.println("<tr>"
						+ "<th>ID</th>"
						+ "<th>Title</th>"
						+ "<th>Company</th>"
						+ "<th>Location</th>"
						+ "<th>Salary</th>"
						+ "<th>Type</th>" 
						+"<th>Action</th>"
					+ "</tr>");

			int c = 1;
			while (rs.next()) {   

				out.println("<tr>");
				
				int id = rs.getInt("id");
				
				out.println("<td>" + c++ + "</td>");
				out.println("<td>" + rs.getString("title") + "</td>");
				out.println("<td>" + rs.getString("company") + "</td>");
				out.println("<td>" + rs.getString("location") + "</td>");
				out.println("<td>" + rs.getString("salary") + "</td>");
				out.println("<td>" + rs.getString("type") + "</td>");
				out.println("<td>");
				out.println("<a href='editJob?id=" + id + "'>Edit</a>  | ");
				out.println("<a href='deleteJob?id=" + id + "' onclick=\"return confirm('Are you sure?')\">Delete</a>");
				out.println("</td>");
				out.println("</tr>");
			}

			out.println("</table>");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (st != null)
					st.close();

				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		out.println("<style>");
		out.println("body { font-family: Arial; }");
		out.println("table { border-collapse: collapse; width: 70%; margin: auto; }");
		out.println("th, td { border: 1px solid black; padding: 8px; text-align: center; }");
		out.println("th { background-color: lightgray; }");
		out.println("</style>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    doGet(request, response);
	}
}