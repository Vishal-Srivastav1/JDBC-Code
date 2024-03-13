package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MaximumSal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try{
			
			//register jdbc deriver by loading jdbc driver class
			//class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			
		//statement processing
			if(con!=null)
				st=con.createStatement();
			////prepare SQL query
		//creating SQL query SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE SAL=(SELECT MAX(SAL)FROM EMP);
			String query="SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE SAL IN(SELECT MAX(SAL)FROM EMP GROUP BY JOB)";
			System.out.println(query);
		////send and execute SQL query in db s/w	
		if(st!=null)
			rs=st.executeQuery(query);
		
		////process the ResultSet object
		if(rs!=null) {
			while(rs.next()) {	
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getInt(5));
			}//while close
		}//if close
		}//try close
		catch(SQLException se) {
		se.printStackTrace();
		}catch(Exception e) {
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
				
		}//finall close
			
			
		}//main claose
	
	}//class close


