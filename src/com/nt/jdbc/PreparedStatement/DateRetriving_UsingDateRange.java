package com.nt.jdbc.PreparedStatement;
// Write a JDBC app  person Details WHOSE DOB between (START_DOB to END_DOB)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateRetriving_UsingDateRange {
        private final static String QUERY_STRING ="SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES WHERE DOB>=? AND DOB<=? ";                 
	public static void main(String[] args) {
	
		PreparedStatement pStatement=null;
		Connection connection=null;
		ResultSet resultSet=null;
		Scanner scanner=null;
		  String dateString=null, dateString2=null;
		try {
			// reding input from keyboard
			scanner=new Scanner(System.in);
			if (scanner!=null) {

			System.out.println("ENTER THE FIRST DATE VALUE DD-MM-YYYY ");
			 dateString=scanner.next();
			
			System.out.println("ENTER THE SECOND DATE VALUE DD-MM-YYYY ");
			 dateString2=scanner.next();
		}	
			//convert the as for SQL date Obj
			SimpleDateFormat sFormat=new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Date date1=new java.sql.Date(sFormat.parse(dateString).getTime());
			java.sql.Date date2=new java.sql.Date(sFormat.parse(dateString2).getTime());
			
			//load the class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// establish the connection 
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			
			//creating the prepared statement onj
			if (connection!=null) {
				System.out.println(QUERY_STRING);
				System.out.println();
				pStatement=connection.prepareStatement(QUERY_STRING);
	
				pStatement.setDate(1, date1);
				pStatement.setDate(2, date2);
			}
			//working with resultset obj
			if (pStatement!=null) {
				resultSet=pStatement.executeQuery();
			}
			
			if (resultSet!=null) {
				boolean flag =false;
				while (resultSet.next()!=false) {
					flag=true;
					int sno= resultSet.getInt(1);
					String sname=resultSet.getString(2);
					java.sql.Date d1=resultSet.getDate(3);
					java.sql.Date d2=resultSet.getDate(4);
					java.sql.Date d3=resultSet.getDate(5);
					//converting Sql Date in Strig formate
					SimpleDateFormat ds1= new SimpleDateFormat("dd-MM-yyyy");
					String st1=ds1.format(d1);
					String st2=ds1.format(d2);
					String st3=ds1.format(d3);
					
					System.out.format(" PID\tPNAME\t\tDOB\t\tDOJ\t\tDOM\n");
					System.out.println(" "+sno+" \t"+sname+" \t "+st1+" \t "+st2+" \t "+st3);
					System.out.println();
				}
				if (flag==false) {
					System.out.println(" NUMBER OF RECORDED NOT EFFECTED");
				}
			}
			
			
			
			
		}////try close 
		catch (SQLException se) {
			se.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		finally {
			try {
				if (resultSet!=null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (pStatement!=null) {
					pStatement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (connection!=null) {
					connection.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			try {
				if (scanner!=null) {
					scanner.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}// finally close 
	}// main close
}// class close
