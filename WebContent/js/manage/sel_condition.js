var sel_device_num = null;
var sel_year = null;
var sel_month = null;
var sel_day = null;
var url_arr = new Array();

/**
 * 页面加载完后就执行该函数
 */
function init(){
	get_url_array();
	if(url_arr[0] != null){
		$("#device_choice").find("li").eq(url_arr[0]).css("color", "green");
	}else{
		$("#device_choice li:eq(0)").css("color", "green");
	}
}

function device_select(){
	$("#device_choice li").each(function(i){
		$(this).click(function(){
			sel_device_num = i;
			
			//改变选择条件,首先获取当前已存在的条件信息
			get_url_array();
			
			//改变其中的对应信息为当前选择的信息
			if(sel_device_num > 0){
				url_arr[0] = sel_device_num;
			}else if(sel_device_num == 0){
				url_arr[0] = null;
			}
			
			var url = window.location.href;
			var url_new;
			if(url.indexOf("?") == -1){
				url_new = url;
			}
			else{
				url_new = url.substr(0,url.indexOf("?"));//url地址？前的部分,包括？
			}
			
			var first_param = true;
			for(var j = 0; j < 4 ; j++){
				if(url_arr[j] != null){
					if(first_param){
						first_param = false;
						url_new = url_new + "?dev=" + url_arr[j];
					}
					else{
						url_new = url_new + "&dev=" + url_arr[j];
					}
				}
			}
			
			window.location.href = url_new;
		});
	});
}

/**
 * 将所有设备的选中状态全部取消
 */
function cancel_chosen(){
	$("#device_choice li").each(function(i){
		$(this).css("color","black");
	});
}

/**
 * 获取url中某个参数的值
 * @param name
 * @returns
 */
function get_url_param(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) return unescape(r[2]); return null; //返回参数值
}

/**
 * 从url中获取参数
 */
function get_url_array(){
	url_arr[0] = get_url_param("dev");
	url_arr[1] = get_url_param("year");
	url_arr[2] = get_url_param("month");
	url_arr[3] = get_url_param("day");
}

function construct_url(array){
	
}

(function($){
$.extend({
ms_DatePicker: function (options) {
            var defaults = {
                YearSelector: "#sel_year",
                MonthSelector: "#sel_month",
                DaySelector: "#sel_day",
                FirstText: "--",
                FirstValue: 0
            };
            var opts = $.extend({}, defaults, options);
            var $YearSelector = $(opts.YearSelector);
            var $MonthSelector = $(opts.MonthSelector);
            var $DaySelector = $(opts.DaySelector);
            var FirstText = opts.FirstText;
            var FirstValue = opts.FirstValue;

            // 初始化
            var str = "<option value=\"" + FirstValue + "\">" + FirstText + "</option>";
            $YearSelector.html(str);
            $MonthSelector.html(str);
            $DaySelector.html(str);

            // 年份列表
            var yearNow = new Date().getFullYear();
			var yearSel = $YearSelector.attr("rel");
            for (var i = yearNow - 5; i <= yearNow + 5; i++) {
				var sed = yearSel==i?"selected":"";
				var yearStr = "<option value=\"" + i + "\" " + sed+">" + i + "</option>";
                $YearSelector.append(yearStr);
            }

            // 月份列表
			var monthSel = $MonthSelector.attr("rel");
            for (var i = 1; i <= 12; i++) {
				var sed = monthSel==i?"selected":"";
                var monthStr = "<option value=\"" + i + "\" "+sed+">" + i + "</option>";
                $MonthSelector.append(monthStr);
            }

            // 日列表(仅当选择了年月)
            function BuildDay() {
                if ($YearSelector.val() == 0 || $MonthSelector.val() == 0) {
                    // 未选择年份或者月份
                    $DaySelector.html(str);
                } else {
                    $DaySelector.html(str);
                    var year = parseInt($YearSelector.val());
                    var month = parseInt($MonthSelector.val());
                    var dayCount = 0;
                    switch (month) {
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                            dayCount = 31;
                            break;
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            dayCount = 30;
                            break;
                        case 2:
                            dayCount = 28;
                            if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
                                dayCount = 29;
                            }
                            break;
                        default:
                            break;
                    }
					
					var daySel = $DaySelector.attr("rel");
                    for (var i = 1; i <= dayCount; i++) {
						var sed = daySel==i?"selected":"";
						var dayStr = "<option value=\"" + i + "\" "+sed+">" + i + "</option>";
                        $DaySelector.append(dayStr);
                    }
                }
            }
            $MonthSelector.change(function () {
                BuildDay();
            });
            $YearSelector.change(function () {
                BuildDay();
            });
			if($DaySelector.attr("rel")!=""){
				BuildDay();
			}
        } // End ms_DatePicker
});
})(jQuery);
