<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%if(session.getAttribute("userType").equals("普通用户")){%>
<%@ include file = "level1_navbar.jsp" %>
<%}else if(session.getAttribute("userType").equals("管理用户")){%>
<%@ include file = "level2_navbar.jsp" %>
<%}else if(session.getAttribute("userType").equals("专家用户")){%>
<%@ include file = "level3_navbar.jsp" %>
<%}%>