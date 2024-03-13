package com.nt.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import java.util.Scanner;

public class AgeCalculation_throwJavaApp {
	//select round((sysdate-dob)/365.25,2) from person_info_dates where pid=30; Using Db s/w no need of java conversion      
                  private final static String QUER_STRING="select pid,pname,dob from PERSON_INFO_DATES where pid=?";
	public static void main(String[] args) {
		//read inputs
		PreparedStatement preparedStatement=null;
		Connection connection=null;
		ResultSet resultSet=null;
		Scanner scanner=null;
		int id=0;
		
			
		try {
			 // read input 
			scanner=new Scanner(System.in);
			if (scanner!=null) {
				System.out.println("Enter the PID ");
				 id=scanner.nextInt();
				
			}
			//load the class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//load the class
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
			
			// creating the prepaid statement obj with precompile SQL query
			if (connection!=null) {
				preparedStatement=connection.prepareStatement(QUER_STRING);
				
			}
			// setting the parem in SQL query
			if (preparedStatement!=null) {
				preparedStatement.setInt(1, id);
			}
			 // executing the SQL query
			if (preparedStatement!=null) {
				resultSet=preparedStatement.executeQuery();
			}
			// working with resultset obj
			
			if (resultSet!=null) {
				boolean flag=false;
				while (resultSet.next()!=false) {
					flag=true;
					int no=resultSet.getInt(1);
					String nameString=resultSet.getString(2);
		            java.sql.Date jsDate=resultSet.getDate(3);
		            java.util.Date sysDate= new java.util.Date();
		            float age=(sysDate.getTime()-jsDate.getTime())/(1000.0f*60.0f*60.0f*24.0f*365.25f);
		            DecimalFormat dsFormat= new DecimalFormat("#.##");
		            System.out.print("id= "+no+" Name= "+nameString+" ");
		            System.out.println("person Age ="+dsFormat.format(age));
				}
				if (flag==false) {
					System.out.println("NUMBER OF RECORD NOT EFFECTED ");
					
				}
				else {
					System.out.println("NUMBER OF RECORD ARE EFFECTED ");
				}
			}
			
		}// try close
		
		catch (SQLException se) {
			se.printStackTrace();
			// TODO: handle exception
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				if (resultSet!=null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (preparedStatement!=null) {
					preparedStatement.close();
				}
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (connection!=null) {
					connection.close();
				}
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (scanner!=null) {
					scanner.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}//finall close
	}// main close
}// class close
