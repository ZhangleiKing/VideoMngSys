<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/user/regist-result.css" rel="stylesheet">
<title>注册信息</title>
</head>
<body>
	<div class="box">
		<div class="infoLink">
			<!-- ${requestScope.registStatus }<br />	 -->
			<c:if test="${requestScope.registStatus == 0 }">
				注册失败，请<a href="regist">重新尝试</a>
			</c:if>
			<c:if test="${requestScope.registStatus == 1 }">
				注册成功，请<a href="login">登陆</a>
			</c:if>
			<c:if test="${requestScope.registStatus == 2 }">
				提交过于频繁，请<a href="regist">重新尝试</a>
			</c:if>
		</div>
	</div>
		
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript">
</script>
</body>
</html>