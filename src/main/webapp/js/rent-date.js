var _tabIndex = 1;
/**
 * 页面初始化
 */
$(function() {
	getDatasList(1);
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 获取租期信息
 */
function getDatasList(index){
    var url = "";
    _tabIndex = index;
    if(_tabIndex == 1){
        url = myUtil.BASE + "/custcenter/getPaidCustRent";
    }else if(_tabIndex == 2){
        url = myUtil.BASE + "/custcenter/getFinishCustRent";
    }else if(_tabIndex == 3){
        url = myUtil.BASE + "/custcenter/getCustRent";
    }
	myUtil.ajax.axs(url,null,getListSuccess);
}

function getListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
    var tabId = "";
    if(_tabIndex == 1){
        tabId = "rent-paid";
    }else if(_tabIndex == 2){
        tabId = "rent-finish";
    }else if(_tabIndex == 3){
        tabId = "rent-all";
    }
    $("#"+tabId).html("");
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
			html += '<div class="content clearfloat box-s">';
			html += '<div class="topsche-top box-s clearfloat"><p class="fl add"><i class="iconfont icon-time"></i>&nbsp;到期时间：'+myUtil.dateFormat(item.endTime, "yyyy-MM-dd")+'</p>';
			if(_tabIndex == 3){
				if(item.rentOrderState == RENT_ORDER.getRentOrderState("WAIT")){
                    html += '<p class="tit fr">';
                    html += '待支付</p></div>';
				}else if(item.rentOrderState == RENT_ORDER.getRentOrderState("PAID")){
                    html += '<p class="tit titwo fr">';
                    html += '履行中</p></div>';
                }else if(item.rentOrderState == RENT_ORDER.getRentOrderState("FINISH")){
                    html += '<p class="tit tifour fr">';
                    html += '已完成</p></div>';
                }else if(item.rentOrderState == RENT_ORDER.getRentOrderState("CANCEL")){
                    html += '<p class="tit tithree fr">';
                    html += '取消</p></div>';
                }else{
                    html += '<p class="tit tithree fr">';
                    html += '其他</p></div>';
				}
			}else{
                html += '<p class="fl time"></p></div>';
			}
			html += '<div class="list clearfloat fl box-s"><a href="room-detail.html?rentId='+item.rentId+'">';
			html += '<div class="tu clearfloat"><span></span><img src="'+item.bedroomImageUrl+'"/></div>';
			html += '<div class="right clearfloat"><div class="tit clearfloat"><p class="fl">'+item.name+'</p>';
			html += '<span class="fr">'+item.rentAmount+'<samp>元/月</samp></span></div><p class="recom-jianjie">'+item.roomType+'   |  '+item.space+'  |  '+item.decorate+'</p>';
			html += '<div class="recom-bottom clearfloat"><span><i class="iconfont icon-duihao"></i>随时住</span>';
			html += '<span><i class="iconfont icon-duihao"></i>家电齐全</span></div></div></a></div>';
			html += '<div class="topsche-top entrust box-s clearfloat">';
			html += '<a href="#" class="tit fr entrust-btn">续租</a><a href="rent-transfer.html" class="tit fr entrust-btn">转租</a></div></div>';
		});
	}else{
		html += '<div class="empty-list clearfloat" id="main"><i class="iconfont icon-meineirong"></i><p>还没有内容哦！</p></div>';
		//myUtil.toast('空数据');
	}
    $("#"+tabId).append(html);
}
