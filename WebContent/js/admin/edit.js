/**
 * 管理员增加用户前的判断
 * @returns {Boolean}
 */
function adminAddUser(){
	var username = $("#username").val();
	var pwd = $("#password").val();
	var email = $("#email").val();
	var authority = $("#authority").val();
	
	if(username == ""){
		$("#username").focus();
		return false;
	}
	else if(pwd == ""){
		$("#password").focus();
		return false;
	}
	else if(email == ""){
		$("#email").focus();
		return false;
	}
	else if(authority == ""){
		$("#authority").focus();
		return false;
	}
	else{
		if(pwd.length < 20){
			var md5_pwd = $.md5(pwd);
			$("#password").val(md5_pwd);
		}
		return true;
	}
}