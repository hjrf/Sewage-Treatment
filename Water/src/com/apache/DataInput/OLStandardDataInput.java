package com.apache.DataInput;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.ExeSql;

public class OLStandardDataInput extends HttpServlet {
	ExeSql exeSql = new ExeSql(); 
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
        response.setContentType("text/html;charset=UTF-8");  
        response.setContentType("text/html");  
        response.setHeader("Cache-Control", "no-store");  
        response.setHeader("Pragma", "no-cache");  
        response.setDateHeader("Expires", 0);  
        PrintWriter out = response.getWriter();  
              
        String tablename = request.getParameter("tablename");
        String parameter = request.getParameter("parameter");
        String level1 = request.getParameter("level1");
        String level2 = request.getParameter("level2");
        String level3 = request.getParameter("level3");
        
        exeSql.Do("create table IF not EXISTS "+ tablename +"(id int primary key not null auto_increment,污染物 varchar(30),级别1 varchar(30),级别2 varchar(30),级别3 varchar(30))");
        exeSql.Do("insert into "+ tablename +" (污染物,级别1,级别2,级别3) values ('"+ parameter +"','"+ level1 +"','"+ level2 +"','"+ level3 +"')");
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
