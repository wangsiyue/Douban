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
<title>修改书籍</title>
<style type="text/css">@import url("css/main.css");</style>
</head>
<body>
		<div id="global">
			<form:form  commandName="book" enctype="multipart/form-data" action="admin/updateBook" method="post">
			    <fieldset>
			        <legend>修改一本书</legend>
			        <form:hidden path="bookid" />
			        <p>
			            <label for="bookType">类别: </label>
			           <form:select id="bookType" path="bookType.typeid"
			           items="${bookTypes }" itemLabel="typename"
			           itemValue="typeid"/>
			        </p>
			        <!--  onkeydown="if(event.keyCode==13)return false;"  回车键   防止填完一项按下回车键 -->
			         <p>
			            <label for="ISBN">ISBN: </label>
			            <form:input id="ISBN" path="ISBN" onkeydown="if(event.keyCode==13)return false;"/>
			        </p>
			        <p>
			            <label for="name">书名: </label>
			            <form:input id="name" path="name" onkeydown="if(event.keyCode==13)return false;"/>
			        </p>
			        <p>
			            <label for="auther">作者: </label>
			           <form:input id="auther" path="auther" onkeydown="if(event.keyCode==13)return false;"/>
			        </p>
			       
			        <p>
			            <label for="price">价格: </label>
			            <form:input id="price" path="price" onkeydown="if(event.keyCode==13)return false;"/>
			        </p>
			         <p>
			            <label for="description">详情: </label>
			            <form:input id="description" path="description" onkeydown="if(event.keyCode==13)return false;"/>
			        </p>
			        <p>
			            <label for="pdfs">已下载的电子书: </label>
			            <ul>
			          	<c:forEach items="${book.pdfsStringList }" var="pdfUrl">
			          		<li><a href="${pdfUrl }">下载电子书</a></li>
			          	</c:forEach>  
			            </ul>
			            
			            <div id="UploadFiles">
			            	重新上传电子书:
			            	<input type="file" name="pdfsUrl"/>
			            	<input type="file" name="pdfsUrl"/>
			            </div>
			        </p>
			        
			        <p id="buttons">
			            <input id="reset" tabindex="4" type="reset">
			            <input id="submit" tabindex="5" value="修改书籍" type="submit">
			        </p>
			    </fieldset>
			</form:form>
	</div>
</body>
</html>