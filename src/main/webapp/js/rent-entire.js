/**
 * 页面初始化
 */
$(function() {
	myUtil.menu(1);
	getDatasList();
	$("#oe_overlay").hide();
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 获取房源信息
 */
function getDatasList(){
	var url = myUtil.BASE + "/room/getRoom";
	myUtil.ajax.axs(url,null,getListSuccess);
}

function getListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	$("#room").html("");
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
			html += '<div class="list clearfloat fl box-s"><a href="room-detail.html?rentId='+item.id+'"><div class="tu clearfloat">';
			html += '<span></span><img src="'+item.bedroomImageUrl+'"/></div><div class="right clearfloat">';
			html += '<div class="tit clearfloat"><p class="fl">'+item.name+'</p><span class="fr">'+item.price+'<samp>元/月</samp></span></div>';
			html += '<p class="recom-jianjie">'+item.roomType+'   |  '+item.space+'  |  '+item.decorate+'</p><div class="recom-bottom clearfloat">';
			html += '<span><i class="iconfont icon-duihao"></i>随时住</span><span><i class="iconfont icon-duihao"></i>家电齐全</span>';
			html += '</div></div></a></div>';
		});
	}else{
		myUtil.toast('空数据');
	}
	$("#room").append(html);
}
