package com.apache.Check;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.ExeSql;
import com.apache.MySql.Select;

public class LogCheck extends HttpServlet {
	ExeSql exeSql = new ExeSql(); 	
	public static String userName;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
		String key = null;
		String type = null;
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        try {  
            response.setContentType("text/html");  
            response.setHeader("Cache-Control", "no-store");  
            response.setHeader("Pragma", "no-cache");  
            response.setDateHeader("Expires", 0); 
            String name = request.getParameter("name");
            String pwd = request.getParameter("pwd");
            String level = request.getParameter("level");
            String sql = "select * from user where 用户姓名 = '"+ name +"'"; 
            ResultSet rs = null;
            rs = Select.Result(sql);
            try {         
				if(!rs.next()){
					key = "1";
				}
				else 
				{
					if(rs.getString("用户密码").equals(pwd))
					{
						if(rs.getString("用户级别").equals(level))
						{
							key = "2";
							request.getSession().setAttribute("userName",name);
							if(level.equals("1"))
							{
								type ="普通用户";
							}
							else if(level.equals("2"))
							{
								type ="管理用户";
							}
							else if(level.equals("3"))
							{
								type ="专家用户";
							}
							Date date=new Date();
							DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String time=format.format(date);
							request.getSession().setAttribute("userType",type);
							exeSql.Do("create table IF not EXISTS userLogInf (id int primary key not null auto_increment,用户姓名 varchar(30),用户级别 varchar(30),登录时间 varchar(30))");
							exeSql.Do("insert into userLogInf (用户姓名,用户级别,登录时间 ) values ('"+ name +"','"+ type +"','"+ time +"')");
						}
						else
						{
							key = "4";
						}
					}
					else
					{
						key = "3";						}
					}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
            
        }finally { 
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
