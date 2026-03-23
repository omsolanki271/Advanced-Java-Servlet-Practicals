package com.hirehub.admin;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/updateJob")
public class UpdateJobServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		String title = request.getParameter("title");
		String company = request.getParameter("company");
		String location = request.getParameter("location");
		String salary = request.getParameter("salary");
		String type = request.getParameter("type");

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/jobportal",
					"root",
					"abc123");
		
			String sql = "UPDATE jobs SET title=?, company=?, location=?, salary=?, type=? WHERE id=?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, title);
			ps.setString(2, company);
			ps.setString(3, location);
			ps.setString(4, salary);
			ps.setString(5, type);
			ps.setInt(6, id);

		
			ps.executeUpdate(); 
			
			//response.sendRedirect("ViewJobsServlet");
			request.setAttribute("msg", "job updated successfully....");

			RequestDispatcher rd = request.getRequestDispatcher("ViewJobsServlet");
			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();

				if (conn != null)
					conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}