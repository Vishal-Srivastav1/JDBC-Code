package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_checking {

	public static void main(String[] args) {
		
		try (Connection con=DriverManager.getConnection("jdbc:mysql:///vishal123","root","#vmk5657")){
		
			if (con==null) {
				System.out.println("Connection not Establish");
			}else {
				System.out.println("connection  Establish");
			}
		//    
		//	(HOST=127.0.0.1)
		} 
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
