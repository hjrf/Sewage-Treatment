package com.apache.Check;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.ExeSql;

public class RegInfEntry extends HttpServlet {
	ExeSql exeSql = new ExeSql(); 
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)  
	    throws ServletException, IOException {  
		 	String key = null;
	        PrintWriter out = response.getWriter();  
	        try {  
	            response.setHeader("Cache-Control", "no-store");  
	            response.setHeader("Pragma", "no-cache");  
	            response.setDateHeader("Expires", 0);  
	            String subcek = request.getParameter("subcek");
	            String name = request.getParameter("name");
	            String pwd = request.getParameter("pwd");
	            String level = request.getParameter("level");
	            String email = request.getParameter("email");
	            String realname = request.getParameter("realname");
	            String telephone = request.getParameter("telephone");
	            String unit = request.getParameter("unit");
	            String cardnum = request.getParameter("cardnum");
	            String cardtype = request.getParameter("cardtype");
	            String note = request.getParameter("note");
	            exeSql.Do("create table IF not EXISTS user (id int primary key not null auto_increment,用户姓名 varchar(30),用户密码 varchar(30),用户级别 varchar(30),电子邮箱 varchar(30),真实姓名 varchar(30),电话号码 varchar(30),所在单位 varchar(30),证件号码 varchar(30),证件类型 varchar(30),备注信息 varchar(30))");
	            String sql = "insert into user (用户姓名,用户密码,用户级别 ,电子邮箱 ,真实姓名,电话号码,所在单位 ,证件号码 ,证件类型 ,备注信息) values ('"+ name +"','"+ pwd +"','"+ level +"','"+ email +"','"+ realname +"','"+ telephone +"','"+ unit +"','"+ cardnum +"','"+ cardtype +"','"+ note +"')";
	            if(subcek.equals("1"))
	            {
	            	exeSql.Do(sql);
	            	key = "regt";            	
	            }    
	        }			
	         finally { 
	        	out.print(key);
	            out.close();  
	        }  
	 }
	    private void ExeSql(String string) {
		// TODO Auto-generated method stub
		
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
