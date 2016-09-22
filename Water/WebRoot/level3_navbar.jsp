<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="level_head_model.jsp"%>
    <div class="sidebar-nav">
        <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>个人信息管理</a>
        <ul id="dashboard-menu" class="nav nav-list collapse in">
            <li><a href="table_user.jsp">信息查看</a></li>
            <li ><a href="user.jsp">信息编辑</a></li>
        </ul>

        <a href="#accounts-menu" class="nav-header" data-toggle="collapse"><i class="icon-briefcase"></i>污水数据分析<span class="label label-info">+3</span></a>
        <ul id="accounts-menu" class="nav nav-list collapse">
            <li ><a href="excel.jsp?tableName=excel_input_standard">数据上传与下载</a></li>
            <li ><a href="table_model.jsp?tableName=excel_input_standard">水质评价标准</a></li>
            <li ><a href="table_model.jsp?tableName=real_train_data">污水实际数据</a></li>
            <li ><a href="data_train_create.jsp">训练数据生成</a></li>
            <li ><a href="table_model.jsp?tableName=data_test_create">生成数据编辑</a></li>
            <li ><a href="id3.jsp">污水级别预测（bayes训练）</a></li>
            <li ><a href="knn.jsp">污水级别预测（knn训练）</a></li>
        </ul>

        <a href="#error-menu" class="nav-header collapsed" data-toggle="collapse"><i class="icon-exclamation-sign"></i>污水数据优化<i class="icon-chevron-up"></i></a>
        <ul id="error-menu" class="nav nav-list collapse">
          	<li ><a href="table_model.jsp?tableName=bayes_result">预测结果查看（bayes）</a></li>
          	<li ><a href="table_model.jsp?tableName=knn_result">预测结果查看（knn）</a></li>
          	<li ><a href="line_chart.jsp">数据趋势图查看</a></li>
            <li ><a href="table_model_white.jsp?tableName=data_test_create">生成信息报警</a></li>
            <li ><a href="table_model_white.jsp?tableName=real_train_data">实际信息报警</a></li>
           	<li ><a href="table_model_knowledge.jsp?tableName=data_exception">专家规则查看</a></li>
           	 <li ><a href="instrument.jsp">模拟仪表操作</a></li>
        </ul>

        <a href="#legal-menu" class="nav-header" data-toggle="collapse"><i class="icon-legal"></i>系统使用帮助</a>
    </div>