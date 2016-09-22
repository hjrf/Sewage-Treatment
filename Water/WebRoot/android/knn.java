package com.apache.Algorithm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.ExeSql;

public class knn extends AlgorithmTool {
	//自适应get与post方式
	ExeSql exeSql = new ExeSql();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		PrintWriter out = response.getWriter();//例化jsp页面输出  
		response.setHeader("Cache-Control", "no-store");//设置传输属性
	    response.setHeader("Pragma", "no-cache");  
	    response.setDateHeader("Expires", 0); 
		String kValue = request.getParameter("kValue");//获取页面传来的值
		String sl1 = request.getParameter("select1");
		String sl2 = request.getParameter("select2");
	    try {
	    	 int k = Integer.valueOf(kValue);
	    	 int train_num =Integer.valueOf(request.getParameter("train_num"));//获取页面传来的值
			 int test_num = Integer.valueOf(request.getParameter("test_num"));//获取页面传来的值
			 
			 double [][] trainData = GetTrainData(trainTBname,test_num,false);
			 double [][] testData = GetTrainData(trainTBname,test_num);
			 double [][][] allODistance = GetAllODistance(trainData, testData,false);
			 double [][] minODistance = GetMinODistance(allODistance,5,false);
			 double [][] minODistance_Standard = GetMinODistance_Standard(allODistance,5,true);
			 double [] knnResult = GetKnnResult(minODistance_Standard,true);
			 exeSql.Do("create table IF not EXISTS knn_result (id int primary key not null auto_increment,处理厂名称 varchar(30),区段名称 varchar(30),PH值 varchar(30),溶解氧 varchar(30),高锰酸盐指数 varchar(30),氨氮 varchar(30),k值 varchar(30),最近欧氏距离 varchar(30),预测结果 varchar(30))");
			 exeSql.Do("truncate table knn_result");	
			
			for(int i = 0;i<knnResult.length;i++)
			{
				String sql = " insert into knn_result (处理厂名称 ,区段名称 ,PH值 ,溶解氧 ,高锰酸盐指数 ,氨氮 ,k值,最近欧氏距离,预测结果) values ('处理厂"+ sl1 +"','区段"+ sl2 +"','"+ testData[i][0] +"','"+ testData[i][1] +"','"+ testData[i][2] +"','"+ testData[i][3] +"','"+ k +"','"+ minODistance[i][0] +"','"+ knnResult[i] +"')";
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
