package com.apache.Algorithm;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.Select;
import com.mysql.jdbc.ResultSetMetaData;

public class id3 extends HttpServlet {
	static String trainTBname = "real_train_data";//训练集表名
	static String standardTBname = "excel_input_standard";//特征参数分类评价标准表名
	static int parStandardNum = 3;//每个特征参数共有3种分类
	static int recordStandardNum = 3;//每条记录有3种分类
	
	int TDfiledCount = GetFiledCount(trainTBname);
	int TDrowCount = GetRowCount(trainTBname);
	int SDfiledCount = GetFiledCount(standardTBname);
	int SDrowCount = GetRowCount(standardTBname);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
		response.setHeader("Cache-Control", "no-store");  
	    response.setHeader("Pragma", "no-cache");  
	    response.setDateHeader("Expires", 0); 
//		String excelPach = request.getParameter("xxx");
	    try {
	    	double [][] trainData = GetTrainData(trainTBname);//实际训练数据集
	    	double [][] testData = GetTestData(trainData);//除去分类结果的测试数据
	    	double[] standardResult = GetStandardResult(trainData);//分类结果集合
	    	double[] eveStandardNum = GetEveStandardNum(standardResult);//每种分类结果的数目
	    	double[][] standardData = GetStandardData(standardTBname);//每个特征参数的分级标准
	    	double[][][] conclusionTrainData = GetConclusionTrainData(trainData,testData);//特征参数的种类，每个特征参数的不同分类，全部记录中每种分类结果的数目
	    	double[] beforeRadio =  GetBeforeRatio(eveStandardNum);//获取前验概率
	    	double info_D = GetInfo_D(beforeRadio);//D元组期望
	    	double[] eveInfo_D =  GetEveInfo_D(conclusionTrainData,beforeRadio);//每个特征参数的期望
	    	int rootNode = GetRootNode(info_D,eveInfo_D);//通过Gain值求出根节点
	    	CreateTree(rootNode,standardData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request, response);
	}
	//获取训练数据集

	double [][] GetTrainData(String tableName,boolean print) throws SQLException//记录条数，特征参数数目
	{
		ResultSet rs = Select.Result("SELECT * FROM "+ tableName +"");
		double[][] trainData = new double[TDrowCount][TDfiledCount-1];	
		rs.beforeFirst();//游标移动到第一行之前 
		int num = 0;
		while(rs.next())
		{
			for(int i = 1;i<TDfiledCount;i++)//获取除id字段之外的所有数据
			{
				try{
				trainData[num][i-1] = Double.valueOf(rs.getString(i+1));
				}
				catch(Exception e)
				{
					System.out.println("第"+ (num+1) +"记录存在数据为空的字段");
				}
			}
			num++;
		}
		if(print)
		{
			PrintArray(trainData);
		}
		return trainData;
	}	
	//获取每条记录除分类结果的测试数据
	double[][] GetTestData(double [][] trainData,boolean print) throws SQLException
	{
		double [][]	testData = new double[TDrowCount][TDfiledCount-2];//记录数，特征参数数
		for(int i = 0;i<TDrowCount;i++)
		{
			for(int j = 0;j < TDfiledCount-2;j++)
			{	
				testData[i][j] = trainData[i][j];//获取所有记录的每个特征参数的实际值
			}
		}
		if(print)
		{
			PrintArray(testData);
		}
		return testData;	
	}
	//获取每条记录的分类结果
	double[] GetStandardResult(double [][] trainData,boolean print) throws SQLException
	{
		double [] standardResult = new double[TDrowCount];
		for(int i = 0;i<TDrowCount;i++)
		{
			standardResult[i] = trainData[i][TDfiledCount-2];//获取所有记录的分类值结果
		}
		if(print)
		{
			PrintArray(standardResult);
		}
		return standardResult;	
	}

