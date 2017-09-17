<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<%
	/*机顶网址  防止路径出错    只有jsp特有*/
	String path = request.getContextPath(); //  /douban
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#bookType").change(function(){
			//路径:findBookByBookType/1
			$.post("findBookByBookType/"+$(this).val(),function(json){
				var obj=$.parseJSON(json);
				$("#bookList").html("");
				//obj是json的数据
				for(var i=0;i<obj.length;i++){
					var o=obj[i];
					$("#bookList").append(
							"<tr><td>"+o.bookType.typename
							+"</td><td>"+o.name
							+"</td><td>"+o.ISBN
							+"</td><td>"+o.auther
							+"</td><td>"+o.price
							+"</td><td>"
							+"<a href='toDetailBookForm/"+o.bookid+"'>详情</a>&nbsp;<a href='toRankBookForm/"+o.bookid+"'>投票</a></td></tr>")
				}
			});
		});
	});

</script>
<title>学生书籍列表-前台</title>
</head>
<body>
	<div id="global">
		<h1>微书屋</h1>
		<!-- 用forEach  迭代 -->
		筛选：<select id="bookType" name="bookType.typeid">
			<option value="-1">所有类型</option>
			<c:forEach items="${bookTypes }" var="bookType">
				<option value="${bookType.typeid}">${bookType.typename}</option>
			</c:forEach>
		</select>

		<table border="1">
			<thead>
				<tr>
					<th>类别</th>
					<th>书名</th>
					<th>ISBN</th>
					<th>作者</th>
					<th>价格</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="bookList">
				<c:forEach items="${bookList}" var="book">
					<tr>
						<td>${book.bookType.typename}</td>
						<td>${book.name}</td>
						<td>${book.ISBN}</td>
						<td>${book.auther}</td>
						<td>${book.price}</td>
						<td><a href="toDetailBookForm/${book.bookid }">详情</a> 
							<a href="toRankBookForm/${book.bookid }">投票</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>