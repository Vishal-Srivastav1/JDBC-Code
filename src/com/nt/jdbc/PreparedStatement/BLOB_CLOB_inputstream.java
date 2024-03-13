package com.nt.jdbc.PreparedStatement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;



public class BLOB_CLOB_inputstream {
	 
            private final static String QUERY_STRING="insert into ARTIST_INFO1CLOB values(PID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		
		 String name=null, addrs=null,resumeloction=null,photolocation=null;
		
		
		
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
						System.out.println("Enter the photoLocation ");
						photolocation=scanner.next().replace("?","");
				}
              try (Reader reader =new FileReader(resumeloction);
            		  InputStream ipStream=new FileInputStream(photolocation)){
				      
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
						preparedStatement.setBinaryStream(4, ipStream);        //
					}	                                                       // 1->if we need to give multiple path then use single right" / slash"                   
                    // executing the SQL Query                                 // ‪‪C:/Users/Acer/Desktop/dog2.txt
                        int count=0;                                           // Enter the photoLocation 
                                                                               // C:/Users/Acer/Desktop/mk.jpg
                    if (preparedStatement!=null) {                             //
						count=preparedStatement.executeUpdate();               //  2->for one path  use" \\ back_slash"
					}                                                          // C:\\Users\\Acer\\Desktop\\mk.jpg
                    if (count==0) {                                            //
						System.out.println("RECORD NOT INSERTED ");            //
					}
                    else {
						System.out.println("RECORDED INSERTED "+ count);
					}
			
              }	//inner try 3 close 
		} //inner try 2 close
	}//outer try close 1
		catch (SQLException se) {                                      //OutPut 
			System.out.println("problem in record insertion ");        // Enter Actor Name 
			se.printStackTrace();                                      // vishal
			                                                           // Enter the Actor Addrs 
		}catch (FileNotFoundException fe) {                            // shivpur
			System.out.print(" Error: Please give Right Path ");       // Enter the resumeLocation 
		}                                                              // C:/Users/Acer/Desktop/dog2.txt
		catch (Exception e) {                                          // Enter the photoLocation 
			e.printStackTrace();                                       // C:/Users/Acer/Desktop/mk.jpg
		}                                                              // RECORDED INSERTED 1

}// main close 
}// class close 
