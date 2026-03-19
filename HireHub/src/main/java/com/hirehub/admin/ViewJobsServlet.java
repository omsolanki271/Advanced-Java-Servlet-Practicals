package com.hirehub.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/viewJobs")
public class ViewJobsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
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
			
			out.println("<a href=\"Adashboard.html\">Go to Dashboard</a>");
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

			
			while (rs.next()) {   

				out.println("<tr>");
				
				int id = rs.getInt("id");
				
				out.println("<td>" + rs.getInt("id") + "</td>");
				out.println("<td>" + rs.getString("title") + "</td>");
				out.println("<td>" + rs.getString("company") + "</td>");
				out.println("<td>" + rs.getString("location") + "</td>");
				out.println("<td>" + rs.getString("salary") + "</td>");
				out.println("<td>" + rs.getString("type") + "</td>");
				out.println("<td>");
				out.println("<a href='editJob?id=" + id + "'>Edit</a>  | ");
				out.println("<a href='deleteJob?id=" + id + "'>Delete</a>");
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
}