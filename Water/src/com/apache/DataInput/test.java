package com.apache.DataInput;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.ExeSql;

public class test extends HttpServlet{
	ExeSql exeSql = new ExeSql(); 	
	//自适应get与post方式
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		PrintWriter out = response.getWriter();//例化jsp页面输出  
		response.setHeader("Cache-Control", "no-store");//设置传输属性
	    response.setHeader("Pragma", "no-cache");  
	    response.setDateHeader("Expires", 0); 
		String flag = request.getParameter("flag");//获取页面传来的值
		out.print("100");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
}
