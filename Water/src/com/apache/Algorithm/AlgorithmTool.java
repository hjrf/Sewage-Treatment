package com.apache.Algorithm;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;

import com.apache.MySql.Select;
import com.mysql.jdbc.ResultSetMetaData;

public class AlgorithmTool extends HttpServlet{
	
	public static String trainTBname = "real_train_data";//训练集表名
	public static String standardTBname = "excel_input_standard";//特征参数分类评价标准表名
	public static String warnTBname = "real_train_data";//特征参数分类评价标准表名
	public static int parStandardNum = 3;//每个特征参数共有3种分类
	public static int recordStandardNum = 3;//每条记录有3种分类
	
	int TDfiledCount = GetFiledCount(trainTBname)-1;//-1是为了去除id字段
	
//获取训练数据
	public double [][] GetTrainData(String tableName,int rowCount,boolean print) throws SQLException
	{
		ResultSet rs = Select.Result("SELECT * FROM "+ trainTBname +" ORDER BY RAND() LIMIT "+ rowCount +"");
		double[][] trainData = new double[rowCount][TDfiledCount];	
		rs.beforeFirst();//游标移动到第一行之前 
		int num = 0;
		while(rs.next())
		{
			for(int i = 1;i<TDfiledCount+1;i++)//获取除id字段之外的所有数据
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
//获取每种分类结果数数目
	double [] GetEveParNum(double [][] trainData,boolean print)
	{
		double [] eveParNum = new double [recordStandardNum];//每条记录共有3中分类可能
		for(int i = 0;i<trainData.length;i++)
		{		
			if(trainData[i][trainData[i].length-1] == 1)//用temp存储三种分类结果的数目
			{
				eveParNum[0]++;
			}
			else if(trainData[i][trainData[i].length-1] == 2)//用temp存储三种分类结果的数目
			{
				eveParNum[1]++;
			}
			else if(trainData[i][trainData[i].length-1] == 3)//用temp存储三种分类结果的数目
			{
				eveParNum[2]++;
			}
		}
		if(print)
		{
			PrintArray(eveParNum);
		}
		return eveParNum;
		
	}
//前验概率
	public double [] GetBeforePro(double [][] trainData,double [] eveParNum,boolean print)
	{
		double[] beforePro = new double[recordStandardNum];
		for(int i = 0;i<recordStandardNum;i++)
		{
			beforePro[i] = eveParNum[i]/trainData.length;
		}
		if(print)
		{
			PrintArray(beforePro);
		}
		return beforePro;
	}
//均值
	double [][] GetAverage(double [][] trainData,double [] eveParNum,boolean print)
	{
		double [][] parValCount = new double[recordStandardNum][trainData[0].length-1];//记录的不同分类数，该分类下的所有记录中每个特征参数的值累加
		double [][] average = new double[recordStandardNum][trainData[0].length-1];//记录的不同分类数，该分类下每个特征参数的平均值
		for(int i = 0;i<trainData.length;i++)
		{
			if(trainData[i][trainData[i].length-1] == 1)
			{
				for(int j = 0;j<trainData[i].length-1;j++)
				{
					parValCount[0][j]+= trainData[i][j];
				}
			}
			else if(trainData[i][trainData[i].length-1] == 2)
			{
				for(int j = 0;j<trainData[i].length-1;j++)
				{
					parValCount[1][j]+= trainData[i][j];
				}
			}
			else if(trainData[i][trainData[i].length-1] == 3)
			{
				for(int j = 0;j<trainData[i].length-1;j++)
				{
					parValCount[2][j]+= trainData[i][j];
				}
			}
		}

		for(int i = 0;i<recordStandardNum;i++)
		{
			for(int j = 0;j<trainData[i].length-1;j++)
			{
				average[i][j] = parValCount[i][j]/eveParNum[i];
			}
		}
		if(print)
		{
			PrintArray(average);
		}
		return average;
	}
	//标准差
	double[][] GetStandardDev(double [][] trainData,double[][] average,boolean print)
	{
		double [][] standardDev = new double[recordStandardNum][trainData[0].length];
		double [][] temp = new double[recordStandardNum][trainData[0].length];
		for(int i = 0;i<recordStandardNum;i++)
		{
			for(int j = 0;j<trainData[0].length-1;j++)
			{
				for(int k = 0;k<trainData.length;k++)
				{
					temp[i][j] += Math.pow((trainData[k][j]-average[i][j]),2);//每种分类结果，每个特征参数
				}
				standardDev[i][j] = Math.sqrt(temp[i][j]/trainData.length);
			}		
		}
		if(print)
		{
			PrintArray(standardDev);
		}
		return standardDev;
	}
//条件概率
	public double[][][] GetConditionalPro(double [][] testData,double[][] average,double[][] standardDev,boolean print)
	{
		double[][][] conditionalPro = new double[testData.length][recordStandardNum][testData[0].length-1];
		for(int i = 0;i<testData.length;i++)
		{	
			for(int j = 0;j<recordStandardNum;j++)
			{
				for(int k = 0;k<testData[0].length-1;k++)
				{
			
					conditionalPro[i][j][k] = Math.pow((testData[i][k] - average[j][k]),2)/(2*Math.pow(standardDev[j][k],2));
					conditionalPro[i][j][k] = Math.exp(-conditionalPro[i][j][k]);
					conditionalPro[i][j][k]/=(Math.sqrt(2 * 3.1415926) * standardDev[j][k]);
				}
			}
		}
		if(print)
		{
			PrintArray(conditionalPro);
		}
		return conditionalPro;
	}
//后验概率
	public double[][] GetAfterPro(double[] beforePro,double[][][] conditionalPro,boolean print)
	{
		double[][] afterPro = new double[conditionalPro.length][recordStandardNum];
		Fill1(afterPro);
		for(int i = 0;i<conditionalPro.length;i++)
		{
			for(int j = 0;j<recordStandardNum;j++)
			{
				for(int k=0;k<conditionalPro[i][j].length;k++)
				{
					afterPro[i][j] *= conditionalPro[i][j][k]*beforePro[j];
				}
			}
		}
		if(print)
		{
			PrintArray(afterPro);
		}
		return afterPro;
	}
//bayes结果
	public double[] GetBayseResult(double[][] afterPro,boolean print)
	{
		double [] bayesResult = new double[afterPro.length];
		double [] temp = new double[recordStandardNum];
		for(int i = 0;i<afterPro.length;i++)
		{
			for(int j = 0;j<afterPro[i].length;j++)
			{
				temp[j] = afterPro[i][j];
			}
			bayesResult[i] = GetMaxIndex(temp)+1;//加1是因为获取的索引是从0开始的，而级别是从1开始的
		}
		if(print)
		{
			PrintArray(bayesResult);
		}
		return bayesResult;
	}

	
	//knn
	//全部欧氏距离
	public double[][][] GetAllODistance(double[][] trainData,double[][] testData,boolean print)
	{		
		double[][][] allODistance = new double[testData.length][trainData.length][2];//测试数据数目，每条测试数据有（训练数据条目数）的欧氏距离，【0】欧氏距离【1】该条欧氏距离对应的训练数据的水质级别
		for(int i = 0;i<testData.length;i++)
		{
			for(int j = 0;j<trainData.length;j++)
			{
				for(int k = 0;k<trainData[0].length-1;k++)
				{
					allODistance[i][j][0] += Math.pow((trainData[j][k] - testData[i][k]),2);
					//oDistance[i][j] = trainData[i][j]
				}													//每条测试数据的欧氏距离都有（训练数据条目数）条												//此处计算的是第j条测试数据的所有欧氏距离与该条欧式距离对应的训练数据的水质级别
				allODistance[i][j][0] = Math.sqrt(allODistance[i][j][0]);//将该条欧式距离的值插入[i][0]中
				allODistance[i][j][1] = trainData[j][trainData[j].length-1];//将该条欧氏距离对应的水质级别插入[i][1]中
			}
		}
		if(print)
		{
			PrintArray(allODistance);
		}
		return allODistance;
	}
	//获取每条测试数据的最小的前kValue条欧氏距离
	public double[][] GetMinODistance(double[][][] allODistance,int kValue,boolean print)
	{
		double[][] minODistance = new double[allODistance.length][kValue];//测试数据条目数，欧氏距离最小的前kValue条记录
		for(int i = 0;i<allODistance.length;i++)
		{
			for(int k=0;k<kValue;k++)//冒泡排序法只从小到大排序前kValue条从小到大排序
			{
				for(int g=k;g<allODistance[i].length;g++)
				{
					if(allODistance[i][g][0] < allODistance[i][k][0])
					{
						double temp = allODistance[i][k][0];
						allODistance[i][k][0] = allODistance[i][g][0];
						allODistance[i][g][0] = temp;//欧式距离排序
						double temp1 = allODistance[i][k][1];
						allODistance[i][k][1] = allODistance[i][g][1];
						allODistance[i][g][1] = temp1;//将水质级别与欧式距离一起排序
					}
				}
			}
			for(int g = 0;g<kValue;g++)
			{
				minODistance[i][g] =  allODistance[i][g][0];//将（测试数据条目数的数据）条数据中从小到大排序好的前kValue个数据存入数组
			}
		}	
		if(print)
		{
			PrintArray(minODistance);
		}
		return minODistance;	
	}
	//获取每条测试数据的最小的前kValue条欧氏距离对应的水质级别
	public double [][] GetMinODistance_Standard(double[][][] allODistance,int kValue,boolean print)
	{
		double[][] minODistance_Standard = new double[allODistance.length][kValue];//测试数据条目数，欧氏距离最小的kValue条记录对应的水质级别
		for(int i = 0;i<allODistance.length;i++)
		{
				for(int k=0;k<kValue;k++)//冒泡排序法只从小到大排序前kValue条从小到大排序
				{
					for(int g=k;g<allODistance[i].length;g++)
					{
						if(allODistance[i][g][0] < allODistance[i][k][0])
						{
							double temp = allODistance[i][k][0];
							allODistance[i][k][0] = allODistance[i][g][0];
							allODistance[i][g][0] = temp;//欧式距离排序
							double temp1 = allODistance[i][k][1];
							allODistance[i][k][1] = allODistance[i][g][1];
							allODistance[i][g][1] = temp1;//将水质级别与欧式距离一起排序
						}
					}
				}
				for(int g = 0;g<kValue;g++)
				{
					minODistance_Standard[i][g] =  allODistance[i][g][1];//将（测试数据条目数的数据）条数据中从小到大排序好的前kValue个数据存入数组
				}

		}	
		if(print)
		{
			PrintArray(minODistance_Standard);
		}
		return minODistance_Standard;	
	}
	//knn结果
	public double [] GetKnnResult(double[][] minODistance_Standard,boolean print)
	{
		double[] knnResult = new double[minODistance_Standard.length];
		
		for(int i = 0;i<minODistance_Standard.length;i++)
		{		
			for(int j = 0;j<minODistance_Standard[i].length;j++)
			{
				double[] temp = GetShwoMost(minODistance_Standard[i],3);
				PrintArray(temp);
				knnResult[i] = GetMaxIndex(temp)+1;
			}
		}
		if(print)
		{
			PrintArray(knnResult);
		}
		return knnResult;		
	}
	public double[] GetShwoMost(double[] array1,int max)//返回一个长度为max的数组，数组的每个元素值的大小代表该值在array1数组中出现的次数
	{
		double[] showMost = new double[max];
		for(int i = 0;i<array1.length;i++)
		{
			showMost[(int)array1[i]-1]++;
		}
		return showMost;
	}

	
	
	
	
	//knn结束
	
	
	
	
	public double[][] GetStandardData(String warnTBname,String standardTBname,boolean print) throws SQLException
	{
		ArrayList<String> filedName = GetFiledName(trainTBname,false);//获取训练数据字段名
		filedName.remove("id");
		filedName.remove("standard");//去除id与级别字段
		double [][] standardData= new double[filedName.size()-1][TDfiledCount-2];//存放评价标准数据
		String sql = "",sql1 = "",sql2 = "";
		sql1= " SELECT * FROM "+ standardTBname +" where ";
		for(int i = 0;i<filedName.size()-1;i++)
		{
			sql2 += " 污染物 = '"+ filedName.get(i).trim() +"' or";
		}
		sql2 = sql2.substring(0,sql2.length()-2);
		sql = sql1+sql2;
		//System.out.println(sql);
		
	    ResultSet rs = Select.Result(sql);	    
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
	
	public double[][] GetStandardData(String standardTBname,boolean print) throws SQLException
	{
	
		String sql= " SELECT * FROM "+ standardTBname;
	    ResultSet rs = Select.Result(sql);	    
		int num = 0;
		int filedCount = GetFiledCount(standardTBname);
		int rowCount = GetRowCount(standardTBname);
		double [][] standardData= new double[rowCount][filedCount-2];
		while(rs.next())
		{
			for(int i = 2;i<filedCount;i++)
			{
				standardData[num][i-2] = Double.valueOf(rs.getString(i+1));
			}
			num++;
		}
		if(print)
		{
			PrintArray(standardData);
		}
		return standardData;
	}
	
	public String[][] GetAllStandardData(String standardTBname) throws SQLException
	{
	
		String sql= " SELECT * FROM "+ standardTBname;
	    ResultSet rs = Select.Result(sql);	    
		int num = 0;
		int filedCount = GetFiledCount(standardTBname);
		int rowCount = GetRowCount(standardTBname);
		String [][] standardData= new String[rowCount][filedCount-1];
		while(rs.next())
		{
			for(int i = 1;i<filedCount;i++)
			{
				standardData[num][i-1] = String.valueOf(rs.getString(i+1));
			}
			num++;
		}
		return standardData;
	}
	
	public double GetRandom(double min,double max)
	{
		double rand = 0;
		rand = min+(max-min)*Math.random();
		BigDecimal b = new BigDecimal(rand);
		return  b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public double Get4decimal(double decimal)
	{
		BigDecimal b = new BigDecimal(decimal);
		return  b.setScale(4,   BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	int GetMaxIndex(double[] array1)
	{
		int maxIndex = 0;
		for(int i = 0;i<array1.length;i++)
		{
			if(array1[i] > array1[0])
			{
				array1[0] = array1[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}
	
	/**
	* 打印1维集合
	* <p><br>
	* @param filedName String[]类型的1维集合<br/>
	* @return null
	*/		
	void PrintArray(ArrayList<String>filedName)
	{
		for(int i = 0;i<filedName.size();i++)
		{
			System.out.print("[");
			System.out.print("\t");
			System.out.print(filedName.get(i));
			System.out.print("\t");
			System.out.print("]");
		}
		System.out.println("");
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
		System.out.println("");
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
	
	public void PrintArray(double [][][] array3)
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
	
	public void Fill1(double [][][] array3)
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
	
	
	public void Fill1(double [][] array2)
	{
		for(int i = 0;i<array2.length;i++)
		{
		
			for(int j = 0;j<array2[i].length;j++)
			{
				
				if(array2[i][j] == 0)
				{
					array2[i][j] = 1;
				}
			}

		}
	}
	

	public int GetFiledCount(String tableName)//获取字段条数
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
	
	public int GetRowCount(String tableName)//获取记录数目
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

	public ArrayList <String> GetFiledName(String tableName,boolean print) throws SQLException//获取全部字段名集合
	{
		ArrayList<String> filedName = new ArrayList<String>();
		ResultSet rs = Select.Result("SELECT * FROM "+ tableName +"");
	    ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();//例化数据表信息对象
	    int filedCount = rsmd.getColumnCount();//获取字段列数
	    for(int i = 0;i<filedCount;i++)
	    {
	    	filedName.add(rsmd.getColumnName(i+1)) ;
	    }
	    if(print)
	    {
	    	PrintArray(filedName);
	    }
		return filedName;
	}	
	

	//打印判断,默认不打印
	public double[][] GetTrainData(String tableName,int rowCount) throws SQLException
	{
		return GetTrainData(tableName,rowCount,false);
	}
	public double [] GetEveParNum(double [][] trainData)
	{
		return GetEveParNum(trainData,false);
	}
	public double[] GetBeforePro(double [][] trainData,double[] eveParNum)
	{
		return GetBeforePro(trainData,eveParNum,false);
	}
	public double [][] GetAverage(double [][] trainData,double [] eveParNum)
	{
		return GetAverage(trainData,eveParNum,false);	
	}
	public double[][] GetStandardDev(double [][] trainData,double[][] average)
	{
		return GetStandardDev(trainData,average,false);
	}
}