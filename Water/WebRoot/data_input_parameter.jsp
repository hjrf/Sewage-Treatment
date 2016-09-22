<%@ page language="java" import="java.util.*"pageEncoding="UTF-8" %>
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
    <form id="tab" action="OLParaeterDataInput.do" method="post">
        <label>数据表名</label>
        <input id="hjr_id_tablename" name="tablename" type="text" value="data_input_parameter" class="input-xlarge">
        <label>参数名称</label>
        <input id="hjr_id_unit" name="parameter" type="text" value="" class="input-xlarge">
        <label>参数数值</label>
        <input id="hjr_id_telephone" name="value" type="text" value="" class="input-xlarge">
    </form>
      </div>
  </div>

</div>

</div>

<script>
function input()
{
	$("#tab").submit();
	alert("参数数据录入成功！");
	window.open('data_input_parameter.jsp','_parent','',false);
}
function hjr_clear()
{	
	$("#hjr_id_unit").val("");
	$("#hjr_id_telephone").val("");
}
</script>

<%@ include file = "level_foot_model.jsp" %> 
