<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "level_navbar_judge.jsp" %>
<link href="css/xxx.css" rel="stylesheet" type="text/css"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println(basePath);
%>
    <div class="content"> 
   <img src="images/LineChart.png"/>
	   </br>
	   红色曲线：PH值
	   </br>
	   	黄色曲线：溶解氧
	   	</br>
	   蓝色曲线：高锰酸盐指数
	   </br>
	 绿色曲线：氨氮
    </div>


<script>
function hjr_submit()
{
	$("#hjr_form").submit();
	//alert('xxx成功');
}
</script>   
<script language="javascript" src="js/xxx.js"></script>
 <%@ include file = "level_foot_model.jsp" %> 
    