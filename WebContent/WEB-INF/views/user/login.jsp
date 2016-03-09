<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/user/login-page.css" rel="stylesheet">
<title>登陆</title>
</head>
<body>
	<div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<span class="glyphicon glyphicon-film"></span>
				<span>视频存储管理系统</span>
			</div>
			<div class="login-content ">
				<div class="form">
						<div class="form-group">
							<div class="col-xs-12  ">
								<div class="input-group">
									<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
									<input type="text" id="username" name="username" class="form-control" placeholder="用户名" >
								</div>
								<div class="back-info" id="username-back-info">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12  ">
								<div class="input-group">
									<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
									<input type="password" id="password" name="password" class="form-control" placeholder="密码">
								</div>
								<div class="back-info" id="password-back-info">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12 checkbox remember-pwd">
							  <label>
							    <input type="checkbox" id="remember" value="">
							    	记住密码
							  </label>
							</div>
						</div>
						<div class="form-group form-actions">
							<div class="col-xs-12 col-xs-offset-4 ">
								<button type="submit" class="btn btn-sm btn-info" id="login-btn" onclick="login_submit()">登录</button>
							</div>
							<div class="back-info col-xs-offset-4" id="login-back-info">
							</div>
						</div>
						<div class="form-group">
							
							<div class="col-xs-offset-6 col-xs-6 link">
								<p class="text-center remove-margin"><small>还没注册?</small> <a href="regist" ><small>注册</small></a>
								</p>
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
		
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jQuery.md5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jQuery.Cookie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/user/login-page.js"></script>
<script type="text/javascript">
	check_cookie_info();
	check_input_info();
</script>
</body>
</html>