<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="level_head_model.jsp"%>

    <div class="sidebar-nav">
          <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>个人信息管理</a>
        <ul id="dashboard-menu" class="nav nav-list collapse in">
            <li><a href="table_user.jsp">信息查看</a></li>
            <li ><a href="user.jsp">信息编辑</a></li>
        </ul>

        <a href="#accounts-menu" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>污水数据上传<span class="label label-info">+3</span></a>
        <ul id="accounts-menu" class="nav nav-list collapse">
            <li ><a href="excel.jsp">Excel数据上传</a></li>
            <li ><a href="table_model.jsp?tableName=excel_input_standard">评价标准数据查看</a></li>
             <li ><a href="table_model.jsp?tableName=real_train_data">水质实际数据查看</a></li>
        </ul>

        <a href="#error-menu" class="nav-header collapsed" data-toggle="collapse"><i class="icon-exclamation-sign"></i>污水数据录入<i class="icon-chevron-up"></i></a>
        <ul id="error-menu" class="nav nav-list collapse">
            <li ><a href="data_input_standard.jsp">标准录入</a></li>
            <li ><a href="data_input_parameter.jsp">参数录入</a></li>
              <li ><a href="table_model.jsp?tableName=data_input_standard">录入标准查看</a></li>
            <li ><a href="table_model.jsp?tableName=data_input_parameter">录入参数查看</a></li>
        </ul>

        <a href="#legal-menu" class="nav-header" data-toggle="collapse"><i class="icon-legal"></i>用户操作模块</a>
        <ul id="legal-menu" class="nav nav-list collapse">
            <li ><a href="instrument.jsp">模拟仪表操作</a></li>
        </ul>
         <a href="#" class="nav-header" ><i class="icon-comment"></i>系统使用帮助</a>
    </div>
