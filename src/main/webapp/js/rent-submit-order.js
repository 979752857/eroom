var _rentId = 0;
/**
 * 页面初始化
 */
$(function() {
    _rentId = myUtil.getUrlParam("rentId");
    getOrderDatas();
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
		startTime:null,
		endTime:null,
		type:null
	}
	myUtil.ajax.axs(url,data,getPaydetailListSuccess);
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

function getPaydetailListSuccess(result){
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
            value: '1',
            text: '年租'
        }, {
            value: '2',
            text: '月租'
        }, {
            value: '3',
            text: '周租'
        }, {
            value: '4',
            text: '三日租'
        }]);
        var showUserPickerButton = doc.getElementById('showUserPicker');
        var userResult = doc.getElementById('rent-type');
        showUserPickerButton.addEventListener('tap', function(event) {
            userPicker.show(function(items) {
				var typeJson = JSON.stringify(items[0]);
                var type = eval('(' + typeJson + ')');
                userResult.value = type.text;
                getTimeLength(type.value);
            });
        }, false);


    });
})(mui, document);