var _tabIndex = 1;
/**
 * 页面初始化
 */
$(function() {
    var index = myUtil.getUrlParam('tabIndex');
    if(index){
    	_tabIndex = index;
    	$("#tab-"+_tabIndex).click();
	}
	getDatasList(_tabIndex);
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 获取订单信息
 */
function getDatasList(index){
	var url = "";
	_tabIndex = index;
	if(_tabIndex == 1){
		url = myUtil.BASE + "/payorder/getPayOrderWait";
	}else if(_tabIndex == 2){
		url = myUtil.BASE + "/roombook/getRentBookFinish";
	}else if(_tabIndex == 3){
		url = myUtil.BASE + "/payorder/getPayOrderFinish";
	}else if(_tabIndex == 4){
		url = myUtil.BASE + "/payorder/getPayOrderAll";
	}
	myUtil.ajax.axs(url,null,getListSuccess);
}

function getListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	var tabId = "";
	if(_tabIndex == 1){
		tabId = "order-wait";
	}else if(_tabIndex == 2){
		tabId = "order-assess";
	}else if(_tabIndex == 3){
		tabId = "order-finish";
	}else if(_tabIndex == 4){
		tabId = "order-all";
	}
	$("#"+tabId).html("");
	if(datas&&datas.length>0){
        $.each(datas,function(index,item) {
            html += '<div class="content clearfloat box-s"><div class="topsche-top box-s clearfloat">';
            html += '<p class="fl add">支付租期：'+myUtil.dateFormat(item.startTime, "yyyy-MM-dd")+' - '+myUtil.dateFormat(item.endTime, "yyyy-MM-dd")+'</p><p class="tit fr">'+myUtil.getSystemParamTitle("PAY_ORDER", "ORDER_STATE", item.payOrderState)+'</p> </div>';
            html += '<div class="list clearfloat fl box-s"><a href="house-details.html"><div class="tu clearfloat">';
            html += '<span></span><img src="../upload/list-tu.jpg"/></div><div class="right clearfloat"><div class="tit clearfloat">';
            html += '<p class="fl">'+item.name+'</p> </div><p class="recom-jianjie">'+item.roomType+'   |  '+item.space+'  |  '+item.decorate+'</p>';
            html += '<div class="recom-bottom clearfloat"><p class="recom-price">'+item.rentAmount+'元/月</p></div> </div> </a> </div> <div class="topsche-top entrust box-s clearfloat">';
            if(item.payOrderState == PAY_ORDER.getPayOrderState("WAIT")){
                html += '<a href="pay-rent.html" class="tit fr entrust-btn"> 付款 </a><a href="#" class="tit fr entrust-btn rent-btn"> 取消订单 </a></div> </div>';
			}else if(item.payOrderState == PAY_ORDER.getPayOrderState("FINISH")){
                html += '<a href="pay-rent.html" class="tit fr entrust-btn"> 查看详情 </a><a href="#" class="tit fr entrust-btn rent-btn"> 立即评价 </a></div> </div>';
            }else{
                html += '</div></div>';
			}

        });
	}else{
		html += '<div class="empty-list clearfloat" id="main"><i class="iconfont icon-meineirong"></i><p>还没有订单，快去下单吧！</p></div>';
	}
	$("#"+tabId).append(html);
}
