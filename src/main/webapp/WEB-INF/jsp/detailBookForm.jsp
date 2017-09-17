<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!-- 这就是form 标签库 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%
	/*机顶网址  防止路径出错    只有jsp特有*/
	String path=request.getContextPath();  //  /douban
	//                    http           ://      localhost            :       8080                /douban /       
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
<meta charset="UTF-8">
<title>书籍详情</title>
</head>
<body>
<h1>微书屋</h1>
		<div id="global">
			<fieldset>
				<legend>${book.name }</legend>
				<p>
					<div id="info"><lable>类别:</lable>${book.bookType.typename }</div>
				</p>
				<p>
					<div id="info"><lable>ISBN:</lable>${book.ISBN }</div>
				</p>
				<p>
					<div id="info"><lable>作者:</lable>${book.auther }</div>
				</p>
				<p>
					<div id="info"><lable>价格:</lable>${book.price }</div>
				</p>
				<p id="buttons">
					<a href="javascript:history.go(-1)">返回上一级</a>
				</p>
				
			</fieldset>
	</div>
</body>
</html>