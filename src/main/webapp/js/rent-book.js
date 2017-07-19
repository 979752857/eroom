var _tabIndex = 1;
/**
 * 页面初始化
 */
$(function() {
	getDatasList(1);
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 获取预约信息
 */
function getDatasList(index){
	var url = "";
	_tabIndex = index;
	if(_tabIndex == 1){
		url = myUtil.BASE + "/roombook/getRentBookApply";
	}else if(_tabIndex == 2){
		url = myUtil.BASE + "/roombook/getRentBookFinish";
	}else if(_tabIndex == 3){
		url = myUtil.BASE + "/roombook/getRentBookAll";
	}
	myUtil.ajax.axs(url,null,getListSuccess);
}
/**
 * 取消预约看房
 */
function cancelBook(bookId){
	var url = myUtil.BASE + "/roombook/cancelBook";
	var date = {bookId : bookId};
	myUtil.ajax.axs(url,date,function(result){
		if(!result){
			myUtil.toast('服务器繁忙请重试');
		}
		if(result.success){
			myUtil.toast('预约取消成功');
			getDatasList(1);
		}
	});
}

function getListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	var tabId = "";
	if(_tabIndex == 1){
		tabId = "book-apply";
	}else if(_tabIndex == 2){
		tabId = "book-finish";
	}else if(_tabIndex == 3){
		tabId = "book-all";
	}
	$("#"+tabId).html("");
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
			html += '<div class="content clearfloat box-s"><div class="topsche-top box-s clearfloat">';
			html += '<p class="fl time"><i class="iconfont icon-time"></i>&nbsp;看房时间：'+myUtil.dateFormat(item.startTime, "MM-dd hh:mm")+'</p>';
			if(item.applyState == '00'){
				html += '<p class="tit fr">'; 
			}else if(item.applyState == '01' || item.applyState == '03'){
				html += '<p class="tit titwo fr">'; 
			}else{
				html += '<p class="tit tithree fr">';
			}
			html += myUtil.getSystemParamTitle("ROOM_BOOK", "APPLY_STATE", item.applyState)+'</p>';
			html += '</div><div class="list clearfloat fl box-s">';
			html += '<a href="room-detail.html?rentId='+item.rentId+'"><div class="tu clearfloat"><span></span><img src="'+item.bedroomImageUrl+'"/>';
			html += '</div><div class="right clearfloat"><div class="tit clearfloat"><p class="fl">'+item.name+'</p>';
			html += '<span class="fr">'+item.price+'<samp>元/月</samp></span></div><p class="recom-jianjie">'+item.roomType+'   |  '+item.space+'  |  '+item.decorate+'</p>';
			html += '<div class="recom-bottom clearfloat"><span><i class="iconfont icon-duihao"></i>随时住</span>';
			html += '<span><i class="iconfont icon-duihao"></i>家电齐全</span></div></div></a>';
			html += '<div class="topsche-top entrust box-s clearfloat"><a href="#" class="tit fr entrust-btn">租住此房</a>';
			if(item.applyState == '01' || item.applyState == '00'){
				html += '<a href="javascript:cancelBook('+item.bookId+');" class="tit fr entrust-btn">取消预约</a>';
			}
			html += '</div></div></div>';
		});
	}else{
		html += '<div class="empty-list clearfloat" id="main"><i class="iconfont icon-meineirong"></i><p>还没有内容哦！</p></div>';
		//myUtil.toast('空数据');
	}
	$("#"+tabId).append(html);
}
