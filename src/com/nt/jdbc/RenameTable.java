package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RenameTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			sc= new Scanner(System.in);
			String name=null,name1=null;
			if(sc!=null) {
				System.out.println("Enter OLD table Name ");
				name=sc.nextLine();
				System.out.println("Enter NEW table Name ");
				name1=sc.nextLine();
			}
    //Load the Driver class
			//class.forName(Oracle.jdbc.OracleDriver);
			
			//establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			
			if(con!=null)
				st=con.createStatement();
			
			//prepare the SQL query
			//rename student to vishal;
			String query="RENAME "+name+" TO "+name1;
			  System.out.println(" "+query);
			  System.out.println();
			  
			  int count=0;
			  if(st!=null)
				count=st.executeUpdate(query);
			  if(count==0) {
				  System.out.println("Table changed "+name+" TO "+name1);
			  }
			  else {
				  System.out.println("Table not changed");
			  }
				  
		}//try close
		catch(SQLException se) {
			
			if(se.getErrorCode()==900)
				System.out.println(" DB Table is not there ");
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

