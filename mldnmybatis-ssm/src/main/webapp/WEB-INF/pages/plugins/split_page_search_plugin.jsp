<%@ page pageEncoding="UTF-8"%>
<%--
组件引用：
	<jsp:include page="split_page_search_plugin.jsp"/>
传递属性：
	request.setAttribute("columnData",columnData) ;
	request.setAttribute("url",split_page_url) ;
--%>
<%	// 定义与分页有关的变量
	request.setCharacterEncoding("UTF-8") ;
	String split_page_url = request.getAttribute("basePath").toString() + request.getAttribute("url") ;
	// columnData数据保存格式：显示标签:列名称|显示标签:列名称
	String columnData = (String)  request.getAttribute("columnData") ;
	String column = request.getParameter("col") ; // 接收传递的col参数
	String keyWord = request.getParameter("kw") ; // 接收查询关键字
	if (keyWord == null) {
		keyWord = "" ; // 为空字符串
	}
	if (columnData == null) {
		columnData = "" ;
	}
%>
<div id="searchDiv">
	<form action="<%=split_page_url%>" method="post" class="form-horizontal" id="myForm">
		<div class="form-group" id="inputDiv">
			<%
				if (!"".equals(columnData)) { 
			%>
			<div class="col-md-4">
			<%
				String columnResults [] = columnData.split("\\|") ; // 拆分
			%>
				<select name="col" id="col" class="form-control">
					<option value="">====== 请选择查询模式 ======</option>
			<%
				for (int x = 0 ; x < columnResults.length ; x ++) {
					String columnTemp [] = columnResults[x].split(":") ;
			%>
					<option value="<%=columnTemp[1]%>" <%=columnTemp[1].equals(column)?"selected":""%>><%=columnTemp[0]%></option>
			<%
				}
			%>
				</select>
			</div> 
			<%
				} 
			%>
			<div class="col-md-6">
				<input type="text" id="kw" name="kw" class="form-control" value="<%=keyWord%>" placeholder="请输入检索关键字...">
			</div>
			<div class="col-md-2">
				<input type="submit" class="btn btn-primary" value="查询">
			</div>
		</div>
	</form>
</div>