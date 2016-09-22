package com.apache.Check;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.Select;

public class RegCheck extends HttpServlet  { 

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)  
	    throws ServletException, IOException {  	
		 	ResultSet rs = null;
		 	String key = null;
		 	String pwd = null;
	        PrintWriter out = response.getWriter();  
	        try {  
	            response.setHeader("Cache-Control", "no-store");  
	            response.setHeader("Pragma", "no-cache");  
	            response.setDateHeader("Expires", 0);  
	            
	      
	            String subcek = request.getParameter("subcek");
	            String name = request.getParameter("name");

			 	
	            String sql = "select * from user where 用户姓名 = '"+ name +"'";             
	            rs = Select.Result(sql);
	            try {
	            	if(rs.next())
	            	{	
	            		key = "2";	
	            		while(rs.next())
	            		{
	            			pwd = rs.getString("用户密码");
	            		}
	            	}
	            	else
	            	{
	            	    key = "1";	
	            	}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();	
				}
	        } finally { 
	        	out.print(key);
	            out.close();  
	        }  
	    }   
	  
	    @Override  
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
	    throws ServletException, IOException {  
	        processRequest(request, response);  
	    }   
	  
	    @Override  
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
	    throws ServletException, IOException {  
	        processRequest(request, response);  
	    }  
}
