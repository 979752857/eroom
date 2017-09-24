var _rentOrderId = 0;
/**
 * 页面初始化
 */
$(function() {
    _rentId = myUtil.getUrlParam("rentOrderId");
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
		rentOrderId:_rentOrderId
	}
	myUtil.ajax.axs(url,data,getPayOrderSuccess);
}

function getPayOrderSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas.list;
	var html = "";
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
            html += '<dl class="list clearfloat box-s fl"><dt><p class="fl">'+myUtil.getSystemParamTitle("PAY_DETAIL", "TYPE", item.type)+'[流水号：'+item.payDetailId+']</p>';
            html += '<span class="fr">-'+item.amount+'</span></dt><dd>'+myUtil.dateFormat(item.createTime, "yyyy-MM-dd hh:mm")+'</dd></dl>';
        });
        _data_paydetail.page = _data_paydetail.page+1;
	}else{
        html += '<div class="empty-list clearfloat" id="main"><i class="iconfont icon-meineirong"></i><p>还没有缴费信息哦！</p></div>';
		myUtil.toast('目前还没有缴费信息哦！');
	}
	$("#paydetail").append(html);
    checkLoadMore(result.datas, _data_paydetail);
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