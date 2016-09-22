<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.apache.Algorithm.*" import="java.sql.*" import="com.apache.MySql.*" import="java.lang.*" %>
<%
	String recordNum = request.getParameter("flag");
	AlgorithmTool at = new AlgorithmTool();
	double [][] trainData = at.GetTrainData("real_train_data",Integer.valueOf(recordNum));
	for(int i = 0;i<trainData.length;i++)
	{
		for(int j = 0;j<trainData[i].length-1;j++)
		{
			out.print(trainData[i][j]);
			out.print("_");
		}
	}
%>

    