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

