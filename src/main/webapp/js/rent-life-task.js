var _tab_index = 1;
/**
 * 页面初始化
 */
$(function() {
	getTaskDatasList(1);
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 获取任务信息
 */
function getTaskDatasList(index, data){
    var url ;
    _tab_index = index;
	if(_tab_index == 1){
        $("#task-execute").show();
        $("#task-finish").hide();
        $("#task-all").hide();
        url = myUtil.BASE + "/taskinfo/getTaskInfoWait";
	}else if(_tab_index == 2){
        $("#task-execute").hide();
        $("#task-finish").show();
        $("#task-all").hide();
        url = myUtil.BASE + "/taskinfo/getTaskInfoFinish";
	}else{
        $("#task-execute").hide();
        $("#task-finish").hide();
        $("#task-all").show();
        url = myUtil.BASE + "/taskinfo/getAllTaskInfo";
	}
	myUtil.ajax.axs(url,data,getTaskListSuccess);
}

function getTaskListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
    if(_tab_index == 1){
        $("#task-execute").html("");
    }else if(_tab_index == 2){
        $("#task-finish").html("");
	}else{
        $("#task-all").html("");
	}
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
			html += '<div class="content clearfloat box-s"><div class="topsche-top box-s clearfloat"><p class="fl time">';
			html += '<i class="iconfont icon-time"></i>'+myUtil.dateFormat(item.updateTime, "yyyy-MM-dd hh:mm")+'</p></div>';
			html += '<div class="list clearfloat fl box-s"><a href="#"><div class="tu clearfloat">';
			if(item.type == ROOM_TASK.getType("TASK")){
				html += '<span></span><img src="../upload/clean.jpg"/></div><div class="right clearfloat">';
                html += '<div class="tit clearfloat"><p class="fl">'+item.custName+'</p><span class="fr">'+myUtil.dateFormat(item.endTime, "yyyy-MM-dd")+'</span></div>';
                html += '<p class="recom-liuyan">'+item.content+'</p><div class="recom-bottom clearfloat">';
			}else if(item.type == ROOM_TASK.getType("ASSESS")){
				html += '<span></span><img src="../upload/evaluate.jpg"/></div><div class="right clearfloat">';
                html += '<div class="tit clearfloat"><p class="fl">'+item.custName+'</p><span class="fr">'+myUtil.dateFormat(item.endTime, "yyyy-MM-dd")+'</span></div>';
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
            if(item.taskState == ROOM_TASK.getState("WAITING")){
                if(item.type == ROOM_TASK.getType("TASK")){
                    html += '<div class="topsche-top entrust box-s clearfloat"><a href="#" class="tit fr entrust-btn">任务完成</a><a href="rent-life-taskdetail.html" class="tit fr entrust-btn">查看详情</a></div>';
                }else if(item.type == ROOM_TASK.getType("ASSESS")){
                    html += '<div class="topsche-top entrust box-s clearfloat"><a href="rent-life-taskassess.html" class="tit fr entrust-btn">任务评价</a></div>';
                }
            }else if(item.taskState == ROOM_TASK.getState("FINISH")){
                if(item.type == ROOM_TASK.getType("TASK")){
                    html += '<div class="topsche-top entrust box-s clearfloat"><a href="rent-life-taskdetail.html" class="tit fr entrust-btn">查看详情</a></div>';
                }else if(item.type == ROOM_TASK.getType("ASSESS")){
                    html += '<div class="topsche-top entrust box-s clearfloat"><a href="#" class="tit fr entrust-btn">查看评价</a></div>';
                }
            }
			html += '</div>';
		});
	}else{
        html += '<div class="empty-list clearfloat" id="main"><i class="iconfont icon-meineirong"></i><p>还没有内容哦！</p></div>';
		// myUtil.toast('目前还没有任务信息哦！');
	}
    if(_tab_index == 1){
        $("#task-execute").append(html);
    }else if(_tab_index == 2){
        $("#task-finish").append(html);
    }else{
        $("#task-all").append(html);
    }
}