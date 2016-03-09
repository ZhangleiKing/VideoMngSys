/**
 * 编辑用户信息
 */
function editUser(){
	$(".btn-edit").click(function(){
		var aid=$(this).attr("name");//点击获取当前button的id值
		window.location.replace("user/"+aid);
	});
}

/**
 * 删除用户信息
 */
function deleteUser(){
    $(".btn-delete").click(function(){
        var aid=$(this).attr("name");//点击获取当前button的id值
        warn('warn!','确认删除!','f',true,'确认',function(){
            $.ajax({
                url:"http://localhost:8080/VideoMngSys/admin/list",
                type:'POST',
                dataType:'json',
                data:{
                    id:aid
                },
                success: function(data){
                	if(data.deleteStatus == 1){
                		window.location.reload();
                	}        	
                }
            });
        });

    });
}