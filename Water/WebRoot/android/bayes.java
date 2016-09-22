package com.apache.Algorithm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.ExeSql;

public class bayes extends AlgorithmTool {
	//自适应get与post方式
	ExeSql exeSql = new ExeSql();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		PrintWriter out = response.getWriter();//例化jsp页面输出  
		response.setHeader("Cache-Control", "no-store");//设置传输属性
	    response.setHeader("Pragma", "no-cache");  
	    response.setDateHeader("Expires", 0); 
		String sl1 = request.getParameter("select1");
		String sl2 = request.getParameter("select2");
		//String xxx = request.getParameter("xxx");//获取页面传来的值
	    try {
	    	 int train_num =Integer.valueOf(request.getParameter("train_num"));//获取页面传来的值
			 int test_num = Integer.valueOf(request.getParameter("test_num"));//获取页面传来的值
			 
			 double [][] trainData = GetTrainData(trainTBname,test_num);
			 double [] eveParNum = GetEveParNum(trainData);
			 double [] beforePro = GetBeforePro(trainData, eveParNum);
			 double [][] average = GetAverage(trainData, eveParNum);
			 double [][] standardDev = GetStandardDev(trainData, average); 
			 double [][] testData = GetTrainData(trainTBname,test_num);
			 double[][][] conditionalPro = GetConditionalPro(testData, average, standardDev, false);
			 double [][] afterPro = GetAfterPro(beforePro, conditionalPro, false);
			 double [] bayesResult = GetBayseResult(afterPro, false);
			 
			 
			 double[][] standardData = GetStandardData(warnTBname, standardTBname, true);
			 
			 exeSql.Do("create table IF not EXISTS bayes_result (id int primary key not null auto_increment,处理厂名称 varchar(30),区段名称 varchar(30),PH值 varchar(30),溶解氧 varchar(30),高锰酸盐指数 varchar(30),氨氮 varchar(30),一级前验概率 varchar(30),二级前验概率 varchar(30),三级前验概率 varchar(30),一级后验概率 varchar(30),二级后验概率 varchar(30),三级后验概率 varchar(30),预测结果 varchar(30))");
			 exeSql.Do("truncate table bayes_result");	
			
			for(int i = 0;i<bayesResult.length;i++)
			{
				String sql = " insert into bayes_result (处理厂名称 ,区段名称 ,PH值 ,溶解氧 ,高锰酸盐指数 ,氨氮 ,一级前验概率 ,二级前验概率 ,三级前验概率 ,一级后验概率,二级后验概率 ,三级后验概率,预测结果) values ('处理厂"+ sl1 +"','区段"+ sl2 +"','"+ testData[i][0] +"','"+ testData[i][1] +"','"+ testData[i][2] +"','"+ testData[i][3] +"','"+ beforePro[0] +"','"+ beforePro[1] +"','"+ beforePro[2] +"','"+ Get4decimal(afterPro[i][0]) +"','"+ Get4decimal(afterPro[i][1]) +"','"+ Get4decimal(afterPro[i][2]) +"','"+ bayesResult[i] +"')";
				exeSql.Do(sql);
			}
			out.print("100");	
			 
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
}
