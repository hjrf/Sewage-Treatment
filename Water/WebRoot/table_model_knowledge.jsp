<%@ page language="java" import="java.util.*"pageEncoding="UTF-8" %>
<%@ page  import="java.sql.*" import="com.apache.MySql.*" import="com.apache.Algorithm.*" import="java.lang.*" %>
<%@ include file = "level_navbar_judge.jsp" %>
<link href="css/table.css" rel="stylesheet" type="text/css"/>
<%
//获取数据表基本数据
	
	String tableName = request.getParameter("tableName");//获取url tableName参数的值  
	ResultSet rs = null;//声明结果集，表格数据
	ResultSet  rs1 = null;//声明结果集，专家规则
	rs = Select.Result("select * from "+ tableName +"");//查询所有记录
	//
	ResultSetMetaData rsmd = rs.getMetaData();//例化数据表信息对象
	int filedCount = rsmd.getColumnCount();//获取字段列数

	String [] filedName = new String[filedCount];//声明字段内容数组
	for(int i=0;i<filedCount;i++){
		filedName[i] = rsmd.getColumnName(i+1);//获取每一个字段名
	}
	//
	rs.last(); //游标移动到最后一行记录
	int rowCount = rs.getRow(); //获取总记录条数
	
	
//分页功能实现
	int pageCurr = 0;//当前页
	int pageSumCount = 0;//共有几页
	int pageOneCount = 5;//每页记录数
	pageSumCount = rowCount/pageOneCount;//计算应该分几页
	
	String temp = request.getParameter("pageCurr");//获取String型当前页数
	if(temp == null)
	{
		temp = "0";//如果没有pageCurr参数，默认为0
	}
	pageCurr = Integer.parseInt(temp);//String强制类型转换为int
	
	int pageLast = pageCurr-1;//上一页
	int pageNext = pageCurr+1;//下一页
	if(pageLast<0)
	{
		pageLast = 0;
	}
	if(pageNext>pageSumCount)
	{
	pageNext = pageSumCount;
	}
	int RecordLimitLower = pageCurr*pageOneCount;//每页查询的起始记录数
	String currURL = new String(request.getRequestURI());//获取url路径
	//if(request.getQueryString()!=null && !"".equals(request.getQueryString())){//如果参数不为空
	//System.out.println(request.getQueryString());参数
		//currURL.append("?" + request.getQueryString());//获取路径+参数后的url
		//System.out.println(currURL);
	//}
	
	rs = Select.Result("select * from "+ tableName +" limit "+RecordLimitLower+","+pageOneCount+"");//查询所有记录
	
	rs.last(); //游标移动到最后一行记录
	rowCount = rs.getRow(); //获取当前页记录条数
	
	
	 
	String[][] s = new String[rowCount][filedCount];//根据当前页的记录数与字段列数声明一个2维数组用来存放数据
	
	rs.beforeFirst();//游标移动到第一行之前 
	
	int record = 0;
	while(rs.next())
	{
		for(int i = 0;i<filedCount;i++)
		{
			s[record][i] = rs.getString(filedName[i]);//当前页数据表数据导入数组				
		}
		record++;//记录数自增
	}
%>
<div class="content">
<table class="altrowstable" id="alternatecolor">
<tr>
	<th>id</th>
	<%for(int i = 1;i<filedCount;i++) {%>
	<th><%=filedName[i]%></th>
	<%}%>
	<th>删除</th>
</tr>

