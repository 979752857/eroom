var _tab_index = 1;
var _data_execute = {
	isLoad : false,
	loadMore : true,
	page : 0
}
var _data_finish = {
	isLoad : false,
    loadMore : true,
	page : 0
}
var _data_all = {
	isLoad : false,
    loadMore : true,
	page : 0
}
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
 * tab页切换
 */
function changeTab(index){
    _tab_index = index;
    if(_tab_index == 1){
		if(!_data_execute.isLoad){
			_data_execute.isLoad = true;
			getTaskDatasList();
		}else{
            checkLoadMore(null, _data_execute);
		}
    }else if(_tab_index == 2){
		if(!_data_finish.isLoad) {
            _data_finish.isLoad = true;
            getTaskDatasList();
        }else{
            checkLoadMore(null, _data_finish);
        }
    }else if(_tab_index == 3){
		if(!_data_all.isLoad) {
            _data_all.isLoad = true;
            getTaskDatasList();
        }else{
            checkLoadMore(null, _data_all);
		}
    }
}

/**
 * 获取任务信息
 */
function getTaskDatasList(){
    var url ;
    var data;
	if(_tab_index == 1){
        $("#task-execute").show();
        $("#task-finish").hide();
        $("#task-all").hide();
        url = myUtil.BASE + "/taskinfo/getTaskInfoWait";
        data = {
        	page : _data_execute.page
		}
	}else if(_tab_index == 2){
        $("#task-execute").hide();
        $("#task-finish").show();
        $("#task-all").hide();
        url = myUtil.BASE + "/taskinfo/getTaskInfoFinish";
        data = {
            page : _data_finish.page
        }
	}else{
        $("#task-execute").hide();
        $("#task-finish").hide();
        $("#task-all").show();
        url = myUtil.BASE + "/taskinfo/getAllTaskInfo";
        data = {
            page : _data_all.page
        }
	}
	myUtil.ajax.axs(url,data,getTaskListSuccess);
}

function getTaskListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas.list;
	var html = "";
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
			html += '<div class="content clearfloat box-s"><div class="topsche-top box-s clearfloat"><p class="fl time">';
			html += '<i class="iconfont icon-time"></i>'+myUtil.dateFormat(item.startTime, "yyyy-MM-dd")+'</p></div>';
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
                    html += '<div class="topsche-top entrust box-s clearfloat"><a href="rent-life-taskassess.html?taskId='+item.id+'" class="tit fr entrust-btn">任务评价</a></div>';
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
        if(_tab_index == 1){
            _data_execute.page = _data_execute.page+1;
        }else if(_tab_index == 2){
            _data_finish.page = _data_finish.page+1;
        }else{
            _data_all.page = _data_all.page+1;
        }
	}else{
        html += '<div class="empty-list clearfloat" id="main"><i class="iconfont icon-meineirong"></i><p>还没有内容哦！</p></div>';
		// myUtil.toast('目前还没有任务信息哦！');
	}
    if(_tab_index == 1){
        $("#task-execute").append(html);
        checkLoadMore(result.datas, _data_execute);
    }else if(_tab_index == 2){
        $("#task-finish").append(html);
        checkLoadMore(result.datas, _data_finish);
    }else{
        $("#task-all").append(html);
        checkLoadMore(result.datas, _data_all);
    }
}

/**
 * 加载更多
 */
function loadMore(){
    getTaskDatasList();
}