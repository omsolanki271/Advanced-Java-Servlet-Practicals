package com.hirehub.user;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.print.attribute.standard.PresentationDirection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/jobportal", "root", "abc123");
			String query = "insert into users(fullname,email,pass,mobile,address) values(?,?,?,?,?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, fullname);
			ps.setString(2, email);
			ps.setString(3, pass);
			ps.setString(4, mobile);
			ps.setString(5, address);

			int result = ps.executeUpdate();
			if (result > 0) {
				/*
				 * RequestDispatcher rd = request.getRequestDispatcher("/login.html");
				 * 
				 * rd.forward(request, response);
				 */
				response.sendRedirect("login.html");

			} else {

				out.println("<h3> Registration Failed</h3>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null)
				{
					ps.close();
				}
				if(conn != null)
				{
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

};