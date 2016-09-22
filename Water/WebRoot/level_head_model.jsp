<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ZH">
<html>
<HEAD>
<title><%=session.getAttribute("userType")%></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    
    <link rel="stylesheet" type="text/css" href="css/theme.css">
    <link rel="stylesheet" href="WEB-INF/lib/font-awesome/css/font-awesome.css">

    <script src="js/jquery-1.12.2.js" type="text/javascript"></script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
      <div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">                  
                    <li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button">当前登录用户：</a></li>
                    <li id="fat-menu" class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> <%=session.getAttribute("userName") %>
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">个人中心</a></li>
                            <li class="divider visible-phone"></li>
                            <li><a tabindex="-1" href="log.jsp">注销</a></li>
                        </ul>
                    </li>                  
                </ul>
                <a class="brand" href="#"><span class="first">污水处理过程智能优化分析系统 --</span> <span class="second"><%=session.getAttribute("userType")%></span></a>
        </div>
    </div>