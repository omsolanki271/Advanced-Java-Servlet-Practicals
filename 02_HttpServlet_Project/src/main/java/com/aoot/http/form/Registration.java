package com.aoot.http.form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fnm = request.getParameter("fnm");
		String lnm = request.getParameter("lnm");
		String gmail = request.getParameter("mail");
		String pass = request.getParameter("pwd");
		String cpass = request.getParameter("cpwd");
		out.println(fnm + "<br>");
		out.println(lnm + "<br>");
		out.println(gmail + "<br>");
		out.println(pass + "<br>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
