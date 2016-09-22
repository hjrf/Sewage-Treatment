<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.apache.Algorithm.*" import="java.sql.*" import="com.apache.MySql.*" import="java.lang.*" %>
<%
	AlgorithmTool at = new AlgorithmTool();
	double [][] trainData = at.GetTrainData("real_train_data",5);
	for(int i = 0;i<trainData.length;i++)
	{
		for(int j = 0;j<trainData[i].length;j++)
		{
			//out.print(trainData[i][j]);
			//out.print("_");
		}
	}
	
	String [][] allStandardData = at.GetAllStandardData("excel_input_standard");
	for(int i = 0;i<allStandardData.length;i++)
	{
		for(int j = 0;j<allStandardData[i].length;j++)
		{
			out.print(allStandardData[i][j]);
			out.print("_");
		}
	}
%>

    