/**
 * 检查密码长度
 */
function check_pwd_length(){
	var pwd = $("#password").val();
	if(pwd.length < 6){
		$("#password-back-info").html("密码长度不得少于6位");
		return false;
	}else{
		$("#password-back-info").html("");
		return true;
	}
}

/**
 * 检查两次密码输入是否一致
 */
function check_pwd_same(){
	var pwd = $("#password").val();
	var re_pwd = $("#re-password").val();
	
	if(pwd != re_pwd){
		$("#re-password-back-info").html("两次密码输入不一致");
		return false;
	}else{
		$("#re-password-back-info").html("");
		return true;
	}
}

/**
 * 检查邮箱格式
 * @param input_email
 */
function check_email_style(input_email){
	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if(reg.test(input_email)){
		$("#email-back-info").html("");
		return true;
	}else{
		$("#email-back-info").html("邮箱格式不正确");
		return false;
	}
}

/**
 * 检查用户名输入完整性与用户名是否已被注册
 * @returns {Boolean}
 */
var check_username = false;
function check_username_input(){
	var username_text = $("#username").val();
	if(username_text == ""){
		$("#username-back-info").html("请输入注册用户名");
		return false;
	}else{
		if(check_username_exist(username_text)){
			return false;
		}
		else{
			check_username = true;
			return true;
		}
		
	}
}

/**
 * 检查注册的用户名是否已存在
 * 已存在，则返回true
 * 不存在，则返回false
 */
function check_username_exist(username_text){
	var bol = true;
	$.ajax({
		url:"http://localhost:8080/VideoMngSys/user/usernameExist",
		async:false,
		type:'POST',
		dataType:'json',
		data:{
			username:username_text
		},
		success:function(data){
			if(data.status=="1"){
				$("#username-back-info").html("该用户名已被注册");
			}else{
				$("#username-back-info").html("");
				bol = false;
			}
		}
	});
	return bol;
}

/**
 * 检查密码输入完整性
 * @returns {Boolean}
 */
function check_password_input(){
	var password_text = $("#password").val();
	if(password_text == ""){
		$("#password-back-info").html("请输入注册密码");
		return false;
	}else{
		$("#password-back-info").html("");
		return true;
	}
}

/**
 * 检查再次输入密码的完整性
 * @returns {Boolean}
 */
function check_re_password_input(){
	var re_password_text = $("#re-password").val();
	if(re_password_text == ""){
		$("#re-password-back-info").html("请输入确认密码");
		return false;
	}else{
		$("#re-password-back-info").html("");
		return true;
	}
}

/**
 * 检查email输入完整性
 * @returns {Boolean}
 */
function check_email_input(){
	var email_text = $("#email").val();
	if(email_text == ""){
		$("#email-back-info").html("请输入邮箱账户");
		return false;
	}else{
		$("#email-back-info").html("");
		return true;
	}
}

/**
 * 移开光标后检查输入信息的完整性
 */
function check_input_info(){
	$("#username").blur(function(){
		check_username_input();
	});
	
	$("#password").blur(function(){
		check_password_input();
		check_pwd_length();
		check_pwd_same();
	});
	
	$("#re-password").blur(function(){
		check_re_password_input();
		check_pwd_same();
	});
	
	$("#email").blur(function(){
		var email_text = $("#email").val();
		check_email_input();
		check_email_style(email_text);
	})
}

/**
 * button的onclick方法
 * 注册提交
 */
function regist_submit(){
	var email_text = $("#email").val();
	
	if(!check_username){
		$("#username").focus();
		return false;
	}else if(!check_password_input()){
		$("#password").focus();
		return false;
	}else if(!check_re_password_input()){
		$("#re-password").focus();
		return false;
	}else if(!check_email_input()){
		$("#email").focus();
		return false;
	}else if(!check_pwd_length()){
		$("#password").focus();
		return false;
	}else if(!check_pwd_same()){
		$("#re-password").focus();
		return false;
	}else if(!check_email_style(email_text)){
		$("#email").focus();
		return false;
	}else {
		var pwd = $("#password").val();
		var md5_pwd = $.md5(pwd);
		$("#password").val(md5_pwd);
		$("#re-password").val(md5_pwd);
		return true;
	}
}

/**
 * form的onsubmit方法
 * 只能预防因为网络延迟，用户在等待过程中多次点击提交按钮
 * 导致的表单多次提交
 */
//表单是否提交
var isCommitted = false;
function beforeSubmit(){
	if(isCommitted == false){
		isCommitted = true;
		return true;//返回true让表单正常提交
	}else{
		return false;//返回false，表单不提交
	}
}

