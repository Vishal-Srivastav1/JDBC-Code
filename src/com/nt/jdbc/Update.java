package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			//read input
			sc=new Scanner(System.in);
			int num=0;
			String sname=null,sadd=null;
			float avg=0.0f;
			if(sc!=null) {
				
				System.out.println("ENTER The Sname ");
				sname=sc.nextLine();
				
				System.out.println("ENTER The Address ");
				sadd=sc.nextLine();
				
				System.out.println("ENTER The AVG ");
				avg=sc.nextFloat();
				
				System.out.println("ENTER The Sno ");
				num=sc.nextInt();
				
			}
			
			sname="'"+sname+"'";
			sadd="'"+sadd+"'";
			
			
			//Load the driver class
			//class.forName(oracle.jdbc.OracleDrivers);
			
			//establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			
			//query update student set sname='mukesh',sadd='khalwapatti',avg=98.54 where sno=101;
			String query="update student set sname="+sname+",sadd="+sadd+",avg="+avg+" where sno="+num;
			System.out.println(query);
			
			
			int count=0;
			if(st!=null) 	
				
			     count=st.executeUpdate(query);
			
			   //process the result
				if(count==0)
			   System.out.println("No records found to Updation !..");
			else 
				System.out.println(count+" Record Updated");
			
		
		}//TRY CLOSE
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid col names or tale names or SQL keywords ");
			else if(se.getErrorCode()==12899)
				System.out.println("Do not insert more than xol six=ze data to sname,sadd cols ");
				
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

