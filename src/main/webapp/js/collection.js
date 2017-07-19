/**
 * 页面初始化
 */
$(function() {
	getDatasList();
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 获取房源收藏信息
 */
function getDatasList(){
	var url = myUtil.BASE + "/custcenter/getCollect";
	myUtil.ajax.axs(url,null,getListSuccess);
}

function getListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	$("#collect").html("");
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
			html += '<div class="list clearfloat fl box-s"><a href="room-detail.html?rentId='+item.id+'"><div class="tu clearfloat"><span></span>';
			html += '<img src="'+item.bedroomImageUrl+'"/></div><div class="right clearfloat"><div class="tit clearfloat"><p class="fl">'+item.name+'</p>';
			html += '<i class="fr iconfont icon-delete delete"></i></div><p class="recom-jianjie">'+item.roomType+'   |  '+item.space+'  |  '+item.decorate+'</p>';
			html += '<div class="recom-bottom clearfloat">'+item.price+'<samp>元/月</samp></div></div></a></div>';
		});
	}else{
		myUtil.toast('空数据');
	}
	$("#collect").append(html);
}
