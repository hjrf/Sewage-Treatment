<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.apache.Algorithm.*" import="java.sql.*" import="com.apache.MySql.*" import="java.lang.*" %>
<%
	    	AlgorithmTool at = new AlgorithmTool();
	 		String trainTBname = "real_train_data";//训练集表名
			String standardTBname = "excel_input_standard";//特征参数分类评价标准表名
	    	String warnTBname = "real_train_data";//特征参数分类评价标准表名
			String parName = request.getParameter("parName");
			int train_num =Integer.valueOf(request.getParameter("train_num"));//获取页面传来的值
			String parValue1 = request.getParameter("parValue1");
			String parValue2 = request.getParameter("parValue2");
			String parValue3 = request.getParameter("parValue3");
			String parValue4 = request.getParameter("parValue4");
			 double [][] trainData = at.GetTrainData(trainTBname,train_num,false);
			 double [][] testData = new double[1][4];
			 testData[0][0] = Double.valueOf(parValue1);
			 testData[0][1] = Double.valueOf(parValue2);
			 testData[0][2] = Double.valueOf(parValue3);
			 testData[0][3] = Double.valueOf(parValue4);
			 
			 double [][][] allODistance = at.GetAllODistance(trainData, testData,false);
			 double [][] minODistance = at.GetMinODistance(allODistance,5,false);
			 double [][] minODistance_Standard = at.GetMinODistance_Standard(allODistance,5,true);
			 double [] knnResult = at.GetKnnResult(minODistance_Standard,true);
			 out.print("b");//预测结果
			 out.print("_");
			 out.print(knnResult[0]);//预测结果
			 out.print("_");
			 out.print(minODistance[0][0]);//预测结果
			 out.print("_");
			 out.print(minODistance[0][1]);//预测结果
			 out.print("_");
			 out.print(minODistance[0][2]);//预测结果
		
%>

    