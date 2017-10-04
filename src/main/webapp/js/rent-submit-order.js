var _rentId = 0;
var _rent_time_type = "01";
/**
 * 页面初始化
 */
$(function() {
    _rentId = myUtil.getUrlParam("rentId");
    getValidRentOrder();
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 提交租住订单
 */
function saveRentOrder(){
	var url = myUtil.BASE + "/rentorder/saveRentOrder";
	var data = {
		rentId:_rentId,
        rentTimeType:_rent_time_type
	}
	myUtil.ajax.axs(url,data,getDataSuccess);
}

/**
 * 获取出租信息
 */
function getOrderDatas(){
	var url = myUtil.BASE + "/roomrent/getRentingRentId";
	var data = {
		rentId:_rentId
	}
	myUtil.ajax.axs(url,data,getOrderDataSuccess);
}

/**
 * 获取用户有效的租房信息
 */
function getValidRentOrder(){
	var url = myUtil.BASE + "/rentorder/getValidRentOrder";
	var data = {
		rentId:_rentId
	}
	myUtil.ajax.axs(url,data,getValidOrderDataSuccess);
}

function getDataSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	if(datas){
        myUtil.toast('订单提交成功！');
        window.location.href = "rent-confirm-order.html?rentOrderId="+datas.rentOrderId;
	}else{
		myUtil.toast('订单提交失败，请重试！');
	}
}

function getOrderDataSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	if(datas){
		$("#rent-info").html(datas.name);
		$("#rent-address").html(datas.address);
		$("#start-time").html(myUtil.dateFormat(new Date(), "yyyy-MM-dd"));
        var now = new Date();
        now.setFullYear(now.getFullYear()+1);
        $("#end-time").html(myUtil.dateFormat(now, "yyyy-MM-dd"));
		$("#mortgage").html(datas.price+"元");
		$("#price").html(datas.price+"元");
		$("#otherPrice").html("200"+"元");
		$("#totlePrice").html("3200"+"元");
		console.info(datas);
	}else{

	}
	$("#paydetail").append(html);
}

function getValidOrderDataSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	if(datas){
		//用户存在有效信息，进行跳转   传rentOrderId
        window.location.href = "rent-confirm-order.html?rentOrderId="+datas.rentOrderId;
	}else{
        getOrderDatas();
	}
}

function getTimeLength(type){
	var date = new Date();
    var dateStr = dateFormat(date, "yyyy-MM-dd");
    dateStr += " 12:00:00";
    var startTime = dateFormat(date, "yyyy-MM-dd");
    var endTime = "";
    date = new Date(Date.parse(dateStr.replace(/-/g,"/")));
	if(type == 1){		//年租
        date = dateAdd(date, "y", 1);
		console.info(dateFormat(date, "yyyy-MM-dd"));
	}else if(type == 2){		//月租
        date = dateAdd(date, "m", 1);
        console.info(dateFormat(date, "yyyy-MM-dd"));
	}else if(type == 3){		//周租
        date = dateAdd(date, "w", 1);
        console.info(dateFormat(date, "yyyy-MM-dd"));
    }else if(type == 4){		//三日租
        date = dateAdd(date, "d", 3);
        console.info(dateFormat(date, "yyyy-MM-dd"));
    }
    endTime = dateFormat(date, "yyyy-MM-dd");
	$("#start-time").html(startTime);
	$("#end-time").html(endTime);
}

(function($, doc) {
    $.init();
    $.ready(function() {
        var userPicker = new $.PopPicker();
        userPicker.setData([{
            value: '01',
            text: '年租'
        }, {
            value: '02',
            text: '月租'
        }, {
            value: '03',
            text: '周租'
        }, {
            value: '04',
            text: '三日租'
        }]);
        var showUserPickerButton = doc.getElementById('showUserPicker');
        var userResult = doc.getElementById('rent-type');
        showUserPickerButton.addEventListener('tap', function(event) {
            userPicker.show(function(items) {
				var typeJson = JSON.stringify(items[0]);
                var type = eval('(' + typeJson + ')');
                userResult.value = type.text;
                _rent_time_type = type.value;
                getTimeLength(type.value);
            });
        }, false);
    });
})(mui, document);