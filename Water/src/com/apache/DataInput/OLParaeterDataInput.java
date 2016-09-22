package com.apache.DataInput;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.ExeSql;

public class   OLParaeterDataInput extends HttpServlet {
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
        String value = request.getParameter("value");
        
        exeSql.Do("create table IF not EXISTS "+ tablename +"(id int primary key not null auto_increment,参数名称 varchar(30),参数数值 varchar(30))");
        exeSql.Do("insert into "+ tablename +" (参数名称,参数数值) values ('"+ parameter +"','"+ value +"')");
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
