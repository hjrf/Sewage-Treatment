<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page  import="java.sql.*" import="com.apache.MySql.*" %>
<%@ include file = "level_navbar_judge.jsp" %>
<style>
 .progressBar{width:200px;height:8px;border:1px solid #98AFB7;border-radius:5px;margin-top:10px;}
 #bar{width:0px;height:8px;border-radius:5px;background:#5EC4EA;}
</style>

<script type="text/javascript">
 function progressBar(){
  //初始化js进度条
  $("#bar").css("width","0px");
  //进度条的速度，越小越快
  var speed = 20;

  bar = setInterval(function(){
   nowWidth = parseInt($("#bar").width());
   //宽度要不能大于进度条的总宽度
   if(nowWidth<=200){
    barWidth = (nowWidth + 1)+"px";
    $("#bar").css("width",barWidth);
   }else{
    //进度条读满后，停止
    clearInterval(bar);
   } 
  },speed);
 }
 
 
 $(function(){

	var currentValue = $('#currentValue');

	$('#defaultSlider').change(function(){
	    currentValue.html(this.value);
	});

	// Trigger the event on load, so
	// the value field is populated:

	$('#defaultSlider').change();

});
 
 
 
 
 
</script>

<div class="content" position="absolute""style="letf:100px;">

<div class="well">
<input type="button" value="设备启动" onclick="progressBar()" />
 <div class="progressBar"><div id="bar"></div></div>
 <form id="hjr_form" action="doInf.do" method="get">
<label>风机调节量</label>
<input name="adjustValue" id="defaultSlider" type="range" min="0" max="500" /> 
 		   <label>NH4OH投放量&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A级生物池</label>
	       <input id="hjr_id_1" name="yao1" type="text" value="10" >
	        <input id="hjr_id_1" name="yao1" type="text" value="10" >
	       <label>醋酸投放量&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;O级生物池</label>
	       <input id="hjr_id_2" name="yao2" type="text" value="10" > 
	           <input id="hjr_id_1" name="yao1" type="text" value="10" >
	        <label>H3PO4投放量&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;O级生物池</label>
	       <input id="hjr_id_3" name="yao3" type="text" value="10" >
	           <input id="hjr_id_1" name="yao1" type="text" value="10" >
     
	       </br>
	         <label>注水量：</label>
	         
		  <input name="waterValue" id="defaultSlider" type="range" min="0" max="500" />  </br>
	      <select name = "waterMen">  
		  <option value ="1">开启水闸1</option>  
		  <option value ="2">开启水闸2</option>  
		  <option value="3">开启水闸3</option>  
		  <option value="4">开启水闸4</option> 
		  </select>  
	</form>
	<button onclick="hjr_submit();" class="btn btn-primary"><i class="icon-save"></i>开始调控</button>
</div> 

<script>
function hjr_submit()
{	
$("#hjr_form").submit();
alert("调控成功！");
window.open('instrument.jsp','_parent','',false);
}
</script>
</script>
<%@ include file = "level_foot_model.jsp" %> 