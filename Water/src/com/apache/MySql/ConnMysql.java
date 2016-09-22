package com.apache.MySql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnMysql {
	protected static String driver = "com.mysql.jdbc.Driver";
	protected static String url = "jdbc:mysql://127.0.0.1:3306/water?useUnicode=true&characterEncoding=UTF-8";
	protected static String user = "root"; 
	protected static String pwd = "";
	Connection conn = null;	
	ResultSet rs = null;	
	PreparedStatement statement = null;
	public ConnMysql()
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
	}
}
