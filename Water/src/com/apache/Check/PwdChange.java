package com.apache.Check;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.ExeSql;

public class PwdChange extends HttpServlet{
	ExeSql exeSql = new ExeSql(); 
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
        response.setContentType("text/html;charset=UTF-8");  
        response.setContentType("text/html");  
        response.setHeader("Cache-Control", "no-store");  
        response.setHeader("Pragma", "no-cache");  
        response.setDateHeader("Expires", 0);  
        PrintWriter out = response.getWriter();  
        
        String userName = (String) request.getSession().getAttribute("userName");  
		String pwd = request.getParameter("pwd");
	    if(!pwd.equals(null))
	    {
	    	exeSql.Do("UPDATE user SET 用户密码 = '"+ pwd +"' WHERE 用户姓名 = '"+userName+"'");
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
