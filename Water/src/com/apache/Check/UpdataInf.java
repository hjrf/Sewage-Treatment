package com.apache.Check;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.ExeSql;

public class UpdataInf extends HttpServlet {
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
        String cardnum = request.getParameter("cardnum");
        String unit = request.getParameter("unit");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String note = request.getParameter("note");
        if(!cardnum.equals(null))
        {
        	exeSql.Do("UPDATE user SET 证件号码 = '"+ cardnum +"' WHERE 用户姓名 = '"+userName+"'");
        }
        if(!unit.equals(null))
        {
        	exeSql.Do("UPDATE user SET 所在单位 = '"+ unit +"' WHERE 用户姓名= '"+userName+"'");
        }
        if(!telephone.equals(null))
        {
        	exeSql.Do("UPDATE user SET 电话号码 = '"+ telephone +"' WHERE 用户姓名 = '"+userName+"'");
        }
        if(!email.equals("@qq.com"))
        {
        	exeSql.Do("UPDATE user SET 电子邮箱 = '"+ email +"' WHERE 用户姓名 = '"+userName+"'");
        }
        if(!note.equals(null))
        {
        	exeSql.Do("UPDATE user SET 备注信息 = '"+ note +"' WHERE 用户姓名 = '"+userName+"'");
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
