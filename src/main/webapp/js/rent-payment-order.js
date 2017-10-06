var _payOrderId = 0;
var _canPay = false;
/**
 * 页面初始化
 */
$(function() {
    _payOrderId = myUtil.getUrlParam("payOrderId");
    getPayOrder();
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 提交租住订单
 */
function getPayOrder(){
	var url = myUtil.BASE + "/payorder/getPayRentOrder";
	var data = {
        payOrderId:_payOrderId
	}
	myUtil.ajax.axs(url,data,getPayOrderSuccess);
}

/**
 * 支付租住订单
 */
function payPayOrder(){
    if(!_canPay){
        myUtil.toast('支付订单异常请刷新重试！');
        return;
    }
	var url = myUtil.BASE + "/payorder/payRentOrder";
	var data = {
        payOrderId:_payOrderId
	}
	myUtil.ajax.axs(url,data,payPayOrderSuccess);
}

function getPayOrderSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var subMoney = parseFloat(0);
	if(datas){
        $("#final-money").html((parseFloat(datas.rentAmount)+parseFloat(datas.mortgageAmount)+parseFloat(datas.lateAmount)-subMoney)+"元");
        _canPay = true;
	}else{
		myUtil.toast('哎呀，没有获取到支付订单请刷新重试！');
	}
}

function payPayOrderSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	if(datas){
        if(datas.payOrderState == PAY_ORDER.getPayOrderState("FINISH")){
            toSuccess();
        }else{
            myUtil.toast('哎呀，支付失败了！');
        }
	}else{
		myUtil.toast('哎呀，没有获取到支付订单请刷新重试！');
	}
}

function toSuccess(){
    window.location.href = "rent-success-order.html?payOrderId="+_payOrderId;
}