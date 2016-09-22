<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file = "level_navbar_judge.jsp" %>
       
    <div class="content">
        
        <div class="header">
            
            <h1 class="page-title">用户列表</h1>
        </div>
        
                <ul class="breadcrumb">
            <li><a href="user.jsp">主页</a> <span class="divider">/</span></li>
            <li class="active">用户列表</li>
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
<div class="btn-toolbar">
    <button class="btn btn-primary"><i class="icon-plus"></i>添加用户</button>
    <button class="btn">编辑</button>
    <button class="btn">删除</button>
  <div class="btn-group">
  </div>
</div>
<div class="well">
    <table class="table">
      <thead>
        <tr>
          <th>#</th>
          <th>姓名</th>
          <th>联系电话</th>
          <th>邮箱</th>
          <th style="width: 26px;"></th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>1</td>
          <td>#</td>
          <td>#</td>
          <td>#</td>
          <td>
              <a href="#"><i class="icon-pencil"></i></a>
              <a href="#" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
          </td>
        </tr>
        <tr>
          <td>2</td>
          <td>#</td>
          <td>#</td>
          <td>#</td>
          <td>
              <a href="#"><i class="icon-pencil"></i></a>
              <a href="#" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
          </td>
        </tr>
        <tr>
          <td>3</td>
          <td>#</td>
          <td>#</td>
          <td>#</td>
          <td>
              <a href="#"><i class="icon-pencil"></i></a>
              <a href="#" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
          </td>
        </tr>
        <tr>
          <td>4</td>
          <td>#</td>
          <td>#</td>
          <td>#</td>
          <td>
              <a href="#"><i class="icon-pencil"></i></a>
              <a href="#" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
          </td>
        </tr>
        <tr>
          <td>5</td>
          <td>#</td>
          <td>#</td>
          <td>#</td>
          <td>
              <a href="#"><i class="icon-pencil"></i></a>
              <a href="#" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
          </td>
        </tr>
        <tr>
          <td>6</td>
          <td>#</td>
          <td>#</td>
          <td>#</td>
          <td>
              <a href="#"><i class="icon-pencil"></i></a>
              <a href="#" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
          </td>
        </tr>
      </tbody>
    </table>
</div>
<div class="pagination">
    <ul>
        <li><a href="#">Prev</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">Next</a></li>
    </ul>
</div>

<div class="modal small hide fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">Ã</button>
        <h3 id="myModalLabel">删除确认</h3>
    </div>
    <div class="modal-body">
        <p class="error-text"><i class="icon-warning-sign modal-icon"></i>确定删除该用户吗？</p>
    </div>
    <div class="modal-footer">
        <button class="btn" data-dismiss="modal" aria-hidden="true">退出</button>
        <button class="btn btn-danger" data-dismiss="modal">删除</button>
    </div>
</div>

 <%@ include file = "level_foot_model.jsp" %> 


