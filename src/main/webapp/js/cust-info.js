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
 * 获取用户个人信息
 */
function getDatasList(){
	var url = myUtil.BASE + "/cust/getCustInfo";
	myUtil.ajax.axs(url,null,getListSuccess);
}

function getListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	if(datas){
		$("#nick-name").val(datas.nickName);
		$("#name").val(datas.name);
		$("#sex").val(datas.sex);
		$("#phone").val(datas.phone);
		$("#email").val(datas.email);
		$("#qq").val(datas.qq);
		$("#wechat").val(datas.wechat);
	}else{
		myUtil.toast('哎呀，没有找到您的信息，请重试！');
	}
}
