var TASK_INFO_TASK = "01";
var TASK_INFO_ASSESS = "02";
/**
 * 页面初始化
 */
$(function() {
	myUtil.menu(2);
//	myUtil.alert("网络出问题啦~");
//	myUtil.toast("网络出问题啦~");
	getMessageDatasList();
	getTaskDatasList();
	getPaymentDatasList();
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})
/**
 * 获取最新留言信息
 */
function getMessageDatasList(){
	var url = myUtil.BASE + "/message/getMessage";
	myUtil.ajax.axs(url,null,getMessageListSuccess);
}

/**
 * 获取最新任务信息
 */
function getTaskDatasList(){
	var url = myUtil.BASE + "/taskinfo/getTaskInfo";
	myUtil.ajax.axs(url,null,getTaskListSuccess);
}

/**
 * 获取最新缴费信息
 */
function getPaymentDatasList(){
	var url = myUtil.BASE + "/paydetail/getPayDetail";
	myUtil.ajax.axs(url,null,getPaymentListSuccess);
}

function getMessageListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	$("#message").html("");
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
			html += '<div class="list clearfloat fl box-s"><a href="#"><div class="liuyan-tu clearfloat">';
			html += '<span></span><img src="../images/touxiang.png"/></div><div class="liuyan-right clearfloat">';
			html += '<div class="tit clearfloat"><p class="fl">'+item.custName+'</p><span class="fr">'+myUtil.dateFormat(item.createTime, "yyyy-MM-dd hh:mm")+'</span></div>';
			html += '<p class="recom-liuyan">'+item.content+'</p>';
			html += '<div class="recom-bottom clearfloat"><span><i class="iconfont icon-praise"></i>赞</span>';
			html += '<span><i class="iconfont icon-duihao"></i>特别关注</span></div></div></a></div>';
		});
	}else{
		html += '<div class="empty-list clearfloat" id="main"><i class="iconfont icon-meineirong"></i><p>还没有内容哦！</p></div>';
		//myUtil.toast('空数据');
	}
	$("#message").append(html);
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
            if(item.type == TASK_INFO_TASK){
                html += '<a href="rent-life-taskdetail.html">';
            }else if(item.type == TASK_INFO_ASSESS){
                html += '<a href="rent-life-taskassess.html?taskId='+item.id+'">';
            }
			html += '<div class="content clearfloat box-s"><div class="topsche-top box-s clearfloat"><p class="fl time">';
			html += '<i class="iconfont icon-time"></i>'+myUtil.dateFormat(item.updateTime, "yyyy-MM-dd hh:mm")+'</p>';
			if(item.type == TASK_INFO_TASK){
				html += '<p class="tit fr">查看详情</p></div>';
			}else if(item.type == TASK_INFO_ASSESS){
				html += '<p class="tit fr titwo">任务评价</p></div>';
			}
			html += '<div class="list clearfloat fl box-s"><div class="tu clearfloat">';
			if(item.type == TASK_INFO_TASK){
				html += '<span></span><img src="../upload/clean.jpg"/></div><div class="right clearfloat">';
			}else if(item.type == TASK_INFO_ASSESS){
				html += '<span></span><img src="../upload/evaluate.jpg"/></div><div class="right clearfloat">';
			}
			html += '<div class="tit clearfloat"><p class="fl">'+item.custName+'</p><span class="fr">'+myUtil.dateFormat(item.endTime, "yyyy-MM-dd")+'</span></div>';
			if(item.type == TASK_INFO_TASK){
				html += '<p class="recom-liuyan">'+item.content+'</p><div class="recom-bottom clearfloat">';
			}else if(item.type == TASK_INFO_ASSESS){
				html += '<p class="recom-liuyan">您的室友已经打扫完卫生了，赶快来围观评价吧！</p><div class="recom-bottom clearfloat">';
			}
			var strs= new Array(); //定义一数组 
			strs=item.taskList.split(","); //字符分割 
			for (i=0;i<strs.length ;i++ ) 
			{ 
				strs[i] = myUtil.getSystemParamTitle("TASK_INFO", "TASK_LIST", strs[i]);
				if(item.type == TASK_INFO_ASSESS){
					html += '<span class="finish"><i class="iconfont icon-duihao"></i>'+strs[i]+'</span>';
				}else{
					if(item.taskState == '00'){
						html += '<span class="waiting"><i class="iconfont icon-duihao"></i>'+strs[i]+'</span>';
					}else if(item.taskState == '01'){
						html += '<span class="finish"><i class="iconfont icon-duihao"></i>'+strs[i]+'</span>';
					}
				}
			} 
			html += '</div></div></div></div></a>';
		});
	}else{
		html += '<div class="empty-list clearfloat" id="main"><i class="iconfont icon-meineirong"></i><p>还没有内容哦！</p></div>';
		//myUtil.toast('空数据');
	}
	$("#task").append(html);
}

function getPaymentListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	$("#payment").html("");
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
			html += '<dl class="list clearfloat box-s fl"><dt><p class="fl">'+myUtil.getSystemParamTitle("PAY_DETAIL", "TYPE", item.type)+'[流水号：'+item.payDetailId+']</p>';
			html += '<span class="fr">-'+item.amount+'</span></dt><dd>'+myUtil.dateFormat(item.createTime, "yyyy-MM-dd hh:mm")+'</dd></dl>';
		});
	}else{
		html += '<div class="empty-list clearfloat" id="main"><i class="iconfont icon-meineirong"></i><p>还没有内容哦！</p></div>';
		//myUtil.toast('空数据');
	}
	$("#payment").append(html);
}