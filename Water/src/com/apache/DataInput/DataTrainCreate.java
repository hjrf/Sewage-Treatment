package com.apache.DataInput;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.Algorithm.AlgorithmTool;
import com.apache.MySql.ExeSql;

public class DataTrainCreate extends HttpServlet {
	ExeSql exeSql = new ExeSql(); 	
	//自适应get与post方式
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		PrintWriter out = response.getWriter();//例化jsp页面输出  
		response.setHeader("Cache-Control", "no-store");//设置传输属性
	    response.setHeader("Pragma", "no-cache");  
	    response.setDateHeader("Expires", 0); 
	    String createNum = request.getParameter("creatnum");//获取页面传来的值
	    String way = request.getParameter("level");//获取页面传来的值
	    AlgorithmTool at = new AlgorithmTool();
	    double[][] standardData = null;
	    try {
			standardData = at.GetStandardData(at.standardTBname, false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("获取评价标准数据出错！");
		}
	
			//PH值,溶解氧,高猛酸盐指数,氨氮 ,化学需氧量 ,五日生化需氧量 ,总磷 ,总氮 ,铜 ,锌 ,氟化物 ,硒  ,砷 ,汞 ,镉 ,六价铬 ,铅 ,氢化物 ,挥发酚 ,石油类 ,阴离子表面活性剂 ,硫化物 ,粪大肠菌群 ,Standard
	    exeSql.Do("create table IF not EXISTS data_test_create (id int primary key not null auto_increment,PH值 varchar(30),溶解氧 varchar(30),高猛酸盐指数 varchar(30),氨氮  varchar(30),Standard varchar(30))");
		if(way.equals("1"))
		{
	        for(int i = 0;i< Integer.valueOf(createNum);i++)
	        {
	            String sql = "",sql1 = "",sql2 = "",sql3 = "";
	            //PH值,溶解氧,高猛酸盐指数,氨氮 ,化学需氧量 ,五日生化需氧量 ,总磷 ,总氮 ,铜 ,锌 ,氟化物 ,硒  ,砷 ,汞 ,镉 ,六价铬 ,铅 ,氢化物 ,挥发酚 ,石油类 ,阴离子表面活性剂 ,硫化物 ,粪大肠菌群 ,Standard
	            sql1 = " insert into data_test_create (PH值,溶解氧,高猛酸盐指数,氨氮 ,Standard) values ( ";
	    	    for(int j = 0;j<4;j++)
	    	    {
	    	    	sql2 += " '"+ at.GetRandom(standardData[j][0],standardData[j][2]*2) +"', ";
	    	    }
	    	    //sql2 = sql2.substring(0,sql2.length()-1);	
	            sql3 = " '') ";
	            sql = sql1+sql2+sql3;
	            System.out.println(sql);
	        	exeSql.Do(sql);
	        }
		}
		else if(way.equals("2"))
		{
			exeSql.Do("truncate table data_test_create");
		}
		else 
		{
			System.out.println("执行方式异常！");
		}
	    
	    
	    
		}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
}
