package com.nt.jdbc;
//Java App to get Employee details based on given intial characters Employee name 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectLike {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=null;
		String initchar=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//read input
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter the intial char value");
				initchar=sc.next().toLowerCase();//give lower character like as s 
			}
			
			//Convert input value as required for the SQL query
			initchar="'"+initchar+"%'";//gives 's'%
			
			//register jdbc deriver by loading jdbc driver class
			//class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##mydb11am","123");
		
		//create the Statement object
		if(con!=null);
		st=con.createStatement();
		//prepare SQL query
		
		//SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE 's'%;
		String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE "+initchar;
			
		System.out.println(query);
		
		//send and execute SQL query in db s/w
		if(st!=null)
			rs=st.executeQuery(query);
			
		//process the ResultSet object
		
		if(rs!=null) {
			boolean flag=false;
		while(rs.next()) {
			flag=true;
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
		}//while
		if(flag==false)
			System.out.print("Record not found");
			
		}	//if close
			
		}//try close
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid col or table name or SQL exception");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null);
				rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st!=null);
				st.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null);
				con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null);
			sc.close();
			}catch(Exception se) {
				se.printStackTrace();
			}
		}//finally close

	}//main close

}//class close
