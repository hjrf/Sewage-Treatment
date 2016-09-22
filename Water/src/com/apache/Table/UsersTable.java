package com.apache.Table;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apache.MySql.ExeSql;

public class UsersTable extends HttpServlet {
	ExeSql exeSql = new ExeSql(); 
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        response.setContentType("text/html");  
        response.setHeader("Cache-Control", "no-store");  
        response.setHeader("Pragma", "no-cache");  
        response.setDateHeader("Expires", 0); 
        String tableName = request.getParameter("tableName");
        String id = request.getParameter("id");
        String filed = request.getParameter("filed");
        String updata = request.getParameter("updata");
        
        String bt_id = request.getParameter("bt_id");
//        out.print(id+"_sucess");
//        out.print(updata+"_sucess");
//        out.print(filed+"_sucess");
	    try{
	    	 if(!id.equals(null))
	    	 {	    
	    		 exeSql.Do("UPDATE "+ tableName +" SET "+ filed +" = '"+ updata +"' WHERE id = "+id);
	    	 }
	    }
	    catch(Exception e)
	    {
	    	System.out.print("因为没有进行编辑，所以编辑传输数据为空！");
	    }

	    try{
		    if(!bt_id.equals(null))
		    {
		    	exeSql.Do("DELETE FROM "+ tableName +" WHERE id = "+bt_id);
		    }
	    }
	    catch(Exception e)
	    {
	    	System.out.print("因为没有点击删除按钮，所以删除按钮id为空！");
	    }
        
	}
    @Override  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
        processRequest(request, response);  
    }   
  
    @Override  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
        processRequest(request, response);  
    }  
}
