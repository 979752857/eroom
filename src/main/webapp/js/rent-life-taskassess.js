var _level = 0;
var _taskId = 0;
/**
 * 页面初始化
 */
$(function() {
    _taskId = myUtil.getUrlParam("taskId");
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 任务评价
 */
function assessTask(){
	var url = myUtil.BASE + "/assess/submitAssess";
	if(!_taskId){
		myUtil.toast("参数错误");
		return;
	}
	var data = {
		content : $("#content").val(),
		taskId : _taskId,
		imageUrl : "",
		level : _level
	};
	myUtil.ajax.axs(url,data,getResult);
}

function getResult(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	if(result.success){
        myUtil.alert("评论成功，感谢您的评论！");
        history.go(-1);
	}else{
		if(result.message){
            myUtil.toast(result.message);
		}else{
            myUtil.toast('服务器繁忙请重试');
		}
	}
}
