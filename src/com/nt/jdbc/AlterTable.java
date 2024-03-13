package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AlterTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		
		try {
			sc= new Scanner(System.in);
			String name=null,name1=null,name2=null;
			if(sc!=null) {
				System.out.println("Enter table ");
				name=sc.nextLine();
				          //rename column name
				System.out.println("Enter table OLD_Column Name ");
				name1=sc.nextLine();
				         //rename column name
				System.out.println("Enter table NEW_Column Name ");
				name2=sc.nextLine();
			}
    //Load the Driver class
			//class.forName(Oracle.jdbc.OracleDriver);
			
			//establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			
			if(con!=null)
				st=con.createStatement();
			
			//prepare the SQL query
			//ALTER TABLE STUDENT RENAME COLUMN SNAME TO STUDENTNAME;
			//
			
			String query="ALTER TABLE "+name+" RENAME COLUMN "+name1+" TO "+name2;
			  System.out.println(query);
			  
			  int count=0;
			  if(st!=null)
				count=st.executeUpdate(query);
			  if(count==0) {
				  System.out.println("Table altered... ");
			  }
			  else {
				  System.out.println("Table not altered...");
			  }
				  
		}//try close
		catch(SQLException se) {
			
			if(se.getErrorCode()==942)
				System.out.println("  table or view does not exist ");
			if(se.getErrorCode()==904)
				System.out.println("  invalid indifier.. ");
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