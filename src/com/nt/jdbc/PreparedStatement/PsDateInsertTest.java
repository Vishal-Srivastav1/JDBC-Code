package com.nt.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.Scanner;

public class PsDateInsertTest {
  public static final String INSERT_DATE_QUERY="INSERT INTO PERSON_INFO_DATES VALUES(PID_SEQ.NEXTVAL,?,?,?,?)";                 
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		PreparedStatement ps=null;
		
		
		try {
			//read inputs
			sc =new Scanner(System.in);
			String name=null,sdob=null,sdoj=null,sdom=null;
			if(sc!=null) {
				System.out.println("Person name:: ");
				name=sc.next();
				System.out.println("Person DoB(dd-MM-yyyy):: ");
				sdob=sc.next();
				System.out.println("Person Doj(yyyy-MM-dd):: ");
				sdoj=sc.next();
				System.out.println("Person Dom(MMM-dd-yyyy)::");
				sdom=sc.next();
	
			}
			//convert String date values to java.sql.Date class objs
			//for Dob(dd-MM-yyyy)
			//convert String date value to java.util.date class obj
			 SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
			 java.util.Date udob=sdf1.parse(sdob);
			 System.out.println("1java UtilDate"+udob);
			 //converting java.util.date class obj to java.sql.date class obj
			 long ms=udob.getTime();
			 java.sql.Date sqdob=new java.sql.Date(ms);
			 System.out.println("1java SqlDate"+sqdob);
	//for Doj (YYYY-MM-dd Direcct Conversion)
			 //converting String date value to java.sql.Date class obj
			 java.sql.Date sqdoj=java.sql.Date.valueOf(sdoj);
	//for Dom(MMMM-dd-YYYY)
			//convert String date valu to java.util.date class obj
			 SimpleDateFormat sdf2=new SimpleDateFormat("MMM-dd-yyyy");
			 java.util.Date udom=sdf2.parse(sdom);
			 System.out.println("2utilDate"+udom);
			 //converting java.util.Date class obj to java.sql.Date class obj
			 ms=udom.getTime();
			 java.sql.Date sqdom=new java.sql.Date(ms);
			 System.out.println("2sqlDate"+sqdom);
			 //load jdbc driver class
			 Class.forName("oracle.jdbc.driver.OracleDriver");
	//establish the connection
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			//create preparedStetement obj
			 if(con!=null) {
				 ps=con.prepareStatement(INSERT_DATE_QUERY);
				 System.out.println(INSERT_DATE_QUERY);
				 //SET values to query param
				 if(ps!=null) {
					 ps.setString(1,name);
					 ps.setDate(2,sqdob);
					 ps.setDate(3,sqdoj);
					 ps.setDate(4,sqdom);
					 
				 }
				 // execute query
				 int count=0;
				 if (ps!=null)
					 count=ps.executeUpdate();
				 //proceaa the results
                if(count==0) 
                	System.out.println("Record not inserted");
                	else 
						System.out.println("Record inserted");
					
                
			 }//try
		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in record insertion");
			// TODO: handle exception
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(ps!=null)
					ps.close();
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}//finally		
	}//main
}//class
