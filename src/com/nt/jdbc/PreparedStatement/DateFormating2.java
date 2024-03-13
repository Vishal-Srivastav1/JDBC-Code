package com.nt.jdbc.PreparedStatement;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import oracle.sql.DATE;

public class DateFormating2 {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
     String s1="14-09-2023"; // here direct java.SQL.date conversion is not possible first convert into java.Util.date then convert into java.SQL.date               
     SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");//conversion simple date to java.util.data
     //java.util.Date d1=sd1.parse(st1);
     java.util.Date ud1=sdf.parse(s1);
     System.out.println();
     System.out.println("String Date ->"+s1);
     System.out.println("java.util.Date ->"+ud1);
     System.out.println();
     
     long ls =ud1.getTime();// conversion util date to java.SQL.date
     java.sql.Date dsDate=new java.sql.Date(ls);
      System.out.println();
      System.out.println();
      System.out.println("java.Sql.Date ->"+dsDate);
      
      String s2="2023-03-04"; // here direct converson because string date is alredy SQL date formate
      java.sql.Date dsDate2= java.sql.Date.valueOf(s2);
      System.out.println();
      System.out.println("String Date In form of SQL date  ->"+s2);
      System.out.println("java.sql.Date ->"+dsDate2);
      
      SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");//conversion SQL date to simple data "String Date"
      String sdString =dateFormat.format(dsDate2);
      System.out.println();
      System.out.println("Convertion java.Sql.Date to String date ->"+sdString);
      System.out.println();
      System.out.println();
      
      String sttString= "12-10-2023";
      SimpleDateFormat sd1=new SimpleDateFormat("dd-MM-yyyy");
      java.sql.Date jDate=new java.sql.Date(sd1.parse(sttString).getTime());//here direct conversion String to SQL
      System.out.println("One line Converstion String Date ->"+sttString+" to java.sql.date ->"+jDate);
     
      System.out.println();
      System.out.println();
      DATE d1= new DATE();// sql date give default date 1970-01-01 00:00:00
      System.out.println(d1);
      
      System.out.println();
      System.out.println();
      
      java.util.Date d2 = new java.util.Date();// direct Conversion  system Util_date to SQL_data 
      System.out.println(d2);
      long ld=d2.getTime();
     // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
      java.sql.Date dt1= new java.sql.Date(ld);
      System.out.println(dt1);
      
      
      System.out.println();
      System.out.println("Local Date Time");
      
      LocalDate localDate = LocalDate.now();
      System.out.println("localDate "+localDate );
      System.out.println();
      
      LocalTime localtime  = LocalTime.now();
      System.out.println("localtime "+localtime);
      System.out.println();
      
     LocalDateTime localdatetime=LocalDateTime.now();
     System.out.println("localdatetime "+localdatetime);
     System.out.println();
     System.out.println();
     System.out.println(LocalDateTime.now());
     
	}

}
