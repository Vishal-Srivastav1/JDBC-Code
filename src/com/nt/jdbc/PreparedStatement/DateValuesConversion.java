package com.nt.jdbc.PreparedStatement;                                          //    output 1
			

import java.text.SimpleDateFormat;                                             // String date value::21-11-1990
			public class DateValuesConversion {                                //util date::Wed Nov 21 00:00:00 IST 1990

	public static void main(String[] args)throws Exception{
		// TODO Auto-generated method stub
		System.out.println("\t\t1st");
		String  s1="21-11-1990";//dd-mm-yyyy
      SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
      java.util.Date ud1=sdf.parse(s1);
      
      System.out.println("\tString date value::"+s1);
      System.out.println("\tutil date::"+ud1);
      System.out.println("===========================================================");
      System.out.println();
      
      System.out.println("\t\t2st");
      //converting java.util Date class obj to java.sql.Date class obj
      long ms=ud1.getTime();//gives no.of millisecond that elapsed b/w
      //ud1 date and time and 1970 jan 1st mid night 00:00hrs (Epoach standard)
      
      java.sql.Date sd1=new java.sql.Date(ms);
      System.out.println("\tutil date::"+ud1);                         //  output	2st                                   
      System.out.println("\tsql date::"+sd1); //yyyy-mm-dd            //  util date::Wed Nov 21 00:00:00 IST 1990
	                                                                //  sql date::1990-11-21                   
      System.out.println("===========================================================");
      System.out.println();
      System.out.println("\t\t3st");
      //IF String date value pattern is yyyy-MM-dd pattern then it can be converted directly to java .sql Date class obj
      //with out converting to java uti.Date classs obj
        String s2="1991-12-25";//yyyy-MM-dd
        java.sql.Date sd2=java.sql.Date.valueOf(s2);
        System.out.println("\tString date value:: "+s2);
        System.out.println("\tSql date value:: "+sd2);
      
      
      
      
      
      
      
      
      
      
      
	}}