<%for(int i =0;i<rowCount;i++){%>
<%//没更新一条表格数据都查询一次数据库
	System.out.println(s[i][1]);
	rs1 = Select.Result("select 产生原因,解决方案 from knowledge where 参数 = '"+s[i][1]+"' ORDER BY RAND() LIMIT 10");
	String [][] knowledge = new String[10][2];
	int num = 0;
	while(rs1.next())
	{
		for(int g = 0;g<2;g++)
		{
			knowledge[num][g] = rs1.getString(g+1);
		}
		num++;
	}
 %>
<tr>	
	<td ><b><%=s[i][0]%></b></td>
	<%for(int j = 1;j<filedCount;j++){ if(j<filedCount-2){%>
	
		<td id="<%=filedName[j]+'_'+(i+1)%>" class="hjr_td"><b><%=s[i][j]%></b></td>
	<%}else{%>
		<td id="<%=filedName[j]+'_'+(i+1)%>" class="hjr_td"><select>
		<%for(int k=0;k<10;k++) {%>
		<option><%=knowledge[k][j-(filedCount-2)]%></option>
		<%}%>
		</select></td>
	<%}}%>
	<td><button id="<%=i+1%>" class="hjr_delete">删除</button></td>
</tr>
<%}%>
</table>

<%
/*
StringBuffer url = new StringBuffer(request.getScheme() + "://" + request.getServerName()
    + request.getRequestURI());

System.out.println(request.getServerName());
System.out.println(request.getRequestURI());
if(request.getQueryString()!=null && !"".equals(request.getQueryString())){
System.out.println(request.getQueryString());
	url.append("?" + request.getQueryString());
}
System.out.println(url);



System.out.println("rotocol: " + request.getProtocol()); 
System.out.println("Scheme: " + request.getScheme()); 
System.out.println("Server Name: " + request.getServerName() ); 
System.out.println("Server Port: " + request.getServerPort()); 
System.out.println("rotocol: " + request.getProtocol()); 
System.out.println("Server Info: " + getServletConfig().getServletContext().getServerInfo()); 
System.out.println("Remote Addr: " + request.getRemoteAddr()); 
System.out.println("Remote Host: " + request.getRemoteHost()); 
System.out.println("Character Encoding: " + request.getCharacterEncoding()); 
System.out.println("Content Length: " + request.getContentLength()); 
System.out.println("Content Type: "+ request.getContentType()); 
System.out.println("Auth Type: " + request.getAuthType()); 
System.out.println("HTTP Method: " + request.getMethod()); 
System.out.println("path Info: " + request.getPathInfo()); 
System.out.println("path Trans: " + request.getPathTranslated()); 
System.out.println("Query String: " + request.getQueryString()); 
System.out.println("Remote User: " + request.getRemoteUser()); 
System.out.println("Session Id: " + request.getRequestedSessionId()); 
System.out.println("Request URI: " + request.getRequestURI()); 
System.out.println("Servlet Path: " + request.getServletPath()); 
System.out.println("Accept: " + request.getHeader("Accept")); 
System.out.println("Host: " + request.getHeader("Host")); 
System.out.println("Referer : " + request.getHeader("Referer")); 
System.out.println("Accept-Language : " + request.getHeader("Accept-Language")); 
System.out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding")); 
System.out.println("User-Agent : " + request.getHeader("User-Agent")); 
System.out.println("Connection : " + request.getHeader("Connection")); 
System.out.println("Cookie : " + request.getHeader("Cookie")); 
System.out.println("Created : " + session.getCreationTime()); 
System.out.println("LastAccessed : " + session.getLastAccessedTime());
*/

%>


<div class="pagination">
    <ul>
        <li><a href="<%=currURL+"?tableName="+tableName+"&pageCurr=0" %>">首页</a></li>
        <li><a href="<%=currURL+"?tableName="+tableName+"&pageCurr="+pageLast+"" %>">上一页</a></li>
        <li><a href="<%=currURL+"?tableName="+tableName+"&pageCurr="+pageNext+"" %>">下一页</a></li>
        <li><a href="<%=currURL+"?tableName="+tableName+"&pageCurr="+pageSumCount+"" %>">尾页</a></li>
        <li><a href="#">共<%=pageSumCount+1 %>页-当前第<%=pageCurr+1 %>页</li>
    </ul>
</div>


<%@ include file = "level_foot_model.jsp" %> 
