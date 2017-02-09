<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page  import="java.sql.*" import="com.apache.MySql.*" %>
<%@ include file = "level_navbar_judge.jsp" %>

<div class="content">
<div class="btn-toolbar">
    <button onclick="input();" class="btn btn-primary"><i class="icon-save"></i> 录入</button>
    <button onclick="hjr_clear();" class="btn">清除</button>
  <div class="btn-group">
  </div>
</div>
<div class="well">
    <div id="myTabContent" class="tab-content">
      <div class="tab-pane active in" id="home">
    <form id="tab" action="OLStandardDataInput.do" method="post">
    	<label>数据表名</label>
        <input id="hjr_id_tablename" name="tablename" type="text" value="data_input_standard" class="input-xlarge">
        <label>污染物</label>
        <input id="hjr_id_parameter" name="parameter" type="text" value="" class="input-xlarge">
        <label>参数名称</label>
        <input id="hjr_id_level1" name="level1" type="text" value="" class="input-xlarge">
        <label>谓语动词</label>
        <input id="hjr_id_level2" name="level2" type="text" value="" class="input-xlarge">
        <label>产生原因</label>
        <input id="hjr_id_level3" name="level3" type="text" value="" class="input-xlarge">
         <label>解决方案</label>
        <input id="hjr_id_level3" name="level3" type="text" value="" class="input-xlarge">
    </form>
      </div>
  </div>
</div>
</div>
<script>
function input()
{
	$("#tab").submit();
	alert("标准数据录入成功！");
	window.open('data_input_standard.jsp','_parent','',false);
}
function hjr_clear()
{	
	$("#hjr_id_parameter").val("");
	$("#hjr_id_level1").val("");
	$("#hjr_id_level2").val("");
	$("#hjr_id_level3").val("");
}
</script>

<%@ include file = "level_foot_model.jsp" %> 
