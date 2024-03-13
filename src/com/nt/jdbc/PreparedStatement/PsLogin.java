package com.nt.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLogin {
      private static final String query="select count(*) from student where SNO=? and SNAME=?"; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 Scanner sc=null;
		 Connection con=null;
		 PreparedStatement ps=null;
		 ResultSet rs=null;
		try {
			//read input
			sc= new Scanner(System.in);
			String sname=null;
			int num=0;
			if(sc!=null) {
				System.out.println("Enter the Sno ");
			    num=sc.nextInt();
			    
			    System.out.println("Enter the Sname ");
			    sname=sc.next();
			    
			}//if close
			//load the jdbc driver class(Optional)
			//class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			//create jdbc PrepareStatement Object
			 if(con!=null)
				 System.out.println(query+"...-->prof query compile with param");
				 ps=con.prepareStatement(query);
			 //set values to the param of ore-compiled SQL query
			 if(ps!=null && sc!=null) {
				 ps.setInt(1,num);
			     ps.setString(2,sname);
			 }  
			  //send and executed the SQL query in db  
			 if(ps!=null)
				 rs=ps.executeQuery();
			 
			 //process the resultSet object
			   if(rs!=null) {
				   rs.next();//movies the cursor to first from BFR
			   
                 
             int count=rs.getInt(1);//get first col value of that first record
             
             //process the result
              if(count==0)
            	  System.out.println("Invalid...");
              else
            	  System.out.println("valid ...");
			 
			   }//if close
		
		}//try close
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
       
		finally {
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(ps!=null)
					ps.close();
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
	}//main close

}//class close
