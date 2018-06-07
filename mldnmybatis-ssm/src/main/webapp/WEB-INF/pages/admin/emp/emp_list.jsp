<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>雇员信息管理</title>
<jsp:include page="/WEB-INF/pages/plugins/basepath.jsp"/> 
<jsp:include page="/WEB-INF/pages/plugins/common.jsp"/> 
<script type="text/javascript" src="js/admin/emp/emp_list.js"></script>
</head>
<body>
<%
	String emp_add_url = "pages/admin/emp/emp_add_pre.action" ;
	String emp_edit_url = "pages/admin/emp/emp_edit_pre.action" ;
%>
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<strong>雇员信息列表</strong>
			</div>
			<div class="panel-body">
				<jsp:include page="/WEB-INF/pages/plugins/split_page_search_plugin.jsp"/>
				<button type="button" id="deleteBut" class="btn btn-danger btn-lg">删除雇员信息</button>
				<a href="<%=emp_add_url%>" class="btn btn-lg btn-primary">增加雇员</a>
				<table class="table table-condensed">
					<thead>
						<tr>
							<td><input type="checkbox" id="sabut"/></td>
							<td><strong>照片</strong></td>
							<td><strong>编号</strong></td>
							<td><strong>姓名</strong></td>
							<td><strong>工资</strong></td>
							<td><strong>职位</strong></td>
							<td><strong>操作</strong></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${allEmps}" var="emp">
						<tr>
							<td><input type="checkbox" id="empno" value="${emp.empno}:${emp.photo}"></td>
							<td>${emp.empno}</td>
							<td><img src="upload/emp/${emp.photo}" style="width:50px;"></td>
							<td>${emp.name}</td>
							<td>${emp.salary}</td>
							<td>${emp.job}</td>
							<td><a href="<%=emp_edit_url%>?empno=${emp.empno}" class="btn btn-warning btn-sm">修改</a></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<jsp:include page="/WEB-INF/pages/plugins/split_page_bar_plugin.jsp"/>
			</div>
			<div class="panel-footer">
				<strong>www.mldn.cn</strong>
			</div>
		</div>
	</div>
</div>
</body>
</html>