<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="level_head_model.jsp"%>
    
    <div class="sidebar-nav">
        <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>个人信息管理</a>
        <ul id="dashboard-menu" class="nav nav-list collapse in">
            <li><a href="table_user.jsp">信息查看</a></li>
            <li ><a href="user.jsp">信息编辑</a></li>            
        </ul>

        <a href="#accounts-menu" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>用户信息管理<span class="label label-info">+3</span></a>
        <ul id="accounts-menu" class="nav nav-list collapse">
            <li ><a href="table_model.jsp?tableName=user">信息编辑</a></li>
        </ul>

        <a href="#error-menu" class="nav-header collapsed" data-toggle="collapse"><i class="icon-exclamation-sign"></i>日志管理<i class="icon-chevron-up"></i></a>
        <ul id="error-menu" class="nav nav-list collapse">
            <li ><a href="table_model.jsp?tableName=userLogInf">用户登录日志</a></li>
            <li ><a href="table_model.jsp?tableName=doInf">仪器操作日志</a></li>
        </ul>
        <a href="#legal-menu" class="nav-header" data-toggle="collapse"><i class="icon-legal"></i>系统使用帮助</a>
    </div>