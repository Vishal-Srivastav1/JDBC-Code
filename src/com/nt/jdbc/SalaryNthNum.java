package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SalaryNthNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
		   sc=new Scanner(System.in);
		   int num=0;
		  if(sc!=null) {
			  System.out.println("ENTER the Positions. of SALARY ");
			    num=sc.nextInt();
		  }
		  
		//register jdbc deriver by loading jdbc driver class
			//class.forName("oracle.jdbc.driver.OracleDriver");
		  
			//establish the Connection
			con=DriverManager.getConnection("jdbc:Oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			
			//create the statement
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query
			// select * from(select Ename,sal,dense_rank() over(order by sal desc) r from emp) where r=&n;
		String query="SELECT * FROM(SELECT EMPNO,ENAME,JOB,SAL,DENSE_RANK() OVER(ORDER BY SAL DESC) R FROM EMP) WHERE R="+num;
		////send and execute SQL query in db s/w
		 if(st!=null)
		  //creating ResultSet Object
		rs=st.executeQuery(query);
		 
		System.out.println(query);
		////process the ResultSet object
		if(rs!=null) {
			boolean flag=false;
			while(rs.next()) {
				flag=true;
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			}//while close
			if(flag==false) {
				System.out.println("Record not found");
			}
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


