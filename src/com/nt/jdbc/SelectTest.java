/*
 * Q-> Write a JDBC Application to get Employee Details from EMP DB based on the given 3 Desgnation 
 * */

package com.nt.jdbc;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Read input from end USER 
		Scanner scanner=null;
		String desgn1=null,desgn2=null,desgn3=null;
		Connection con=null;
		Statement st=null;
		ResultSet resultSet=null;
		
		
		try {
			scanner=new Scanner(System.in);
			if(scanner!=null) {
				System.out.println("Enter desgn1 : ");
			    desgn1=scanner.next();
			    System.out.println("Enter desgn2 : ");
			    desgn2=scanner.next();
			    System.out.println("Enter desgn3 : ");
			    desgn3=scanner.next();
			}	
		
		
		//convert the input According to the SQL query
		desgn1="'"+desgn1+"'";// gives clerk
		desgn2="'"+desgn2+"'";// gives salesman
		desgn3="'"+desgn3+"'";//  gives manager
		
		//Load the jdbc class
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		
	// Establish the jdbc Connnection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##mydb11am","123");
		
			//create the Statement object
			if(con!=null);
			st=con.createStatement();
		   
		   //prepare SQL query
			//  select empno,ename,job,sal,deptno from emp where job in('clerk','manager','salesman')order by job;
		   String query="select empno,ename,job,sal,deptno from emp where job in("+desgn1+","+desgn2+","+desgn3+")order by job";
		   System.out.println(query);
		   
		   //send And execute SQL Query
		   if(st!=null)
			   resultSet=st.executeQuery(query);
			   
		if(resultSet!=null) {
			System.out.println("EMP Details ARE : ");
			
			 if(resultSet.next()==false) {
		    	 System.out.println("check the Code Once or Your Input data");
		     }
			 
			while(resultSet.next()!=false) {
				System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+" "+resultSet.getInt(4)+"\t"+resultSet.getInt(5));
			}//while close
		}//if close
		
		
	
	}//try close
		    
		
		catch (SQLException se) {
			se.printStackTrace();
			// TODO: handle exception
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally {
			try {
				if(resultSet!=null) {
					resultSet.close();
				}
					
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}
			
			try {
				if(st!=null) {
					st.close();
				}
					
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}
			
			try {
				if(con!=null) {
					con.close();
				}
					
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}
			
			try {
				if(scanner!=null) {
					scanner.close();
				}
					
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		}// finally close
		
		
	}// Main close

}//class close
