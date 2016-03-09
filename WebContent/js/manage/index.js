/**
 * 面板折叠展开事件响应
 */
function fold_expand_event(){
	$(".specific_dir").click(function(){
		var dir_id = $(this).attr("id");
		var panel_id = dir_id + "_panel";
		$("#"+panel_id).toggle(500);
	});
}



