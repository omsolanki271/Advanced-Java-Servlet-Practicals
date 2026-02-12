package com.aoot.generic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;


//@WebServlet("/Program3")
public class Program3 extends GenericServlet {
	private static final long serialVersionUID = 1L;
       

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		 
        PrintWriter out = response.getWriter();
        out.println("<h1>GenericServlet Program 3</h1>");
        out.println("<p>destroy() method will run on server stop</p>");
	}
	
	public void destroy() {
		System.out.println("Program3 servlet destroyed");
	}
}
