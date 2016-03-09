var check_username = false;
var check_password = false;

function check_username_input(){
	var username = $("#username").val();
	if(username == ""){
		$("#username-back-info").html("请输入用户名");
		return false;
	}else{
		$("#username-back-info").html("");
		return true;
	}
}

function check_password_input(){
	var password = $("#password").val();
	if(password == ""){
		$("#password-back-info").html("请输入密码");
		return false;
	}else{
		$("#password-back-info").html("");
		return true;
	}
}

/**
 * 光标移开后检查输入信息的完整性
 */
function check_input_info(){	
	$("#username").blur(function(){
		check_username_input();
	});
	
	$("#password").blur(function(){
		check_password_input();
	});
	
}

/**
 * handle the login action of user
 */
function login_submit(){
	if(!check_username_input()){
		$("#username").focus();
	}
	else if(!check_password_input()){
		$("#password").focus();
	}
	else{
		var username_text = $("#username").val();
		var password_text = $("#password").val();
		
		var remember_pwd = $("#remember").prop('checked');
		if(remember_pwd){
			$.cookie("username",username_text);
			$.cookie("password",password_text);
		}else{
			$.cookie("username",null);
			$.cookie("password",null);
		}
		
		password_text = $.md5(password_text);
		
		$.ajax({
			url:"http://localhost:8080/VideoMngSys/user/loginSubmit",
			type:'POST',
			dataType:'json',
			data:{
				username:username_text,
				password:password_text
			},
			success:function(data){
				if(data.status == 1){
					$("#login-back-info").html("");
					window.location.href = "http://localhost:8080/VideoMngSys/manage/index"
				}else{
					$("#login-back-info").html("用户名或密码错误");
				}
			}
		});
	}
}

/**
 * if the information is stored in cookie, when user logins, the login page
 * should fill the information automatically.
 */
function check_cookie_info(){
	if($.cookie("username") != null){
		$("#username").val($.cookie("username"));
		$("#password").val($.cookie("password"));
		$("#remember").prop('checked', true);
	}
}