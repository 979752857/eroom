/**
 * 页面初始化
 */
$(function() {
	getMessageDatasList();
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 获取近一个月留言信息
 */
function getMessageDatasList(){
	var url = myUtil.BASE + "/message/getAllMessage";
	myUtil.ajax.axs(url,null,getMessageListSuccess);
}

/**
 * 发送留言消息
 */
function sendMessage(){
	var url = myUtil.BASE + "/message/addMessage";
	var message = $("#message-input").val();
	var data = {
        message : message
	}
	myUtil.ajax.axs(url,data,sendMessageSuccess);
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
			html += '<span><i class="iconfont icon-right"></i>特别关注</span></div></div></a></div>';
		});
	}else{
        html += '<div class="empty-list clearfloat" id="main"><i class="iconfont icon-meineirong"></i><p>还没有留言信息，快去留言吧！</p></div>';
		myUtil.toast('还没有留言信息，快去留言吧！');
	}
	$("#message").append(html);
}

function sendMessageSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	if(result.success){
        getMessageDatasList();
        $("#message-input").html("");
        myUtil.toast('留言成功');
	}else{
        myUtil.toast('留言失败了，请重试！');
	}
}
