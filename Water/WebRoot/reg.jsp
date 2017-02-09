<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ZH">
<html>
<HEAD>
<TITLE>注册</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/reg.css" rel="stylesheet" type="text/css"/>
<script language="javascript" src="js/reg.js"></script>
</HEAD>
<body style = "background:url(images/bg_reg.png);background-size:100%;">
<div id="regnamediv">
    <b>注册名称：</b>
    <input id="regname" name="regname" type="text" />
    <div id="namediv">请输入用户名</div>
</div>

<div id="regpwddiv1">
    <b>注册密码：</b>
	<input id="regpwd1" name="regpwddiv1" type="password" />
    <div id="pwddiv1">请输入密码</div>
</div>	

<div id="regpwddiv2">
    <b>确认密码：</b>
	<input id="regpwd2" name="regpwddiv2" type="password" />
    <div id="pwddiv2">请确认密码</div>
</div>	

<div id="regemaildiv"><b>电子邮箱：</b>
      <input id="email" name="email" type="text" />
      <div id="emaildiv">用户激活和找回密码使用</div>
</div>
<div id = "regleveldiv">
	<b>普通级</b><input id = "level1" name = "level" type = "radio" value = "1"/> <b>管理级</b><input id = "level2" name = "level" type = "radio" value = "2"/> <b>专家级</b><input id = "level3" name = "level" type = "radio" value = "3"/>
	<div id="leveldiv">选择用户级别</div>
</div>
<div id="morediv" style="display:none;">
      <hr id="part" />
<!--      <div id="regquestiondiv"><b>密保问题：</b>-->
<!--        <input id="question" name="question" type="text" />-->
<!--        <div id="questiondiv">用户激活和找回密码使用</div>-->
<!--      </div>-->
<!--      <div id="reganswerdiv"><b>密保答案：</b>-->
<!--        <input id="answer" name="answer" type="text" />-->
<!--        <div id="answerdiv">用户激活和找回密码使用</div>-->
<!--      </div>-->
      <div id="regrealnamediv"><b>真实姓名：</b>
        <input id="realname" name="realname" type="text" />
        <div id="realnamediv">用户的真实姓名</div>
      </div>
      <div id="regtelephonediv"><b>联系电话：</b>
        <input id="telephone" name="telephone" type="text" />
        <div id="telephonediv">用户的联系电话</div>
      </div>
      <div id="regunitdiv"><b>所在单位：</b>
      	<input id="unit" name="unit" type="text" />
        <div id="unitdiv">用户所在单位</div>
      </div>
      <div id="regcardnumdiv"><b>证件号码：</b>
      	<input id="cardnum" name="card" type="text" />
        <div id="carddiv">用户证件号码</div>
      </div>
        <div id="regcardtypediv"><b>证件类型：</b>
      	<select id = "cardtype">  
		  <option value ="类型1">类型1</option>  
		  <option value ="类型2">类型2</option>  
		  <option value="类型3">类型3</option>  
		  <option value="类型4">类型4</option>  
		</select>  
        <div id="cardtypediv">用户证件类型</div>
      </div>
       <div id="regnotediv"><b>备注信息：</b>
      	<textarea id="note" name="note" rows="5" cols="30"></textarea>
        <div id="notediv">备注信息</div>
      </div>
    </div>
	<div id="btndiv2">
      <button id="regbtn" disabled="disabled">注册&nbsp;</button>
      <button id="resetbtn">重置&nbsp;</button>
      <button id="morebtn">选填&nbsp;</button>
      <button id="logbtn">登录&nbsp;</button>
     </div>
  </body>
</html>