	//获取每种分类结果的总数
	double[] GetEveStandardNum(double[] standardResult,boolean print) throws SQLException
	{
		double [] eveStandardNum  = new double[3];//分类结果1的数目，分类结果2的数目，分类结果3的数目
		for(int i=0;i<TDrowCount;i++)
		{
			eveStandardNum[(int) standardResult[i]-1]++;
		}
		if(print)
		{
			PrintArray(eveStandardNum);
		}
		return eveStandardNum;
	}
	//获取评价标准数据	
	double[][] GetStandardData(String tableName,boolean print) throws SQLException
	{
		ArrayList<String> filedName = GetFiledName("real_train_data");//获取训练数据字段名
		filedName.remove("id");
		filedName.remove("standard");//去除id与级别字段
		//System.out.println(filedName.size()-1);//4
		//System.out.println(TDfiledCount-3);//3
//		System.out.println(filedName.get(0));
//		System.out.println(filedName.get(1));
//		System.out.println(filedName.get(2));
//		System.out.println(filedName.get(3));
		double [][] standardData= new double[filedName.size()-1][TDfiledCount-3];//存放评价标准数据
	    ResultSet rs = Select.Result("SELECT * FROM excel_input_standard where 污染物 = '"+ filedName.get(0).trim() +"' or 污染物 = '"+ filedName.get(1).trim() +"' or 污染物 = '"+ filedName.get(2).trim() +"' or 污染物 = '"+ filedName.get(3).trim() +"'");	    
		int num = 0;
		while(rs.next())
		{
			for(int i=2;i<filedName.size();i++)
			{
				try{
					standardData[num][i-2] = Double.valueOf(rs.getString(i+1));
				}
				catch(Exception e)
				{
					System.out.println("第"+ (num+1) +"个特征参数存在数据为空的字段");
				}
			}
			num++;
		}
		if(print)
		{
			PrintArray(standardData);
		}
		return standardData;
	}
	
