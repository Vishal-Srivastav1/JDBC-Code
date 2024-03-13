package com.nt.jdbc.CallableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*CREATE OR REPLACE PROCEDURE FIRST_PRO(X IN NUMBER, Y IN NUMBER, Z OUT NUMBER) AS 
BEGIN
  z:=x+y;
END FIRST_PRO;*/
public class AdditionCallable {
             private static final String QUER_STRING="{CALL FIRST_PRO(?,?,?)}";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  // read input
		   int no1=0;
		   int no2=0;
		  
		  try (Scanner scanner=new Scanner(System.in)){
			
			 if (scanner!=null) {
				System.out.println("Enter the first Number ");
				no1=scanner.nextInt();
				System.out.println("Enter the Second Number ");
				no2=scanner.nextInt();
			}
			 // establish the connection 
			 try(Connection  connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123")){
					 System.out.println(QUER_STRING);
					 // create callable statement obj having the query calling PL/SQL procedure as the pre-compiled SQL query
				try(CallableStatement cStatement= connection.prepareCall(QUER_STRING)){
				 // register Out param with jdbc data types
				 if (cStatement!=null) {
					cStatement.registerOutParameter(3,Types.INTEGER);
				}
				 // sets values to IN params
				 if (cStatement!=null) {
					cStatement.setInt(1, no1);
					cStatement.setInt(2, no2);
				}
				 // execute/call the PL?SQL function
				 if (cStatement!=null) {
					cStatement.execute();
				}
				 // gather result from OUT param 
				 int result=0;
				 if (cStatement!=null) {
					result=cStatement.getInt(3);
					System.out.println("Sum is= "+result);
				 }//try 4
				}// try 3
			 }//try 2
	}//try close 1
		  catch (SQLException se) {
			  System.out.println("Problem in Data Insertion ");
				se.printStackTrace();
			}
			 catch (Exception e) {
				e.printStackTrace();
			}
		 

}//main close
}//class close