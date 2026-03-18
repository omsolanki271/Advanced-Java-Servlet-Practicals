package com.aoot.http.form;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		
		PrintWriter out = response.getWriter();
		String unm = request.getParameter("unm");
		String pw = request.getParameter("pw");
		
		
		boolean found = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/LDRP","root","abc123");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from login");
			while(rs.next())
			{
				if(unm.equals(rs.getString("username")) && pw.equals(rs.getString("pass")))
				{
					found = true;
			        out.println(unm);
			        out.println(pw);
				}
			}
			if(found)
			{
			    out.println("<h2>Login Successful</h2>");
			}
			else
			{
			    out.println("<h2>Invalid Username or Password</h2>");
			}
			rs.close();
			st.close();
			conn.close();
		} 
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
