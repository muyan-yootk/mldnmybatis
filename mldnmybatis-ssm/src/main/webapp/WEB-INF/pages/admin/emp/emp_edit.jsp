<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>雇员信息管理案例</title>
<jsp:include page="/WEB-INF/pages/plugins/basepath.jsp"/>
<jsp:include page="/WEB-INF/pages/plugins/common.jsp"/> 
<script type="text/javascript" src="js/admin/emp/emp_edit.js"></script>
</head>
<body>
<%
	String emp_edit_url = "pages/admin/emp/emp_edit.action";
	String emp_list_url = "pages/admin/emp/emp_list.action";
%>
<div class="row">
	<div class="col-md-12">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<strong>编辑雇员信息</strong>
			</div>
			<div class="panel-body">
				<form action="<%=emp_edit_url%>" method="post" class="form-horizontal" id="myForm" enctype="multipart/form-data">
					<fieldset>
						<div class="form-group" id="empnoDiv"> 
							<label class="col-md-3 control-label">编号：</label>
							<div class="col-md-6">${emp.empno}</div>
							<div class="col-md-3">
								<span id="empnoMsg"></span>
							</div>
						</div>
						<div class="form-group" id="nameDiv"> 
							<label class="col-md-3 control-label">姓名：</label>
							<div class="col-md-6">
								<input type="text" id="name" name="name" value="${emp.name}" class="form-control">
							</div>
							<div class="col-md-3">
								<span id="nameMsg"></span>
							</div>
						</div>
						<div class="form-group" id="jobDiv"> 
							<label class="col-md-3 control-label">职位：</label>
							<div class="col-md-6">
								<input type="text" id="job" name="job" value="${emp.job}" class="form-control">
							</div>
							<div class="col-md-3">
								<span id="jobMsg"></span>
							</div>
						</div>
						<div class="form-group" id="salaryDiv"> 
							<label class="col-md-3 control-label">工资：</label>
							<div class="col-md-6">
								<input type="text" id="salary" name="salary" value="${emp.salary}" class="form-control">
							</div>
							<div class="col-md-3">
								<span id="salaryMsg"></span>
							</div>
						</div>
						<div class="form-group" id="picDiv"> 
							<label class="col-md-3 control-label">照片：</label>
							<div class="col-md-6">
								<img src="upload/emp/${emp.photo}" style="width:200px;">
								<input type="file" id="pic" name="pic" class="form-control">
							</div>
							<div class="col-md-3">
								<span id="picMsg"></span>
							</div>
						</div>
						<div class="form-group" id="buttonDiv">
							<div class="col-md-6 col-md-push-3">
								<input type="hidden" id="empno" name="empno" value="${emp.empno }">
								<input type="hidden" name="photo" value="${emp.photo}">
								<input type="submit" id="submitBut" class="btn btn-primary" value="增加">
								<input type="reset" id="resetBut" class="btn btn-danger" value="重置">
								<a href="<%=emp_list_url%>" class="btn-link">雇员列表</a>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
			<div class="panel-footer">
				<strong>www.mldn.cn</strong>
			</div>
		</div>
	</div>
</div>
</body>
</html>