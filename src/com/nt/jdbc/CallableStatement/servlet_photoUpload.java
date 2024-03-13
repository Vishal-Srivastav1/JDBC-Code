package com.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.db.DBConnect;
import com.mysql.cj.xdevapi.PreparableStatement;
//@WebServlet("/uploadphotos")
//@MultipartConfig
public class VishalPhotosUploadsServlet extends HttpServlet {
	private static final String sqlquery="insert into photos (description,image,pdate) values(?,?,?)";
       @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	Part p =req .getPart("files");
    	String fileName=p.getSubmittedFileName();
    	
    	String remark= req.getParameter("remark");
    //	System.out.println(fileName + " "+ remark);
    	
    	HttpSession session = req.getSession();
    	
    	try {
    		Connection conn=DBConnect.getConn();
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn=DriverManager.getConnection("jdbc:mysql:///job_portal","root","#vmk5657");
//    		
			 java.util.Date d2 = null;
		      if (d2==null) 
		    	 d2=new java.util.Date();  
				      long ld=d2.getTime();
				      java.sql.Date sqlDate= new java.sql.Date(ld);
				      
			PreparedStatement pStatement=conn.prepareStatement(sqlquery);
			pStatement.setString(1, remark);
			pStatement.setString(2, fileName);
			pStatement.setDate(3,sqlDate);
			
			int i= pStatement.executeUpdate();
			if (i==1) {
				 String path= getServletContext().getRealPath("")+"userimg";
				 File file =new File(path);
				 p.write(path+File.separator+fileName);	 
				
						session.setAttribute("succMsg", "Post uploaded Successfully..");
						resp.sendRedirect("viewphoto.jsp");
			
			}else {
				session.setAttribute("succMsg", "Error: Problem in Server Side..");
				resp.sendRedirect("photosUploads.jsp");
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
