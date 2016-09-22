<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.apache.Check.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ZH">
<html>
<HEAD>
<title>用户登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/reg.css" rel="stylesheet" type="text/css"/>
</HEAD>
<body style = "background:url(images/bg_log.png);background-size:100%;">
    <div id="txtdiv1">
	   <b>登录名称：</b>
       <input id="logname" name="lgname" type="text"/>
	   <div id="namediv">请输入用户名</div>
    </div>
	
    <div id="txtdiv2">
	  <b>登录密码：</b>
      <input id="logpwd" name="lgpwd" type="password" />
	  <div id="pwddiv1">请输入密码</div>
    </div>
	<div id = "logleveldiv">
	<b>普通级</b><input id = "level1" name = "level" type = "radio" value = "1"/> <b>管理级</b><input id = "level2" name = "level" type = "radio" value = "2"/> <b>专家级</b><input id = "level3" name = "level" type = "radio" value = "3"/>
	<div id="leveldiv1">选择用户级别</div>
	</div>
	  <div id="txtdiv3">
	  <b>验证码：</b>
      <input id="lgchk" type="text" maxlength="4" style="width:60px;">
      <input type = "button" id="code"  onclick="createCode()">
	</div>
	
    <div id="btndiv">
      <button id="lgbtn">开始登录&nbsp;</button>
      <button id="rgbtn">返回注册&nbsp;</button>
      <input id="chknm" name="chknm" type="hidden" value=""/>
    </div>
    <div id="regimg" style="visibility: hidden;">&nbsp;</div>
 
 <script language="javascript" src="js/log.js"></script>
 <script>
 		$('rgbtn').onclick = function(){
		window.open('reg.jsp','_parent','',false);
	}
	
		$('lgbtn').onclick = function(){
		if($('logname').value == ''){
			alert("请输入用户名！");
			$('logname').focus();
			return false;
		}
		else if($('logpwd').value == ''){
			alert('请输入密码!');
			$('logpwd').focus();
			return false;
		}
		else if(!check_radio()){
		alert("请先选择用户级别!");
		return false;
		}
		else if(!validate()){
			alert("请输入正确验证码！"); //则弹出验证码输入错误  ;
			return false;
		}
		
		var xmlhttp = null;
	    if(window.XMLHttpRequest){
	        xmlhttp = new XMLHttpRequest();
	    }else{
	        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
		url = 'LogCheck.do?name='+$('logname').value+'&pwd='+$('logpwd').value+'&level='+level;
		xmlhttp.open('get',url,true);	
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState == 4){
				if(xmlhttp.status == 200){
					msg = xmlhttp.responseText;
					if(msg == '1'){
						alert('该用户不存在！');
					}
					else if(msg == '2'){
						alert('登录成功！');
						window.open('user.jsp','_parent','',false);
					}
					else if(msg == '3'){
						alert('密码输入错误！');
					}
					else if(msg == '4'){
						alert('没有权限！');
					}
					else{
						alert(msg);
					}
				}
			}
		}
		xmlhttp.send(null);
	}
 </script>
</body>
</html>