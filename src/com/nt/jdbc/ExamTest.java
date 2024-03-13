package com.nt.jdbc;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExamTest {
        public static void main (String[] args){
           Statement st=null;
            Scanner sc=null;
          Connection con=null;
    try{
      // reading the input 
       String newName=null, newcity=null;
      float avg=00.0f;
     int no=0;

        // creating input Stream object
         sc=new Scanner(System.in);
              if(sc!=null){
       	   System.out.println("Enter the newName ");
        	  newName=sc.nextLine().toLowerCase();
       	   System.out.println("Enter the newCity name");
       	   newcity=sc.nextLine().toLowerCase();
	System.out.println("Enter the  AvgSalary ");
       	   avg=sc.nextFloat();
	System.out.println("Enter the Sno ");
       	   no=sc.nextInt();
          }
      //convert the input vaues according to the Sql Query
               newName="'"+newName+"'";// its gives the new Name like 'vishal'
                 newcity="'"+newcity+"'";  ///  its gives the new city like 'hyd' 

          // register the jdbc deriver  by loading  Jdbc driver class 
  // Class.forName("oracle.jdbc.driver.OracleDriver");
     // establish the Connection
    // con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##mydb11am","123");
    con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "c##mydb11am","123");
         // create statement object
 if(con!=null) 
            st=con.createStatement();


     //Prepare SQL Query 
                         
// String Query=update student set sname='vishal', sadd='hyd', avg=10000 where sno=101;;
         String Query="update student set sname="+newName+",sadd="+newcity+",avg="+avg+" where sno="+no;
           /// printing the query 
            System.out.println(Query);

// send and execute SQL query in db S/w
    int count=0;
      if(st!=null)
        count=st.executeUpdate(Query);
         
        //process the result
        if(count==0){
          System.out.println("No record Found ");
     }  else{
      System.out.println("No of record are effected  "+count);
        }
     }//try close

catch (SQLException se){
        if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
          System.out.println(" check the syntax coloum name or table name ");
        else{
           if(se.getErrorCode()==12899)
           System.out.println("Do not insert more then coloum size data " );
        se.printStackTrace();
        }
    }catch(Exception e){
      e.printStackTrace();
}       

    finally{
     //close all jdbc connection or stream con
       try{
         if(st!=null){
              st.close();
         }
} catch(SQLException se){
      se.printStackTrace();
}

 try{
         if(con!=null){
              con.close();
         }
} catch(SQLException se){
      se.printStackTrace();
}

 try{
         if(sc!=null){
              sc.close();
         }
} catch(Exception e){
      e.printStackTrace();
}
}//finally close
}//main close
}// class close





