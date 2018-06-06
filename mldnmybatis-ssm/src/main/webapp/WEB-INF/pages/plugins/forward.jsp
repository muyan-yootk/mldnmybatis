<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--
	<jsp:include page="/pages/plugins/forward.jsp">
		<jsp:param name="msg" value="<%=msg%>"/>
		<jsp:param name="url" value="<%=member_add_url%>"/>
	</jsp:include>
--%>
<div>
	<p><img src="images/dandan.gif"></p>
	<h1><%=request.getParameter("msg")%></h1>
	<h1><span id="jumpTime">3</span>秒后进行跳转，如果没有跳转请按<a href="<%=request.getParameter("url")%>">这里</a>！</h1>
</div>