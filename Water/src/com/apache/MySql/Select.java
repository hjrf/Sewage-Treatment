package com.apache.MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select extends ConnMysql{
	//public static ArrayList<String> list = new ArrayList<String>();
	static Connection conn = null;	
	static ResultSet rs = null;	
	static PreparedStatement statement = null;
	public static ResultSet Result(String sql)
	  {
		try {
			Class.forName(driver);
		} catch (Exception e){
			System.out.println("MySql驱动加载成功");
			e.printStackTrace();
		}			
		try {
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e1) {
			System.out.println("MySql用户名密码验证成功");
			e1.printStackTrace();
		}
		 try {
				statement = conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error:执行SQL语句预编译出错");
			}
		try {
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error：SQL查询语句执行出错！");
		}
//		finally{
//			try {
//				if(!conn.isClosed()) 
//				{
//					conn.close();
//				}
//				if(!statement.isClosed())
//				{
//					statement.close();
//				}
//				if(!rs.isClosed())
//				{
//					rs.close();
//				}
//			} catch (SQLException e) {
				// TODO Auto-generated catch block
////				e.printStackTrace();
//			}
//		}
		return rs;
	  }
}
