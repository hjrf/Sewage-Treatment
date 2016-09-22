package com.apache.DataInput;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.ExeSql;

public class doInf extends HttpServlet{
	ExeSql exeSql = new ExeSql(); 	
	//自适应get与post方式
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		PrintWriter out = response.getWriter();//例化jsp页面输出  
		response.setHeader("Cache-Control", "no-store");//设置传输属性
	    response.setHeader("Pragma", "no-cache");  
	    response.setDateHeader("Expires", 0); 
		String adjustValue = request.getParameter("adjustValue");//获取页面传来的值
		String yao1 = request.getParameter("yao1");//获取页面传来的值
		String yao2 = request.getParameter("yao2");//获取页面传来的值
		String yao3 = request.getParameter("yao3");//获取页面传来的值
		String waterMen = request.getParameter("waterMen");//获取页面传来的值
		String waterValue = request.getParameter("waterValue");//获取页面传来的值
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		exeSql.Do("create table IF not EXISTS doInf (id int primary key not null auto_increment,操作时间 varchar(30),总调节力度 varchar(30),药物1投放量 varchar(30),药物2投放量 varchar(30),药物3投放量  varchar(30),开启注水口 varchar(30),注水量 varchar(30))");
		exeSql.Do("insert into doInf (操作时间 ,总调节力度 ,药物1投放量 ,药物2投放量 ,药物3投放量  ,开启注水口,注水量 ) values ('"+ time +"','"+ adjustValue +"','"+ yao1 +"','"+ yao2 +"','"+ yao3 +"','"+ waterMen +"','"+ waterValue +"')");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
}
