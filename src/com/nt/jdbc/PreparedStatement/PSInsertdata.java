package com.nt.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PSInsertdata {
	private static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  //read inputs
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("Enter student count ");
			    count=sc.nextInt();
			}
			
			//load the Driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			
			if(con!=null)
				ps=con.prepareStatement(INSERT_QUERY);
			//read input values from enduser,set them to query param values aand execute the pre-compiled
			//SQL query for multiple times
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i) {
				//read each student input values
					System.out.println("enter "+i+" student details");
					System.out.println();
					System.out.println("enter student number ");
					int no=sc.nextInt();
					
					System.out.println("enter student name ");
					String name=sc.next();
					
					System.out.println("enter student address ");
					String addrs=sc.next();
					
					System.out.println("enter student avg ");
					float avg=sc.nextFloat();
					
					//set each Stuent details as pre-compiled SQL query params
					ps.setInt(1, no);ps.setNString(2, name);ps.setNString(3, addrs);ps.setFloat(4, avg);
					//execute pre-compile sql query each time
					int result=ps.executeUpdate();
					//process execution result of pre-compiled -SQL query
					if(result==0)
						System.out.println(i+" Student details not insert");
					else
						System.out.println(i+" Student details are inserted");
				
				}//for
			}//if
			
		}//try close
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	finally {
		try {
			if(ps!=null)
				ps.close();
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

	