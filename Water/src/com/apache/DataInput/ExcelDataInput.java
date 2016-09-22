package com.apache.DataInput;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.apache.MySql.ExeSql;





public class ExcelDataInput extends HttpServlet  {
	String excelPach = "";
	String way = "";
	String temp_filed = "temp_filed";
	String excel_alldata_name ="excel_alldata";
	ExeSql exeSql = new ExeSql(); 
	PrintWriter out = null;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		out = response.getWriter();  
        response.setHeader("Cache-Control", "no-store");  
        response.setHeader("Pragma", "no-cache");  
        response.setDateHeader("Expires", 0); 
		excelPach = request.getParameter("file");
		excel_alldata_name = request.getParameter("tbName");

		System.out.println(excelPach);
		
		exeSql.Do("DROP TABLE IF EXISTS "+temp_filed+"");
		exeSql.Do("create table "+temp_filed+" (id int primary key not null auto_increment,filedname varchar(30))");
		ReadExcel();
		out.print("100");
		}
		public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doGet(request, response);
	}
	

	public void ReadExcel()
	{
		Workbook workbook = null;
		Sheet sheet = null;
		Cell cell = null;//单元格
	
		try{
			workbook = Workbook.getWorkbook(new File(excelPach));
			}catch (Exception e) {
				System.out.println("文件无法打开");
		     return;
		    }
		try{
			sheet = workbook.getSheet(0);
			}catch(Exception e){
			System.out.println("读取表出错");
			}
		int columnCount=sheet.getColumns();
		int rowCount=sheet.getRows();
		
		String sql = null,sql1 = null,sql2 = "",sql3 = null,sqll2 = "";

		String[][] data = new String[rowCount-1][columnCount]; 
		for (int i = 0; i <rowCount; i++) {
		     for (int j = 0; j <columnCount; j++) {
		    	 if(i==0)
		    	 {
		    		 cell=sheet.getCell(j, i);  
		    		 String result = cell.getContents();
					 System.out.println(result);
					 exeSql.Do("insert into "+temp_filed+" values(filedname,'"+ result +"')");
					 
					 if(excel_alldata_name.equals("knowledge"))
					 {
						 sql2 += " ,"+result+" varchar(250) ";
					 }
					 else
					 {
						 sql2 += " ,"+result+" varchar(30) ";
					 }
		 			 sqll2 +=""+result+",";
		    	 }


		    	 else
		    	 {
		    		 cell=sheet.getCell(j, i); 
		    		 String result = cell.getContents();
//		    		 if(result.equals(""))
//		    		 {
//		    			result = null;
//		    		 }
					 System.out.println(result);
					 data[i-1][j] = result;
		    	 }
		    }
		}  
		 sql1 = "create table "+excel_alldata_name+" (id int primary key not null auto_increment ";
		 sql3 = " )";
	     sql =sql1 + sql2 + sql3;
	     exeSql.Do("DROP TABLE IF EXISTS "+excel_alldata_name+""); 
	     exeSql.Do(sql);
		 
		 
		 
		 //插入数据
		 String sqll = null,sqll1 = null,sqll3 = null,sqll4 = "",sqll5 = null;
		 sqll1 = "insert into "+ excel_alldata_name +"(";
		 sqll2 = sqll2.substring(0,sqll2.length()-1);	
		 sqll3 = ") values(";
	     sqll5 = ")";
		 for(int i = 0;i<rowCount-1;i++)
		 {
			sqll4 = "";
			for(int j =0;j<columnCount;j++)
			{
				sqll4 += "'"+data[i][j]+"',";
			}
			sqll4 = sqll4.substring(0,sqll4.length()-1);	
			sqll = sqll1 + sqll2 + sqll3 + sqll4 + sqll5;
			System.out.println(sqll);
			exeSql.Do(sqll);		
		 }

		
		 System.out.println(sql);
		 workbook.close();
	}

}
