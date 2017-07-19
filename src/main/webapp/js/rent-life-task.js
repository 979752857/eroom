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
function getTaskDatasList(){
	var url = myUtil.BASE + "/taskinfo/getAllTaskInfo";
	myUtil.ajax.axs(url,null,getTaskListSuccess);
}

function getTaskListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	$("#task").html("");
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
			html += '<div class="content clearfloat box-s"><div class="topsche-top box-s clearfloat"><p class="fl time">';
			html += '<i class="iconfont icon-time"></i>'+myUtil.dateFormat(item.updateTime, "yyyy-MM-dd hh:mm")+'</p></div>';
			html += '<div class="list clearfloat fl box-s"><a href="#"><div class="tu clearfloat">';
			if(item.type == '01'){
				html += '<span></span><img src="../upload/clean.jpg"/></div><div class="right clearfloat">';
			}else if(item.type == '02'){
				html += '<span></span><img src="../upload/evaluate.jpg"/></div><div class="right clearfloat">';
			}
			html += '<div class="tit clearfloat"><p class="fl">'+item.custName+'</p><span class="fr">'+myUtil.dateFormat(item.endTime, "yyyy-MM-dd")+'</span></div>';
			if(item.type == '01'){
				html += '<p class="recom-liuyan">'+item.content+'</p><div class="recom-bottom clearfloat">';
			}else if(item.type == '02'){
				html += '<p class="recom-liuyan">您的室友已经打扫完卫生了，赶快来围观评价吧！</p><div class="recom-bottom clearfloat">';
			}
			var strs= new Array(); //定义一数组 
			strs=item.taskList.split(","); //字符分割 
			for (i=0;i<strs.length ;i++ ) 
			{ 
				strs[i] = myUtil.getSystemParamTitle("PAY_DETAIL", "TYPE", strs[i]);
				if(item.type == '02'){
					html += '<span class="finish"><i class="iconfont icon-duihao"></i>'+strs[i]+'</span>';
				}else{
					if(item.taskState == '00'){
						html += '<span class="waiting"><i class="iconfont icon-duihao"></i>'+strs[i]+'</span>';
					}else if(item.taskState == '01'){
						html += '<span class="finish"><i class="iconfont icon-duihao"></i>'+strs[i]+'</span>';
					}
				}
			} 
			html += '</div></div></a></div>';
			if(item.type == '01'){
				html += '<div class="topsche-top entrust box-s clearfloat"><a href="#" class="tit fr entrust-btn">任务完成</a><a href="rent-life-taskdetail.html" class="tit fr entrust-btn">查看详情</a></div>';
			}else if(item.type == '02'){
				html += '<div class="topsche-top entrust box-s clearfloat"><a href="rent-life-taskassess.html" class="tit fr entrust-btn">任务评价</a></div>';
			}
			html += '</div>';
		});
	}else{
		myUtil.toast('空数据');
	}
	$("#task").append(html);
}