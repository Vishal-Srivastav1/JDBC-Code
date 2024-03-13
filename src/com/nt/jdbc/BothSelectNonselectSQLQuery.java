package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BothSelectNonselectSQLQuery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			 sc= new Scanner(System.in);
			 String query=null;
			// int no=0;
			 if(sc!=null) {
				 System.out.println("Enter the SQL query(Select or non-select::");
				 query=sc.nextLine();
			 }//read inputs
			 
			 //Load JDBC driver class
			 //class.forName("oracle.jdbc.driver.OracleDriver");
			 //gather the results from Db s/w and use them
			 
			 //establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			 
			
			//create  JDbC Statements obj
			if(con!=null)
				st=con.createStatement();
				
			//send and execute SQl query in Db
			
			if(st!=null) {
				boolean flag=st.execute(query);
				System.out.println(flag);
				if(flag==true) {
				System.out.println("Select SQL Query executed");
				//process the ResultSet
				rs=st.getResultSet();
				 //process the resultSet obj
				if(rs!=null) {
					while(rs.next()) {
						System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
					}//while close		
				}//if close
					
			}//if
			else {
				System.out.println("Non-Select SQL Query executed");
				int count=st.getUpdateCount();  //long count=st.getLargeUpdateCount(); for lakh of records then we write this
			 System.out.println("no.of records that are effected::"+count);
			}//else
			}//if
	}//try
		
	catch(SQLException se) {
		se.printStackTrace();
		//we can work with error codes
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		//close jdbc obj
		try {
			if(rs!=null)
				rs.close();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		
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
