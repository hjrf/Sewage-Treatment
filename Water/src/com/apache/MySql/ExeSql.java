package com.apache.MySql;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ExeSql extends ConnMysql{

	PreparedStatement statement = null;
	 public void Do(String sql)
	 {		
		 try {
			statement = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error:执行SQL语句预编译出错");
		}
		try {
			if(!conn.isClosed()) 
			{
				System.out.println("连接MySql数据库water成功");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		 try {
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error:执行SQL语句执行出错");
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
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
	 }
}
