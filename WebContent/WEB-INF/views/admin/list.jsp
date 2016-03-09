<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/slide-menu.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/nav-card.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/manage/index.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/admin/list.css" rel="stylesheet">
<title>查看</title>
</head>
<body>
	<div class="navbar navbar-duomi navbar-static-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header col-md-10">
           		<a class="navbar-brand" href="<%=request.getContextPath() %>/manage/index" id="logo">视频存储管理系统
               	</a>
            </div>
            <div class="col-md-1 user-info">
           		<c:if test="${!empty sessionScope.username }">
					Hi, ${sessionScope.username }
				</c:if>
           	</div>
            <div class="col-md-1 cancel">
           		<form action="<%=request.getContextPath() %>/user/logout" method="POST">
					<button type="submit" class="btn btn-primary">退出</button>
				</form>
           	</div>
        </div>
    </div>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2">
                <ul id="main-nav" class="nav nav-tabs nav-stacked" style="">
                    <li>
                        <a href="<%=request.getContextPath() %>/manage/index">
                            <i class="glyphicon glyphicon-th-large"></i>
                            	首页         
                        </a>
                    </li>
                    
                    <li>
                        <a href="#">
                            <i class="glyphicon glyphicon-credit-card"></i>
                            	视频管理        
                        </a>
                    </li>
 					<c:if test="${sessionScope.authority > 1 }">
 						<li class="active">
	                        <a href="#systemSetting" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-cog"></i>
	                            	 用户管理
	                               <span class="pull-right glyphicon glyphicon-chevron-down"></span>
	                        </a>
	                        <ul id="systemSetting" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                            <li><a href="<%=request.getContextPath() %>/admin/user" target="_blank"><i class="glyphicon glyphicon-user"></i>添加用户</a></li>
	                            <li><a href="<%=request.getContextPath() %>/admin/list" target="_blank"><i class="glyphicon glyphicon-th-list"></i>用户列表</a></li>
	                            <li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>角色管理</a></li>
	                            <li><a href="#"><i class="glyphicon glyphicon-edit"></i>修改密码</a></li>
	                            <li><a href="#"><i class="glyphicon glyphicon-eye-open"></i>日志查看</a></li>
	                        </ul>
	                    </li>
 					</c:if>
                    
                    <li>
                        <a href="#">
                            <i class="glyphicon glyphicon-fire"></i>
                            	关于系统
                        </a>
                    </li>
 
                </ul>
            </div>
            <div class="col-md-10">			
            	<div class="row">
            		<div class="col-md-12 col-sm-12 nav_area">
						<div class="col-md-4 col-sm-4">
							<div class="nav_part_area bg_color_green">
								<span class="glyphicon glyphicon-th-large area_icon"></span>
								<span class="area_title"><a href="<%=request.getContextPath()%>/admin/list" class="active">用户列表</a></span>
							</div>
						</div>
						<div class="col-md-4 col-sm-4">
							<div class="nav_part_area bg_color_blue">
								<span class="glyphicon glyphicon-user area_icon"></span>
								<span class="area_title"><a href="<%=request.getContextPath()%>/admin/user">添加用户</a></span>
							</div>
						</div>
						<div class="col-md-4 col-sm-4">
						</div>
					</div>
            	</div>
            	<div class="row">
            		<div class="col-md-12 col-sm-12 list_area">
	            		<div class="col-md-8 col-sm-8 col-md-offset-2 col-sm-offset-2">
							<div class="data_table">
								<table border="0">
									<thead>
										<tr>
											<th>用户ID</th>
											<th>用户名</th>
											<th>用户邮箱</th>
											<th>用户权限</th>
											<th>编辑</th>
											<th>删除</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${requestScope.users }" var="user">
											<tr>
												<th>${user.id }</th>
												<th>${user.username }</th>
												<th>${user.email }</th>
												<th>${user.authority }</th>
												<th><button name="${user.id }" class="btn btn-info btn-edit">编辑</button></th>
												<th><button name="${user.id }" class="btn btn-danger btn-delete">删除</button></th>
											</tr>
										</c:forEach>	
									</tbody>
								</table>
							</div>
						</div>
					</div>
            	</div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/admin/warning.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/admin/list.js"></script>
<script type="text/javascript">
	editUser();
	deleteUser();
</script>
</html>