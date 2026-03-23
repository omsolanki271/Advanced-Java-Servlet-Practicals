package com.hirehub.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/companies")
public class CompanyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/jobportal",
					"root",
					"abc123");
			CallableStatement cs = conn.prepareCall("{call getAllCompanies()}");

			ResultSet rs = cs.executeQuery();
			ServletConfig config = getServletConfig();
	        String cname = config.getInitParameter("name");
	        
	        out.println("<h2>"+cname+"</h2>");
			
	        out.println("<table border='1'>");

			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getInt("id") + "</td>");
				out.println("<td>" + rs.getString("name") + "</td>");
				out.println("</tr>");
			}

			out.println("</table>");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}