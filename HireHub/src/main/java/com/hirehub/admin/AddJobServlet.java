package com.hirehub.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddJobServlet")
public class AddJobServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String title = req.getParameter("title");
		String company = req.getParameter("company");
		String location = req.getParameter("location");
		String salary = req.getParameter("salary");
		String type = req.getParameter("type");
		String description = req.getParameter("description");

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/jobportal", "root", "abc123");

			String sql = "insert into jobs(title,company,location,salary,type,description) values(?,?,?,?,?,?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, title);
			ps.setString(2, company);
			ps.setString(3, location);
			ps.setString(4, salary);
			ps.setString(5, type);
			ps.setString(6, description);

			int i = ps.executeUpdate();

			if (i > 0) {
				res.sendRedirect("viewJobs.html");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}