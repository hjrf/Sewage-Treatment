<%@ page language="java" import="java.util.*"pageEncoding="UTF-8" %>
<%@ page  import="java.sql.*" import="com.apache.MySql.*" %>
<%@ include file = "level_navbar_judge.jsp" %>
<link href="css/table.css" rel="stylesheet" type="text/css"/>
<script language="javascript" src="js/table.js"></script>

<%
	String tableName = request.getParameter("tableName");  
	String userName = (String) request.getSession().getAttribute("userName");  
	ResultSet rs = null;
	rs = Select.Result("select * from user where 用户姓名 = '"+ userName +"'");
	//
	ResultSetMetaData rsmd = rs.getMetaData();
	int filedCount = rsmd.getColumnCount();

	String [] filedName = new String[filedCount];
	for(int i=0;i<filedCount;i++){
		filedName[i] = rsmd.getColumnName(i+1);
	}
	//
	rs.last(); 
	int rowCount = rs.getRow();  
	String[][] s = new String[rowCount][11];
	rs.beforeFirst(); 
	
	int record = 0;
	while(rs.next())
	{
		for(int i = 0;i<filedCount;i++)
		{
			s[record][i] = rs.getString(filedName[i]);				
		}
		record++;
	}
%>
<div class="content">
<table class="altrowstable" id="alternatecolor">
<tr>
	<th>id</th>
	<%for(int i = 1;i<filedCount;i++) {%>
	<th><%=filedName[i]%></th>
	<%}%>
</tr>
<%for(int i =0;i<rowCount;i++){%>
<tr>
	
	<td ><b><%=s[i][0]%></b></td>
	<%for(int j = 1;j<filedCount;j++){ %>
		<td id="<%=filedName[j]+'_'+(i+1)%>" class="hjr_td_readonly"><b><%=s[i][j]%></b></td>
	<%}%>
</tr>
<%}%>
</table>
 <%@ include file = "level_foot_model.jsp" %> 