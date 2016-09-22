<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "level_navbar_judge.jsp" %>
<link href="css/xxx.css" rel="stylesheet" type="text/css"/>

    <div class="content"> 
    <form id="hjr_create"  action="DataTrainCreate.do" method=="get">
       <label>生成样本数</label>
	   <input id="dataNum" name="creatnum" type="text" value="10" class="input-xlarge">
    	<select name="level" id = "cardtype">  
		  <option  value ="1">生成数据</option>  
		  <option  value="2">清除数据</option> 
		</select>
		</br>	
	</form>
	<button onclick="create();" class="btn btn-primary">执行</button>
    </div>
    <script >
    function create()
    {
    	$("#hjr_create").submit();
    	alert("创建成功！");
    	window.open('data_train_create.jsp','_parent','',false);
    }
    </script>
<script language="javascript" src="js/xxx.js"></script>
 <%@ include file = "level_foot_model.jsp" %> 
    