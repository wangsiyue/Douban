<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%
	String path=request.getContextPath();//     /douban1
	//				http				://		localhost			   :		8080				/douban1
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
<meta charset="UTF-8">
<title>书籍列表</title>
</head>
<body>
<div>
	<h1>书籍列表</h1>
	<a href="<c:url value="/admin/toAddBookForm"/>">添加书籍</a>
	<table border="1" cellspacing="0" cellpadding="4">
		<tr>
			<th>类别</th>
			<th>书名</th>
			<th>ISBN</th>
			<th>作者</th>
			<th>价格</th>
			<th>&nbsp;</th>
		</tr>
		<c:forEach items="${bookList}" var="book">
			<tr>
				<td>${book.bookType.typename }</td>
				<td>${book.name }</td>
				<td>${book.ISBN }</td>
				<td>${book.auther }</td>
				<td>${book.price }</td>
				<td>
					<a href="admin/toEditBookForm/${book.bookid }">编辑</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>