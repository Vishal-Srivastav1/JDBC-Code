package com.nt.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertData2 {
	          // pre-Compiled SQL Query 
	         //  insert into student values(1045,'rak','dfg',78.65);
               private static final String QUERY_STRING="Insert into student values(?,?,?,?)";
               
	public static void main(String[] args) {
		// TODO Auto-generated method stub
          PreparedStatement pStatement=null;
          Scanner scanner=null;
          Connection connection=null;
          
          try {
        	  int no=0;
			// reading the input values
        	   scanner=new Scanner(System.in);
        	   
        	   if (scanner!=null) {
        	   System.out.println("Enter the count of insert data ");
        	   no=scanner.nextInt();
        	   } 
        	   //loading the class
        	   Class.forName("oracle.jdbc.driver.OracleDriver");
        	 //establish the connection java App to data base
        	   connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
        	   
        	   if (scanner!=null) {
        		   pStatement=connection.prepareStatement(QUERY_STRING);
				
        	   }
        	   
        	   if (scanner!=null && connection!=null) {
        		  
        		   for (int i = 1; i <=no; ++i) {
        			   System.out.println();
        			   System.out.println("Student "+i+" Details");
        		    System.out.println("Enter the student ID ");
        		    int sno=scanner.nextInt();
        		    System.out.println("Enter the student Name ");
        		    String namString=scanner.next();
        		    System.out.println("Enter the student Addresh ");
        		    String addString=scanner.next();
        		    System.out.println("Enter the student Avrage ");
        		    float avg=scanner.nextFloat();
        		   
			
        	   // set the values in precompiled SQL Query
        	  
        		   pStatement.setInt(1,sno);
        		   pStatement.setNString(2, namString);
        		   pStatement.setString(3, addString);
        		   pStatement.setFloat(4, avg);
				
			      int result;
			      result=pStatement.executeUpdate();
			       
			      if (result==0) {
		  	  System.out.println("Number of record not effected ");
			    	  		
				}else {
					System.out.println("Number of record are effected "+i);
				}
			      
			   
        		   }// for close 	   
        	   }// try close
        	   
		}catch(SQLException se) {
			se.printStackTrace();
			// TODO: handle exception
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
          finally {
        	  try {
        		  if (pStatement!=null) 
      				pStatement.close();
        		  }catch (SQLException se) {
				    se.printStackTrace();
			     }
        	  
        	  try {
        		  if (connection!=null) 
      				connection.close();
        		  }catch (SQLException se) {
				se.printStackTrace();
			}
        	  
        	  try {
        		  if (scanner!=null) 
      				scanner.close();
        		  }catch (Exception e) {
				e.printStackTrace();
			}	
			
		}// finall close
 	}//close main
}// close class