	double[][][] GetConclusionTrainData(double[][] trainData,double[][] standardData,boolean print) throws SQLException//特征参数的种类，每个特征参数的不同分类，全部记录中每种分类结果的数目
	{
		double[][][] conclusionResult = new double[trainData[0].length-1][parStandardNum][recordStandardNum];//特征参数的种类，每个特征参数的不同分类，全部记录中每种分类结果的出现的次数
		for(int i = 0;i<trainData.length;i++)//记录数
		{
			for(int j = 0;j<trainData[i].length-1;j++)//特征参数种类数4
			{
					if(trainData[i][j]>standardData[j][0] && trainData[i][j]<=standardData[j][1])//大于第一标准小于第二标准
					{
						conclusionResult[j][0][(int) trainData[i][trainData[j].length-1]-1]++;//第j个参数，参数第一分类，某分类结果数目
					}
					else if(trainData[i][j]>standardData[j][1] && trainData[i][j]<=standardData[j][2])//大于第2标准小于第三标准
					{
						conclusionResult[j][1][(int) trainData[i][trainData[j].length-1]-1]++;//第j个参数，参数第二分类，某分类结果数目
					}
					else if(trainData[i][j]>standardData[j][2])//大于第3标准
					{
						conclusionResult[j][2][(int) trainData[i][trainData[i].length-1]-1]++;//第j个参数，参数第三分类，某分类结果数目
					}
			}
		}
	

		Fill1(conclusionResult);//对0进行填1处理
		if(print)//自定义详细打印
		{
			for(int i = 0;i<conclusionResult.length;i++)
			{
				for(int j = 0;j<3;j++)
				{
					for(int k = 0;k<3;k++)
					{
						System.out.print("分类结果为"+ (k+1) +"级的数目:");
						System.out.print(conclusionResult[i][j][k]);//第i个参数，参数的第j种分类，第k种分类结果的数目
						System.out.print("-");
					}
					System.out.println("---参数的第"+ (j+1) +"标准");
				}
				System.out.println("--第"+ (i+1) +"特征参数--");
			}
		}
		//System.out.print(trainData[0].length); 
//		if(print)
//		{
//			PrintArray(conclusionResult);//粗略打印
//		}

		return conclusionResult;		
	}
	double[] GetBeforeRatio(double[] eveStandardNum,boolean print)//获取前验概率
	{
		double [] beforeRatio = new double[eveStandardNum.length];
		double countStandardNum = 0;
		for(int i = 0;i<eveStandardNum.length;i++)
		{
			countStandardNum+=eveStandardNum[i];
		}
		for(int i = 0;i<eveStandardNum.length;i++)
		{
			beforeRatio[i]  = eveStandardNum[i]/countStandardNum;
		}
		if(print)
		{
			PrintArray(beforeRatio);
		}
		return beforeRatio;
	}
	//获取计算D元组分类所需期望信息
	double GetInfo_D(double[] beforeRatio)
	{
		double info_D = 0;
		for(int i = 0;i<beforeRatio.length;i++)
		{
			info_D += -(beforeRatio[i])*(Math.log(beforeRatio[i])/Math.log(2));//累加（负的前验概率乘以（log以2为底前验概率的对数））
		}
		//System.out.print(info_D);//计算D元组分类所需期望信息(位)
		return info_D;
	}
	//获取进行D元组分类每种特征参数参数所需要的期望信息(位)
	double[] GetEveInfo_D(double[][][] conclusionTrainData,double [] beforeRatio)
	{
		double[] eveInfo_D = new double[conclusionTrainData.length];//进行D元组分类每种特征参数参数所需要的期望信息(位)
		double[][] countEveParKind = new double[conclusionTrainData.length][parStandardNum];//每个特征参数，每个参数的不同分类出现的次数
		double [][][] eveParBeforeRatio = new double[conclusionTrainData.length][parStandardNum][recordStandardNum];//特征参数种类数，每个特征参数的不同分类情况，每个特征参数的不同分类情况下该记录不同分类的前验概率	
		for(int i = 0;i<conclusionTrainData.length;i++)
		{
			for(int j = 0;j<parStandardNum;j++)
			{
				for(int k = 0;k<recordStandardNum;k++)
				{
					countEveParKind[i][j] += conclusionTrainData[i][j][k];//每个特征参数，每个参数的分类出现的次数
				}
			}
		}

		//System.out.print(conclusionTrainData.length);//4
//		for(int i = 0;i<conclusionTrainData.length;i++)
//		{
//			System.out.println("第"+(i+1)+"个特征参数");
//			for(int j = 0;j<conclusionTrainData[i].length;j++)
//			{
//				System.out.print("该参数第"+(j+1)+"个分类出现的次数");
//				System.out.println(countEveParKind[i][j]);//每个特征参数，每个参数的分类出现的次数
//			}
//		}
		for(int i = 0;i<conclusionTrainData.length;i++)
		{
			for(int j = 0;j<conclusionTrainData[i].length;j++)
			{
				for(int k = 0;k<conclusionTrainData[i][j].length;k++)
				{
					eveParBeforeRatio[i][j][k] = conclusionTrainData[i][j][k]/countEveParKind[i][j];//每个特征参数，每个参数的分类的前验概率
				}
			}
		}
//		for(int i = 0;i<conclusionTrainData.length;i++)
//		{
//			System.out.println("第"+(i+1)+"个特征参数：");
//			for(int j = 0;j<conclusionTrainData[i].length;j++)
//			{
//				System.out.println("该参数第"+(j+1)+"个分类：");
//				for(int k = 0 ;k<conclusionTrainData[i][j].length;k++)
//				{			
//					System.out.print("该分类且为第"+ (k+1) +"种分类结果的前验概率：");
//					System.out.println(eveParBeforeRatio[i][j][k]);//每个特征参数，每个参数的分类的前验概率
//				}
//			}
//		}

		double[][] eveParRatio = new double [conclusionTrainData.length][parStandardNum];
		for(int i = 0 ;i<conclusionTrainData.length;i++)
		{
			for(int j = 0;j<conclusionTrainData[i].length;j++)
			{
				eveParRatio[i][j] = countEveParKind[i][j]/TDrowCount;//在训练集中，每个特征参数的每种分类出现的次数/总记录数，得出一个2维数组[22][3]，每个特征参数，每个特征参数的一个分类可能出现的次数比上总记录数
			}
		}
		//PrintArray(eveParRatio); 
		for(int i = 0;i<conclusionTrainData.length;i++)
		{
			for(int j = 0 ;j<parStandardNum;j++)
			{
				double[] temp = new double[conclusionTrainData.length]; 
				for(int k = 0;k<recordStandardNum;k++)
				{
					//每个特征参数进行D元组分类所需要的期望信息(位)
					//每个特征参数的每种分类的记录数/总记录数  22个特征参数每个特征参数有三个分类可能，每个可能的记录数/总记录数 得到一个2维数组，[22][3]
					//22个参数每个参数都需要求出其对应的期望，公式为：累加（之前的2维数组的[3]中每个比值*（累加（负的 （每个分类可能的前验概率*log以2为底前验概率的对数*前验概率）））//最终得到22个参数每个参数各自的分组期望是一个长度[22]的1维数组
					temp[i] += -(eveParBeforeRatio[i][j][k])*(Math.log(eveParBeforeRatio[i][j][k])/Math.log(2)); 
				}
				eveInfo_D[i] += eveParRatio[i][j]*temp[i];
			}
		}
		
		
//		for(int i = 0;i<conclusionTrainData.length;i++)
//		{
//			//每种参数的期望
//			System.out.print("期望："); 
//			System.out.println(eveInfo_D[i]); 
//		}
		return eveInfo_D;
	}
	int GetRootNode(double info_D,double[] eveInfo_D)//求根节点，即信息增益(Gain)最大的特征参数
	{
		double [] Gain = new double [eveInfo_D.length];//信息增益数组
		for(int i = 0;i<eveInfo_D.length;i++)
		{
			Gain[i] = info_D - eveInfo_D[i]; 
		}
		PrintArray(Gain);
		double maxGain = Gain[0];
		int rootNode = 0;
		for(int i = 0;i<eveInfo_D.length;i++)
		{
			if(Gain[i]>maxGain)
			{
				rootNode = i;
			}
		}
//		System.out.println("根节点值：");
//		System.out.println(rootNode);
		return rootNode;
		//第rootNode个特征参数作为根节点
	}
	void CreateTree(int rootNode,double[][] standardData)//创建决策树
	{
//		System.out.println("传入根节点值：");
//		System.out.println(rootNode);
//		System.out.println("根节点值分支：");
//		System.out.println(standardData[3][0]);
//		System.out.println(standardData[3][1]);
//		System.out.println(standardData[3][2]);
	}
	void GetPretreatmentTestData(int rootNode,double[][] testData,double[][] standardData)
	{
		String[][] pretreatmentTestData = new String[testData.length][3];
		for(int i = 0;i<testData.length;i++)
		{
			for(int j = 0;j<testData[i].length;j++)
			{
				if(testData[i][rootNode]>standardData[rootNode][0] && testData[i][rootNode]<=standardData[rootNode][1])
				{
					pretreatmentTestData[i][0] = rootNode+"_第一级别 ";//特征参数，第一级别
				}
				else if(testData[i][rootNode]>standardData[rootNode][1] && testData[i][rootNode]<=standardData[rootNode][2])
				{
					pretreatmentTestData[i][0] = rootNode+"_第二级别 ";//特征参数，第一级别
				}
				else if(testData[i][rootNode]>standardData[rootNode][2])
				{
					pretreatmentTestData[i][0] = rootNode+"_第三级别 ";//特征参数，第一级别
				}
			}
			
		}
	}
	void useTree(double[][] pretreatmentTestData)//传入戴分级测试数据
	{
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	void PrintArray(double []array1)
	{
		for(int i = 0;i<array1.length;i++)
		{
			System.out.print("[");
			System.out.print("\t");
			System.out.print(array1[i]);
			System.out.print("\t");
			System.out.print("]");
		}
	}
		
	
	void PrintArray(double [][] array2)
	{
		for(int i = 0;i<array2.length;i++)
		{
			System.out.print("[");
			for(int j = 0;j<array2[i].length;j++)
			{
				System.out.print("\t");
				System.out.print(array2[i][j]);
				System.out.print("\t");
			}
			System.out.print("]");
			System.out.println("");
		}
	}
	
	void PrintArray(double [][][] array3)
	{
		for(int i = 0;i<array3.length;i++)
		{
			System.out.println("---");
			for(int j = 0;j<array3[i].length;j++)
			{
				System.out.print("[");
				for(int k = 0;k<array3[i][j].length;k++)
				{
					System.out.print("\t");
					System.out.print(array3[i][j][k]);
					System.out.print("\t");
				}
				System.out.print("]");
				System.out.println("");
			}
			System.out.println("---");
		}
	}
	
	void Fill1(double [][][] array3)
	{
		for(int i = 0;i<array3.length;i++)
		{
		
			for(int j = 0;j<array3[i].length;j++)
			{
				for(int k = 0;k<array3[i][j].length;k++)
				{

					if(array3[i][j][k] == 0)
					{
						array3[i][j][k] = 1;
					}

				}

			}

		}
	}
	
	int GetFiledCount(String tableName)//获取字段条数
	{
		ResultSet rs = Select.Result("SELECT * FROM "+ tableName +"");
	    ResultSetMetaData rsmd = null;
		try {
			rsmd = (ResultSetMetaData) rs.getMetaData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("获取数据表"+ tableName +"元数据（数据表属性）出错");
		}//例化数据表信息对象
	    int filedCount = 0;
		try {
			filedCount = rsmd.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("获取数据表"+ tableName +"字段数目出错");
		}//获取字段列数
		return filedCount;
	}
	int GetRowCount(String tableName)//获取记录数目
	{
		ResultSet rs = null;
		rs = Select.Result("SELECT * FROM "+ tableName +"");
		try {
			rs.last();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("滚动查询的数据表结果集至末尾"+ tableName +"出错");
		} //游标移动到最后一行记录
		int rowCount = 0;
		try {
			rowCount = rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("获取数据表"+ tableName +"记录数目出错");
		} //获取总记录条数
		try {
			rs.beforeFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("滚动查询的数据表结果集至第一行之前"+ tableName +"出错");
		}//游标移动到第一行之前 
		return rowCount;
	}
	ArrayList <String> GetFiledName(String tableName) throws SQLException//获取全部字段名集合
	{
		ArrayList<String> filedName = new ArrayList<String>();
		ResultSet rs = Select.Result("SELECT * FROM "+ tableName +"");
	    ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();//例化数据表信息对象
	    int filedCount = rsmd.getColumnCount();//获取字段列数
	    for(int i = 0;i<filedCount;i++)
	    {
	    	filedName.add(rsmd.getColumnName(i+1)) ;
	    }
		return filedName;
	}	
	//打印判断,默认不打印
	double[][] GetTrainData(String tableName) throws SQLException
	{
		return GetTrainData(tableName,false);
	}
	double[][] GetTestData(double [][] trainData) throws SQLException
	{
		return GetTestData(trainData,false);
	}
	double[] GetStandardResult(double [][] trainData) throws SQLException
	{
		return GetStandardResult(trainData,false);
	}
	double[] GetEveStandardNum(double[] standardResult) throws SQLException
	{
		return GetEveStandardNum(standardResult,false);
	}
	double[][] GetStandardData(String tableName) throws SQLException
	{
		return GetStandardData(tableName,false);
	}
	double [][][] GetConclusionTrainData(double[][] trainData,double[][] standardData) throws SQLException
	{
		return GetConclusionTrainData(trainData,standardData,false);
	}
	double[] GetBeforeRatio(double[] eveStandardNum)
	{
		return GetBeforeRatio(eveStandardNum,false);	
	}
}
