package com.nt.jdbc.PreparedStatement;
// Retriving the Date based on the PID; 
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DatePrinting {
               //SQL Query
	//select pid,pname,dob,doj,dom from PERSON_INFO_DATES where pid=30;
	private static final String QUER_STRING="select pid,pname,dob,doj,dom from PERSON_INFO_DATES where pid=?";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PreparedStatement pStatement=null;
		Connection connection=null;
		ResultSet resultSet=null;
		Scanner scanner=null;
	        int	id=0;
		try {
			//creating variable
			
			// reading input from keyboard
			 scanner=new Scanner(System.in);
		
			if (scanner!=null) {
			System.out.println("Enter the PID ");
			id=scanner.nextInt();
		}
			//load the class its Optional
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the Connection 
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			
			if (connection!=null) 
				pStatement=connection.prepareStatement(QUER_STRING);
				
			pStatement.setInt(1, id);
			
				if (pStatement!=null) {
					resultSet=pStatement.executeQuery();
					
				}/*
				if (resultSet!=null) {
					while (resultSet.next()!=false) {
						System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5));
					}
					
				}*/
				if (resultSet!=null) {
					boolean flag=false;
					while (resultSet.next()!=false) {
						flag=true;
						int sno= resultSet.getInt(1);
						String nameString=resultSet.getString(2);
						java.sql.Date ds1=resultSet.getDate(3);
						java.sql.Date ds2=resultSet.getDate(4);
						java.sql.Date ds3=resultSet.getDate(5);
						SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
						  String st1=sm.format(ds1);
						  String st2=sm.format(ds2);
						  String st3=sm.format(ds3);
						  System.out.format(" SID \t NAME\t\tDOB\t\tDOJ\t\t dom\n");
						System.out.println(" "+sno+"\t "+nameString+" \t"+st1+" \t"+st2+"\t "+st3);
						
					}
					if (flag==false) {
						System.out.println("\tNumber of recorded not Effected ");
					}
					else {
						System.out.println("\tNumber of recorded Not Found ");
					}
				}
			
			
		} //try close
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (resultSet!=null) {
					resultSet.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (pStatement!=null) {
					pStatement.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (connection!=null) {
					connection.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (scanner!=null) {
					scanner.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}//finally close 
	}//main close
}//class close
