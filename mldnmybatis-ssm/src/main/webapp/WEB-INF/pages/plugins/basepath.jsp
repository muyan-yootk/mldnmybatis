<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() ;
	request.setAttribute("basePath", basePath) ; // 将BasePath的内容保存在application属性之中
%>
<base href="<%=basePath%>/"/>