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
<link href="<%=request.getContextPath()%>/css/manage/index.css" rel="stylesheet">
<title>主页</title>
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
                    <li class="active">
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
 						<li>
	                        <a href="#systemSetting" class="nav-header collapsed" data-toggle="collapse">
	                            <i class="glyphicon glyphicon-cog"></i>
	                            	 用户管理
	                               <span class="pull-right glyphicon glyphicon-chevron-down"></span>
	                        </a>
	                        <ul id="systemSetting" class="nav nav-list collapse secondmenu" style="height: 0px;">
	                            <li><a href="<%=request.getContextPath() %>/admin/user"><i class="glyphicon glyphicon-user"></i>添加用户</a></li>
	                            <li><a href="<%=request.getContextPath() %>/admin/list"><i class="glyphicon glyphicon-th-list"></i>用户列表</a></li>
	                            <li><a href="#"><i class="glyphicon glyphicon-asterisk"></i>存储计划</a></li>
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
            	<div class="divider">
            	</div>
				<div class="row">
            		<div class="col-md-2 condition-label">
            			设备号
            		</div>
            		<div class="col-md-10 condition-choice">
            			<ul id="device_choice">
            				<li data-name="0">不限</li>
            				<li data-name="1">1号</li>
            				<li data-name="2">2号</li>
            				<li data-name="3">3号</li>
            			</ul>
            		</div>
            	</div>
            	<div class="divider">
            	</div>
            	<div class="row">
            		<div class="col-md-2 condition-label">
            			视频日期
            		</div>
            		<div class="col-md-10 condition-choice">
            			<div class="row">
            				<div class="col-md-2">
            					<div class="row">
            						<div class="col-md-9">
            							<select id="sel_year" class="form-control">
										  
										</select>
            						</div>
            						<div class="col-md-3 condition-word">
            						年
            						</div>
            					</div>           					
            				</div>
            				<div class="col-md-2">
            					<div class="row">
            						<div class="col-md-9">
            							<select id="sel_month" class="form-control">
										  
										</select>
            						</div>
            						<div class="col-md-3 condition-word">
            						月
            						</div>
            					</div>           					
            				</div>
            				<div class="col-md-2">
            					<div class="row">
            						<div class="col-md-9">
            							<select id="sel_day" class="form-control">
										  
										</select>
            						</div>
            						<div class="col-md-3 condition-word">
            						日
            						</div>
            					</div>           					
            				</div> 				
            				<div class="col-md-offset-4 col-md-2">
            					<button class="btn btn-success" onclick="return submit_confirm_condition()">确定</button>
            				</div>
            			</div>
            		</div>
            	</div>
            	<div class="divider">
            	</div>
            	<div class="row">
            		<ol class="breadcrumb nav_path">
					  <li><a href="#">usr</a></li>
					  <li><a href="#">hadoop</a></li>
					  <li><a href="#">data</a></li>
					  <li class="active">dev_1</li>
					</ol>
            	</div>
            	<div class="row">
            		<div class="specific_dir" id="dir_1">
            			<span class="glyphicon glyphicon-folder-open folder"></span>
            			<span class="dir_name">20160229</span>
            		</div>
            		<div class="expanding_panel" id="dir_1_panel">
            			<div class="single_record">
            				<span class="glyphicon glyphicon-facetime-video"></span>
	            			095524.mp4
	            			<span class="glyphicon glyphicon-cloud-download"></span>
	            			<span class="glyphicon glyphicon-remove-circle"></span>
            			</div>
            			<div class="single_record">
            				<span class="glyphicon glyphicon-facetime-video"></span>
	            			101331.mp4
	            			<span class="glyphicon glyphicon-cloud-download"></span>
	            			<span class="glyphicon glyphicon-remove-circle"></span>
            			</div>
            		</div>
            		<div class="specific_dir" id="dir_2">
            			<span class="glyphicon glyphicon-folder-open folder"></span>
            			<span class="dir_name">20160307</span>
            		</div>
            		<div class="expanding_panel" id="dir_2_panel">
            			<div class="single_record">
            				<span class="glyphicon glyphicon-facetime-video"></span>
	            			110320.mp4
	            			<span class="glyphicon glyphicon-cloud-download"></span>
	            			<span class="glyphicon glyphicon-remove-circle"></span>
            			</div>
            			<div class="single_record">
            				<span class="glyphicon glyphicon-facetime-video"></span>
	            			131305.mp4
	            			<span class="glyphicon glyphicon-cloud-download"></span>
	            			<span class="glyphicon glyphicon-remove-circle"></span>
            			</div>
            		</div>
            	</div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/manage/index.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/manage/sel_condition.js"></script>
<script type="text/javascript">
$(function () {
	init();
	$.ms_DatePicker();
	device_select();
	fold_expand_event();
}); 
</script>
</html>