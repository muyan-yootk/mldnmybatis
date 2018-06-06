<%@ page pageEncoding="UTF-8"%>
<%--
	组件引用：
		<jsp:include page="split_page_bar_plugin.jsp"/>
	在使用处要传递如下的几个request属性：
		request.setAttribute("url",split_page_url) ;
		request.setAttribute("allRecorders",allRecorders) ;
--%>
<%	// 定义与分页有关的变量
	request.setCharacterEncoding("UTF-8") ;
	String split_page_url = request.getAttribute("basePath").toString() + request.getAttribute("url") ;
	long currentPage = 1 ; 		// 当前所在页
	int lineSize = 5 ;  // 每页显示的数据行
	long allRecorders = 0 ; // 总记录数，需要通过程序统计完成
	long pageSize = 1 ; // 总页数，需要通过程序计算得到
	String column = request.getParameter("col") ; // 接收传递的col参数
	String keyWord = request.getParameter("kw") ; // 接收查询关键字
%>
<%	// 处理分页参数
	try {
		currentPage = Long.parseLong(request.getParameter("cp")) ;
	} catch (Exception e) {}	// 该异常没有必要输出
	try {
		lineSize = Integer.parseInt(request.getParameter("ls")) ;
	} catch (Exception e) {}	// 该异常没有必要输出
	if (keyWord == null) {
		keyWord = "" ; // 为空字符串
	}
	try {
		allRecorders = (Long) request.getAttribute("allRecorders") ;
	} catch (Exception e) {}	// 该异常没有必要输出
	if (allRecorders > 0) {	// 现在有记录返回
		pageSize = (allRecorders + lineSize - 1) / lineSize ;
	}
	if (pageSize == 0) {
		pageSize = 1 ; // 页数为1
	}
	if (column == null) {
		column = "" ; // 为空字符串
	}
%>
<div id="splitPageDiv" style="float:right">
	<ul class="pagination"> 
		<li class="<%=currentPage == 1 ? "active" : ""%>"><a href="<%=split_page_url%>?cp=<%=1%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyWord%>">1</a></li>
	<%
		if (pageSize > 1) {
		long seed = 3 ; // 设置一个种子数
		if (pageSize <= seed * 2 ) {
			for (int x = 2 ; x < pageSize ; x ++) {
	%>
			<li class="<%=currentPage == x ? "active" : ""%>"><a href="<%=split_page_url%>?cp=<%=x%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyWord%>"><%=x%></a></li>
	<%
			}
		} else {	// 如果页码超过了范围
			if (currentPage < seed * 2 - 1) {	// 当前页的内容并没有超过范围
				for (int x = 2 ; x <= seed * 2 ; x ++) {
	%>
				<li class="<%=currentPage == x ? "active" : ""%>"><a href="<%=split_page_url%>?cp=<%=x%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyWord%>"><%=x%></a></li>
	<%
				}
			} else {
				if (currentPage > seed * 2 - 1) {
	%>
				<li class="disabled"><span>...</span></li>
	<%
				} 
				long startPage = currentPage - seed ;
				long endPage = currentPage + seed ;
				if (endPage >= pageSize) {	// 最后一页，加上种子数一定超过总页数
					endPage = pageSize - 1 ;
				}
				for (long x = startPage ; x <= endPage ; x ++) {
	%>
					<li class="<%=currentPage == x ? "active" : ""%>"><a href="<%=split_page_url%>?cp=<%=x%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyWord%>"><%=x%></a></li>
	<%
				}
			}
		}
		if ((currentPage + seed + 1) < pageSize) {
	%>
			<li class="disabled"><span>...</span></li>
	<%
		}
	%>
	<li class="<%=currentPage == pageSize ? "active" : ""%>"><a href="<%=split_page_url%>?cp=<%=pageSize%>&ls=<%=lineSize%>&col=<%=column%>&kw=<%=keyWord%>"><%=pageSize%></a></li>
	<%
		}
	%>
	</ul>
</div>