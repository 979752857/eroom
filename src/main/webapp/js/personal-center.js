/**
 * 页面初始化
 */
$(function() {
	myUtil.menu(3);
	getAccountList();
    getOrderInfo();
    getCustInfo();
	getRoomRentVoList();
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 获取用户账户信息
 */
function getAccountList(){
	var url = myUtil.BASE + "/cust/getCustAccount";
	myUtil.ajax.axs(url,null,getAccountSuccess);
}

/**
 * 获取用户订单信息
 */
function getOrderInfo(){
	var url = myUtil.BASE + "/cust/getOrderInfo";
	myUtil.ajax.axs(url,null,getOrderSuccess);
}

/**
 * 获取用户基本信息
 */
function getCustInfo(){
	var url = myUtil.BASE + "/cust/getCustInfo";
	myUtil.ajax.axs(url,null,getInfoSuccess);
}

/**
 * 获取用户最先到期的租期信息
 */
function getRoomRentVoList(){
	var url = myUtil.BASE + "/custcenter/getLastCustRent";
	myUtil.ajax.axs(url,null,function(result){
		if(!result){
			return;
		}
		var datas = result.datas;
		var html = "";
		if(datas){
			$("#room-date").html("");
			html += myUtil.dateFormat(datas.endTime, "yyyy-MM-dd")+'<i class="iconfont icon-arrowright fr you"></i>';
			$("#room-date").append(html);
		}
	});
}

function getAccountSuccess(result){
	if(!result){
		return;
//		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	if(datas){
		$("#account").html("");
		html += '<ul><li><a href="#" class="clearfloat"><p>￥'+datas.payAmount+'</p><p>余额</p></a></li>';
		html += '<li><a href="#" class="clearfloat"><p>￥'+datas.freezeAmount+'</p><p>押金</p></a></li>';
		html += '<li><a href="#" class="clearfloat"><p>￥'+datas.dueAmount+'</p><p>滞纳金</p></a></li></ul>';
		$("#account").append(html);
	}
}

function getOrderSuccess(result){
	if(!result){
		return;
	}
	var datas = result.datas;
	if(datas){
		$("#pay-finish-size").html(datas.payFinishSize);
		$("#pay-wait-size").html(datas.payWaitSize);
		$("#rent-order-size").html(datas.rentOrderSize);
	}
}

function getInfoSuccess(result){
	if(!result){
		return;
	}
	var datas = result.datas;
	if(datas){
		$("#nick-name").html(datas.nickName);
	}
}

//跳转到我的资料
function toMyInformation() {
	window.location.href= "datamaintain.html"; 
}

// 跳转到我的二维码
function toMyQrCode() {
	window.location.href = 'myqrcode.html?userId=' + myUtil.CMCUST.custId;
}