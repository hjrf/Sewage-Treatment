<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "level_navbar_judge.jsp" %>

<div class="content">      
    <div class="header">
    	<h1 class="page-title">用户编辑</h1>
    </div>
            <ul class="breadcrumb">
            <li><a href="user.jsp">主页</a> <span class="divider">/</span></li>
            <li class="active">用户信息</li>
        	</ul>

        <div class="container-fluid">
        <div class="row-fluid">
                    
	<div class="btn-toolbar">
	    <button onclick="save();" class="btn btn-primary"><i class="icon-save"></i> 保存</button>
	    <button onclick="hjr_clear();" class="btn">清除</button>
	  <div class="btn-group">
	  </div>
	</div>
	<div class="well">
    <ul class="nav nav-tabs">
      <li class="active"><a href="#home" data-toggle="tab">信息编辑</a></li>
      <li><a href="#profile" data-toggle="tab">密码编辑</a></li>
    </ul>
	    <div id="myTabContent" class="tab-content">
		      <div class="tab-pane active in" id="home">
				    <form id="tab" action="UpdataInf.do" method="post">
				        <label>证件号码</label>
				        <input id="hjr_id_cardnum" name="cardnum" type="text" value="" class="input-xlarge">
				        <label>所在单位</label>
				        <input id="hjr_id_unit" name="unit" type="text" value="" class="input-xlarge">
				        <label>联系电话</label>
				        <input id="hjr_id_telephone" name="telephone" type="text" value="" class="input-xlarge">
				        <label>电子邮箱</label>
				        <input id="hjr_id_email" name="email" type="text" value="@qq.com" class="input-xlarge">
				        <label>备注信息</label>
				        <textarea id="hjr_id_note" name="note" value="" rows="3" class="input-xlarge">
				        </textarea>
				    </form>
		      </div>
		      <div class="tab-pane fade" id="profile">
				    <form id="tab2" action="PwdChange.do" method="post">
				        <label>新的密码</label>
				        <input name="pwd" type="password" class="input-xlarge">
				    </form>
					<button onclick="hjr_updata();" class="btn btn-primary">更新</button>
		      </div>
	    </div>

	</div>
</div>

<script language="javascript" src="js/user.js"></script>
 <%@ include file = "level_foot_model.jsp" %> 


