package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SalaryGroup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		try {
			//read input
			sc=new Scanner(System.in);
			float hike_percentage=0.0f;
			String desg1=null,desg2=null,desg3=null;
			if(sc!=null) {
				
				System.out.println("employee JOB_desg1  ");
				desg1=sc.next();////gives manager
				
				System.out.println("employee JOB_desg2  ");
				desg2=sc.next();////gives cleark
				
				System.out.println("employee JOB_desg3  ");
				desg3=sc.next();//gives anaylst
				
				System.out.println("Salary hike_percentage ");
				hike_percentage=sc.nextFloat();
				
			}
			//convert input values as required for the SQL query
			desg1="'"+desg1+"'";//gives 'manager'
			desg2="'"+desg2+"'";//gives 'cleark'
			desg3="'"+desg3+"'";//gives 'analyst'
			
			
			//Load the driver class
			//class.forName(oracle.jdbc.OracleDrivers);
			
			//establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			
			//create Statement object
			if(con!=null)
				st=con.createStatement();
			
			//prepare SQL query
			//update emp set sal=sal+(sal*10/100)where job in('cleark','manager','anaylst')
			String query="update emp set sal=sal+(sal*"+hike_percentage/100+") where job in("+desg1+","+desg2+","+desg3+")";          
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


