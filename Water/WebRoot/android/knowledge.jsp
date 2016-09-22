<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.apache.Algorithm.*" import="java.sql.*" import="com.apache.MySql.*" import="java.lang.*" %>
<%	    
		String parName = request.getParameter("parName");
		ExeSql exeSql = new ExeSql();
		parName = parName.toString().trim(); 	
		if(parName.equals("1"))
		{
			parName = "PH值";
		}
		else if(parName.equals("2")){
			parName = "溶解氧";
		}
		else if(parName.equals("3")){
			parName = "高锰酸盐指数";
		}
		else if(parName.equals("4")){
			parName = "氨氮";
		}	
		String sql = "select 产生原因,解决方案 from knowledge where 参数 ='"+ parName +"'";
		ResultSet rs = Select.Result(sql);
		while(rs.next())
		{
			out.print(rs.getString(1));
			out.print("_");
			out.print(rs.getString(2));
			out.print("_");
		}
%>

    