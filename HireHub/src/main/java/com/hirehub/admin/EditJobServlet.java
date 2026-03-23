package com.hirehub.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

//@WebServlet("/editJob")
public class EditJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id")) ;
		PrintWriter out = response.getWriter();
		//out.println(id);
		response.setContentType("text/html");
		
		HttpSession session = request.getSession(false); 
		
		if (session == null || session.getAttribute("userEmail") == null) {
			response.sendRedirect("login.html");
			return;
		}
		
		String email = (String) session.getAttribute("userEmail");

		ServletContext context = getServletContext();
		String webname = context.getInitParameter("webname");
		
		ServletConfig config = getServletConfig(); 
		String name = config.getInitParameter("name");

		out.println("<h2>"+webname+"</h2>");
		out.println("<h3>"+name+" Dashboard</h3>");
		out.println("<p>Welcome: " + email + "</p>");
	
		out.println("<a href='logout'>Logout</a>");

		out.println("<hr>");
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/jobportal",
					"root",
					"abc123");
			
			
			String sql = "SELECT * FROM jobs where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs1 = ps.executeQuery();
			while(rs1.next())
			{
				out.println("<h2>Edit Job</h2>");

				out.println("<form action='updateJob' method='post'>");

				out.println("<input type='hidden' name='id' value='" + id + "'>");

				out.println("Title: <input type='text' name='title' value='" + rs1.getString("title") + "'><br>");
				out.println("Company: <input type='text' name='company' value='" + rs1.getString("company") + "'><br>");
				out.println("Location: <input type='text' name='location' value='" + rs1.getString("location") + "'><br>");
				out.println("Salary: <input type='text' name='salary' value='" + rs1.getString("salary") + "'><br>");
				out.println("Type: <input type='text' name='type' value='" + rs1.getString("type") + "'><br>");

				out.println("<button type='submit'>Update</button>");

				out.println("</form>");
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

