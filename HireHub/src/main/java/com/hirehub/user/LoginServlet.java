package com.hirehub.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		PrintWriter out = res.getWriter();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/jobportal", "root", "abc123");

			String sql = "select * from users where email =? and pass=?";

			ps = conn.prepareStatement(sql);

			ps.setString(1, email);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {

				HttpSession session = req.getSession();
				session.setAttribute("userEmail", email);
				session.setMaxInactiveInterval(1 * 60);

				Cookie cookie = new Cookie("userEmail", email);
				cookie.setMaxAge(1 * 60);  // 1 
				res.addCookie(cookie);

				res.sendRedirect("AdminDashboard");
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("login.html");
				out = res.getWriter();

				out.println("<script>");
				out.println("document.addEventListener('DOMContentLoaded', function(){");
				out.println("document.getElementById('errorMsg').innerText='Invalid Email or Password';");
				out.println("});");
				out.println("</script>");

				rd.include(req, res);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
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
