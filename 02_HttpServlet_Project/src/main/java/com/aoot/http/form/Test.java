package com.aoot.http.form;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {

	public static void main(String[] args) {
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/LDRP","root","abc123");
			System.out.println("Connection created.....");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
