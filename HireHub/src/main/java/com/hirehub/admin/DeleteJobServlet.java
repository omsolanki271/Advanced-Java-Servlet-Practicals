package com.hirehub.admin;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteJob")
public class DeleteJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteJobServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id1 = request.getParameter("id");

		int id = 0;

		try {
			id = Integer.parseInt(id1);
		} catch (Exception e) {
			response.sendRedirect("viewJobs");
			return;
		}

		Connection conn = null;
		PreparedStatement ps = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/jobportal", "root", "abc123");

			String sql = "DELETE FROM jobs WHERE id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			int result = ps.executeUpdate();

			response.sendRedirect("ViewJobsServlet");

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
