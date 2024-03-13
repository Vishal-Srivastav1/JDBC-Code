package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteRow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			sc=new Scanner(System.in);
			int num=0;
			if(sc!=null) {
				System.out.println("ENTER THE EMPNO ");
				num=sc.nextInt();
			}
			
			//Load the driver class
			//class.forName(jdbc.oracle.DriverManager);
			
			//establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			
			String query="DELETE FROM STUDENT WHERE SNO="+num;
			System.out.println(query);
			
			
			int count=0;
			if(st!=null) 	
				
			     count=st.executeUpdate(query);
			
			   //process the result
				if(count==0)
			   System.out.println("No records found to delete !..");
			else 
				System.out.println(count+" Record deleted");
			
		
		}//TRY CLOSE
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
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}//finally close
			
	}//main cloose

}///class close



