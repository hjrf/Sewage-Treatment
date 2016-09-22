<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "level_navbar_judge.jsp" %>
<link href="css/xxx.css" rel="stylesheet" type="text/css"/>

    <div class="content"> 
	    <form id="hjr_form" action="xxx.do" method="get">
	       <label>xxx</label>
	       <input id="hjr_id_xx" name="hjr_name_xx" type="text" value="xxx" class="input-xlarge">
	       <button onclick="hjr_submit();" class="btn btn-primary"><i class="icon-save"></i>提交</button>
	    </form>
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
    