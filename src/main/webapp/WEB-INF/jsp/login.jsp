<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!-- 这就是form 标签库 -->
<%
	/*机顶网址  防止路径出错    只有jsp特有*/
	String path=request.getContextPath();  //  /douban
	//                    http           ://      localhost            :       8080                /douban /       
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base  href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html charset=UTF-8">
<title>书籍后台登录界面</title>
<script type="text/javascript">
//刷新验证码
//记录错误信息
function loadImage(){
	var img=document.getElementById("randImg");
	img.src="imageCode.jsp?r=" +Math.random;
}

</script>
</head>
<body>
	<c:if test="${errmsg!='' }">
		<font style="color:red"><c:out value="${errmsg}"></c:out></font>
	</c:if>
	
	<form action="adminLogin" method="post">
		<fieldset>
			<legend>管理后台登录</legend>
				<p>用户名：<input type="text" name="uname"/></p>
				<p>密码：<input type="password" name="password"/></p>
				<p>验证码：<input type="text" name="validateCode" /></p>
				<img id="randImg" border=0 src="imageCode.jsp">
				<a href="javascript:loadImage()">换一张</a>
				<p><input type="submit" value="登录"/></p>
		</fieldset>
	</form>
	
</body>
</html>