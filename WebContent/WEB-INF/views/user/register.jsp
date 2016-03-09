<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/user/regist-page.css" rel="stylesheet">

<title>注册</title>
</head>
<body>
	<div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<h1><small>注册</small></h1>
			</div>
			<div class="login-content ">
				<div class="form">
					<form action="regist" onsubmit="return beforeSubmit()" method="post">
						<c:if test="${empty sessionScope.token}">
							<input type="hidden" name="token" value=""/> 
						</c:if>
						<c:if test="${!empty sessionScope.token}">
							<input type="hidden" name="token" value="${sessionScope.token}"/> 
						</c:if>
						<div class="form-group">
							<div class="col-xs-12  ">
								<div class="input-group">
									<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
									<input type="text" id="username" name="username" class="form-control" placeholder="请输入注册用户名">
								</div>
								<div class="back-info" id="username-back-info">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12  ">
								<div class="input-group">
									<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
									<input type="password" id="password" name="password" class="form-control" placeholder="请输入密码(不得少于6位)">
								</div>
								<div class="back-info" id="password-back-info">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12  ">
								<div class="input-group">
									<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
									<input type="password" id="re-password" name="re-password" class="form-control" placeholder="请再次输入密码">
								</div>
								<div class="back-info" id="re-password-back-info">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12  ">
								<div class="input-group">
									<span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
									<input type="email" id="email" name="email" class="form-control" placeholder="请输入邮箱账号">
								</div>
								<div class="back-info" id="email-back-info">
								</div>
							</div>
						</div>
						<div class="form-group form-actions">
							<div class="col-xs-4 col-xs-offset-4 ">
								<button type="submit" class="btn btn-sm btn-success" id="login-btn" onclick="return regist_submit()">点击注册</button>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-6 link">
								<p class="text-center remove-margin"><small>已有账号，</small> <a href="login" ><small>返回登陆</small></a>
								</p>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
		
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jQuery.md5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/user/regist-page.js"></script>
<script type="text/javascript">
	check_input_info();
</script>
</body>
</html>