package com.nt.jdbc.PreparedStatement;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;



public class CLOB_inputstream2 {
            private final static String QUERY_STRING="insert into ARTIST_INFO1CLOB values(PID_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		
		
		
		String name=null, addrs=null,resumeloction=null;
		
		try 
			// read input
				(Scanner scanner=new Scanner(System.in)){
				if (scanner!=null) {
				
						System.out.println("Enter Actor Name ");
						name=scanner.next();
						System.out.println("Enter the Actor Addrs ");
						addrs=scanner.next();
						System.out.println("Enter the resumeLocation ");
						resumeloction=scanner.next().replace("?","");
				}
              try (Reader reader =new FileReader(resumeloction)){
				      
				   //load the class
				  //// //Class.forName("oracle.jdbc.driver.OracleDriver");
				   //load the class
				  try (Connection  connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
					   // create prepread statement obj with having pre-compile SQL query
						   PreparedStatement preparedStatement=connection.prepareStatement(QUERY_STRING)){

				   // setting the value in parem SQL-Query
                    if (preparedStatement!=null) {
                    	
						
						preparedStatement.setString(1, name);
						preparedStatement.setString(2, addrs);
						preparedStatement.setCharacterStream(3, reader);
					}	
                        int count=0;
                        // executing the SQL Query
                    if (preparedStatement!=null) {
						count=preparedStatement.executeUpdate();
					}
                    if (count==0) {
						System.out.println("RECORD NOT INSERTED ");
					}
                    else {
						System.out.println("RECORDED INSERTED "+ count);
					}
			
              }	//inner try 3 close 
		} //inner try 2 close
	}//outer try close 1
		catch (SQLException se) {
			System.out.println("problem in record insertion ");
			se.printStackTrace();
			
		}catch (Exception e) {
			e.printStackTrace();
		}

}// main close 
}// class close 
