/**
 * 页面初始化
 */
$(function() {
	getPaydetailDatasList();
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 获取近半年缴费明细
 */
function getPaydetailDatasList(){
	var url = myUtil.BASE + "/paydetail/getPayDetail";
	myUtil.ajax.axs(url,null,getPaydetailListSuccess);
}

function getPaydetailListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	$("#paydetail").html("");
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
            html += '<dl class="list clearfloat box-s fl"><dt><p class="fl">'+myUtil.getSystemParamTitle("PAY_DETAIL", "TYPE", item.type)+'[流水号：'+item.payDetailId+']</p>';
            html += '<span class="fr">-'+item.amount+'</span></dt><dd>'+myUtil.dateFormat(item.createTime, "yyyy-MM-dd hh:mm")+'</dd></dl>';
        });
	}else{
        html += '<div class="empty-list clearfloat" id="main"><i class="iconfont icon-meineirong"></i><p>还没有缴费信息哦！</p></div>';
		myUtil.toast('目前还没有缴费信息哦！');
	}
	$("#paydetail").append(html);
}