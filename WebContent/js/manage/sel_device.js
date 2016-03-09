var sel_device_num = null;
var sel_year = null;
var sel_month = null;
var sel_day = null;
function device_select(){
	$("#device_choice li").each(function(i){
		$(this).click(function(){
			cancel_chosen();
			sel_device_num = i+1;
			$(this).css("color","green");
		});
	});
}

function cancel_chosen(){
	$("#device_choice li").each(function(i){
		$(this).css("color","black");
	});
}

function get_date(){
	sel_year = $("#sel_year").val();
	sel_month = $("#sel_month").val();
	sel_day = $("#sel_day").val();
}

function submit_confirm_condition(){
	get_date();
	if(sel_device_num == null){
		alert("请选择设备编号");
		return false;
	}else if(sel_year == 0){
		alert("请选择年份");
		return false;
	}else if(sel_month == 0){
		alert("请选择月份");
		return false;
	}else if(sel_day == 0){
		alert("请选择某日");
		return false;
	}else{
		return true;
	}
}