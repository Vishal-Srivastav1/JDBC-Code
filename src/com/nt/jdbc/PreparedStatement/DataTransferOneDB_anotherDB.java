package com.nt.jdbc.PreparedStatement;
// 1. (DATA TRANSFER ONE TABLE TO ANOTHER TABLE)  or (WE CAN SAY ONE DB TO ANOTHER DS S/W)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataTransferOneDB_anotherDB {
         private final static String QUERY_STRING="insert into person_info_dates1 values(?,?,?,?,?)";
         private final static String QUERY_STRING2="select PID,PNAME,DOB,DOJ,DOM from  person_info_dates";   
         		
	public static void main(String[] args) {
		// read input
		PreparedStatement pStatement=null;
		ResultSet resultSet=null;
		Connection connection=null;
		Statement statement=null;
		
		try {
			//load class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// establish the connection 
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");                      
			// creating the prepared Statement obj having precompiled SQL query
			if (connection!=null) {
				pStatement=connection.prepareStatement(QUERY_STRING);
				
			}// create statement object 
			if (connection!=null) {
				statement=connection.createStatement();
			}
			if (statement!=null) {
				resultSet=statement.executeQuery(QUERY_STRING2);
			}
			if (resultSet!=null) {
				boolean flag=false;
				while (resultSet.next()!=false) {
					flag=true;
					int id=resultSet.getInt(1);
					String name=resultSet.getString(2);
					java.sql.Date ds1=resultSet.getDate(3);
					java.sql.Date ds2=resultSet.getDate(4);
					java.sql.Date ds3=resultSet.getDate(5);
				
					// setting the data in pre-compiled SQL query
					pStatement.setInt(1, id);
					pStatement.setString(2, name);
					pStatement.setDate(3, ds1);
					pStatement.setDate(4, ds2);
					pStatement.setDate(5, ds3);
			
					pStatement.executeUpdate();
				System.out.println("ID\t NAME \t\tDOB \t\tDOJ \t\tDOM");
				System.out.println(id+"\t "+name+" \t"+ds1+" \t"+ds2+" \t"+ds3);
				System.out.println();
				}
				
				if (flag==false) {
					System.out.println(" Record NOt Coppied");
				}
				else {
					System.out.println(" Record Coppied ");
				}
			}
		
		}//try close 
		catch (SQLException se) {
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(pStatement!=null)
					pStatement.close();
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}
			try {
				if(resultSet!=null)
					resultSet.close();
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}
			try {
				if(statement!=null)
					statement.close();
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}
			
			try {
				if(connection!=null)
					connection.close();
			} catch (SQLException se) {
				// TODO: handle exception
				se.printStackTrace();
			}
			
			
		}//finally		
	}//main
}//class
