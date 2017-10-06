var _rentOrderId = 0;
var _payOrderId = 0;
var _rentId = 0;
/**
 * 页面初始化
 */
$(function() {
    _rentOrderId = myUtil.getUrlParam("rentOrderId");
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
	var url = myUtil.BASE + "/payorder/getPayRentOrderByRentOrderId";
	var data = {
        rentOrderId:_rentOrderId
	}
	myUtil.ajax.axs(url,data,getPayOrderSuccess);
}

function getPayOrderSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var subMoney = parseFloat(0);
	if(datas){
        _payOrderId = datas.payOrderId;
        _rentId = datas.rentId;
        $("#pay-phase").html((datas.phase+1)+"期房租");
        $("#rent-info").html(datas.name);
        $("#rent-address").html(datas.address);
        $("#rent-money").html("¥"+parseFloat(datas.rentAmount));
        $("#mortgage-money").html("¥"+parseFloat(datas.mortgageAmount));
        $("#late-money").html("¥"+parseFloat(datas.lateAmount));
        $("#total-money").html("¥"+(parseFloat(datas.rentAmount)+parseFloat(datas.mortgageAmount)+parseFloat(datas.lateAmount)));
        $("#sub-money").html("¥"+subMoney);
        $("#final-money").html("¥"+(parseFloat(datas.rentAmount)+parseFloat(datas.mortgageAmount)+parseFloat(datas.lateAmount)-subMoney));
	}else{
		myUtil.toast('哎呀，没有获取到支付订单请重新下单！');
	}
}

function toPayment(){
    window.location.href = "rent-payment-order.html?payOrderId="+_payOrderId;
}

function goBack(){
    window.location.href = "room-detail.html?rentId="+_rentId;
}

(function($, doc) {
    $.init();
    $.ready(function() {
        //普通示例
        var userPicker = new $.PopPicker();
        userPicker.setData([{
            value: '',
            text: '10元优惠券'
        }, {
            value: '',
            text: '20元优惠券'
        }, {
            value: '',
            text: '50元优惠券'
        }, {
            value: '',
            text: '30元优惠券'
        }, {
            value: '',
            text: '70元优惠券'
        }, {
            value: '',
            text: '100元优惠券'
        }, {
            value: '',
            text: '20元优惠券'
        }, {
            value: '',
            text: '15元优惠券'
        }, {
            value: '',
            text: '50元优惠券'
        }, {
            value: '',
            text: '80元优惠券'
        }]);
        var showUserPickerButton = doc.getElementById('showUserPicker');
        var userResult = doc.getElementById('userResult');
        showUserPickerButton.addEventListener('tap', function(event) {
            userPicker.show(function(items) {
                userResult.innerText = JSON.stringify(items[0]);
                //返回 false 可以阻止选择框的关闭
                //return false;
            });
        }, false);


    });
})(mui, document);