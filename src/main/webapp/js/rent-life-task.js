var _tab_index = 1;
/**
 * 页面初始化
 */
$(function() {
	getTaskDatasList();
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 获取近一个月任务信息
 */
function getTaskDatasList(index){
    var url ;
    _tab_index = index;
	if(_tab_index == 1){
        $("#task-execute").show();
        $("#task-finish").hide();
        url = myUtil.BASE + "/taskinfo/getTaskInfo";
	}else{
        $("#task-execute").hide();
        $("#task-finish").show();
        url = myUtil.BASE + "/taskinfo/getAllTaskInfo";
	}
	myUtil.ajax.axs(url,null,getTaskListSuccess);
}

function getTaskListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
    if(_tab_index == 1){
        $("#task-execute").html("");
    }else{
        $("#task-finish").html("");
	}
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
			html += '<div class="content clearfloat box-s"><div class="topsche-top box-s clearfloat"><p class="fl time">';
			html += '<i class="iconfont icon-time"></i>'+myUtil.dateFormat(item.updateTime, "yyyy-MM-dd hh:mm")+'</p></div>';
			html += '<div class="list clearfloat fl box-s"><a href="#"><div class="tu clearfloat">';
			if(item.type == ROOM_TASK.getType("TASK")){
				html += '<span></span><img src="../upload/clean.jpg"/></div><div class="right clearfloat">';
			}else if(item.type == ROOM_TASK.getType("ASSESS")){
				html += '<span></span><img src="../upload/evaluate.jpg"/></div><div class="right clearfloat">';
			}
			html += '<div class="tit clearfloat"><p class="fl">'+item.custName+'</p><span class="fr">'+myUtil.dateFormat(item.endTime, "yyyy-MM-dd")+'</span></div>';
			if(item.type == ROOM_TASK.getType("TASK")){
				html += '<p class="recom-liuyan">'+item.content+'</p><div class="recom-bottom clearfloat">';
			}else if(item.type == ROOM_TASK.getType("ASSESS")){
				html += '<p class="recom-liuyan">您的室友已经打扫完卫生了，赶快来围观评价吧！</p><div class="recom-bottom clearfloat">';
			}
			var strs= new Array(); //定义一数组 
			strs=item.taskList.split(","); //字符分割 
			for (i=0;i<strs.length ;i++ ) 
			{ 
				strs[i] = myUtil.getSystemParamTitle("TASK_INFO", "TASK_LIST", strs[i]);
				if(item.type == ROOM_TASK.getType("ASSESS")){
					html += '<span class="finish"><i class="iconfont icon-duihao"></i>'+strs[i]+'</span>';
				}else{
					if(item.taskState == ROOM_TASK.getState("WAITING")){
						html += '<span class="waiting"><i class="iconfont icon-duihao"></i>'+strs[i]+'</span>';
					}else if(item.taskState == ROOM_TASK.getState("FINISH")){
						html += '<span class="finish"><i class="iconfont icon-duihao"></i>'+strs[i]+'</span>';
					}
				}
			} 
			html += '</div></div></a></div>';
			if(item.type == ROOM_TASK.getType("TASK")){
				html += '<div class="topsche-top entrust box-s clearfloat"><a href="#" class="tit fr entrust-btn">任务完成</a><a href="rent-life-taskdetail.html" class="tit fr entrust-btn">查看详情</a></div>';
			}else if(item.type == ROOM_TASK.getType("ASSESS")){
				html += '<div class="topsche-top entrust box-s clearfloat"><a href="rent-life-taskassess.html" class="tit fr entrust-btn">任务评价</a></div>';
			}
			html += '</div>';
		});
	}else{
		myUtil.toast('目前还没有任务信息哦！');
	}
    if(_tab_index == 1){
        $("#task-execute").append(html);
    }else{
        $("#task-finish").append(html);
    }
}