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

/**
 * 修改用户个人信息
 */
function updateDatas(){
	var nickName = $("#nick-name").val();
	var name = $("#name").val();
	var sex = $("#sex").val();
	var phone = $("#phone").val();
	var email = $("#email").val();
	var qq = $("#qq").val();
	var wechat = $("#wechat").val();
	var data = {
		nickName : nickName,
		name : name,
		sex : sex,
		phone : phone,
		email : email,
		qq : qq,
		wechat : wechat
	};
	var url = myUtil.BASE + "/cust/updateCustInfo";
	myUtil.ajax.axs(url,data,updateDataSuccess);
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

function updateDataSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	if(datas){
		myUtil.toast('个人信息修改成功');
	}else{
		myUtil.toast('哎呀，没有找到您的信息，请重试！');
	}
}
