package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DropTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
			Scanner sc=null;
			Connection con=null;
			Statement st=null;
			
			try {
				sc= new Scanner(System.in);
				String name=null;
				if(sc!=null) {
					System.out.println("Enter table Name for DROP ");
					name=sc.nextLine();
				}
	    //Load the Driver class
				//class.forName(Oracle.jdbc.OracleDriver);
				
				//establish the Connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
				
				if(con!=null)
					st=con.createStatement();
				
				//prepare the SQL queery
				//drop table vishal;
				String query="drop table "+name;
				  System.out.println(query);
				  
				  int count=0;
				  if(st!=null)
					count=st.executeUpdate(query);
				  if(count==0) {// here count ==1 due to driver thin or db issesu
					  System.out.println("Table Droped");//we get this output
				  }
				  else {
					  System.out.println("Table not Drop");
				  }
					  
			}//try close
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
		finally {
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se){
				se.printStackTrace();	
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();	
			}
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e){
				e.printStackTrace();	
			}
			
			
			}//finall close
		}//main close

	}//class close

		
