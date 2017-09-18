var _rentId;
var collectId;
/**
 * 页面初始化
 */
$(function() {
	_rentId = myUtil.getUrlParam('rentId');
	init();
	getDatasList(_rentId);
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 初始化
 */
function init(){
	getCollectDatas();
	$('#collect').click(function(){
		changeCollectDatas();
	});
	$("#sign").attr('href','rent-submit-order.html?rentId='+_rentId);
}

/**
 * 获取房源是否收藏信息
 */
function getCollectDatas(){
	var url = myUtil.BASE + "/custcenter/getCollectByRentid";
	var data = {rentId : _rentId};
	myUtil.ajax.axs(url,data,function(result){
		if(!result){
			return;
		}
		var datas = result.datas;
		if(datas){
			collectId = datas.collectId;
			changeCollect(1);
		}
	});
}

/**
 * 申请预约看房
 */
function applyBook(){
	var url = myUtil.BASE + "/roombook/applyBook";
	var time = new Date();
	var dateStr = $("#book-date").val();
	var day = time.getDate();
	if(dateStr == "明天"){
		time.setDate(day+1);
	}else if(dateStr == "后天"){
		time.setDate(day+2);
	}
	time = myUtil.dateFormat(time, "yyyy-MM-dd")
	var timeStr = $("#book-time").val();
	time = time + " " + timeStr + ":00:00";
	var data = {rentId : _rentId, time : time};
	myUtil.ajax.axs(url,data,function(result){
		if(!result){
			return;
		}
		if(result.success){
			$("#loginmodal").hide();
			$("#lean_overlay").hide();
			myUtil.toast('预约成功，请准时看房哦！');
		}
	});
}

/**
 * 修改房源收藏信息
 */
function changeCollectDatas(){
	var url = myUtil.BASE;
	var data = null;
	var flag = -1;
	if($('#collect').children('i').hasClass("iconfont icon-favorite")){
		url += "/custcenter/addCollect";
		flag = 1;
		data = {rentId : _rentId};
	}else{
		url += "/custcenter/deleteCollect";
		flag = 0;
		data = {collectId : collectId};
	}
	myUtil.ajax.axs(url,data,function(result){
		if(!result){
			return;
		}
		var datas = result.datas;
		if(result.success){
			if(flag == 1){
				collectId = datas.collectId;
			}
			changeCollect(flag);
		}
	});
}

/**
 * 获取房源详细信息
 */
function getDatasList(rentId){
	var url = myUtil.BASE + "/roomdetail/getRoomDetail";
	var data = {rentId : rentId};
	myUtil.ajax.axs(url,data,getDataSuccess);
}

function getDataSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	if(datas){
		$("#rentinfo").html("");
		html += '<p class="tit titwo">'+datas.price+'<span>元/月</span></p>';
		html += '<p class="fu-tit">'+datas.roomType+'&nbsp;|&nbsp;'+datas.space+'m²&nbsp;|&nbsp;'+datas.decorate+'</p>';
		$("#rentinfo").append(html);
		html = "";
		$("#roominfo").html("");
		html += '<ul><li>类型：'+datas.buildType+'</li><li>朝向：'+datas.direction+'</li><li>楼层：'+datas.floor+'</li>';
		html += '<li>供暖：'+datas.heating+'</li><li>建造年代：'+datas.year+'</li><li>环境：'+datas.environment+'</li><li>绿化：'+datas.greening+'</li></ul>';
		html += '<p class="service-tit">提示：易入不会要求您给任何人转账缴费，谨防诈骗哦！</p>';
		$("#roominfo").append(html);
		getConfig(datas.config)
		$("#aroundinfo").html(datas.aroundInfo);
		$("#trafficeinfo").html(datas.traffice);
	}
}

//解析配置内容并填充到页面中
function getConfig(config){
	var strs= new Array(); //定义一数组 
	strs=config.split(","); //字符分割 
	var html = "";
	$("#configinfo").html("");
	html += '<ul>';
	for (i=0;i<strs.length ;i++ ){ 
		if(strs[i] == '01'){
			html += '<li><i class="iconfont icon-wifi"></i>网络</li>';
		}else if(strs[i] == '02'){
			html += '<li><i class="iconfont icon-xiyouyanji"></i>油烟机</li>';
		}else if(strs[i] == '03'){
			html += '<li><i class="iconfont icon-weibolu"></i>微波炉</li>';
		}else if(strs[i] == '04'){
			html += '<li><i class="iconfont icon-7"></i>燃气</li>';
		}else if(strs[i] == '05'){
			html += '<li><i class="iconfont icon-xiyiji"></i>洗衣机</li>';
		}else if(strs[i] == '06'){
			html += '<li><i class="iconfont icon-reshui"></i>热水器</li>';
		}else if(strs[i] == '07'){
			html += '<li><i class="iconfont icon-bingxiang"></i>冰箱</li>';
		}else if(strs[i] == '08'){
			html += '<li><i class="iconfont icon-dpc"></i>衣柜</li>';
		}else if(strs[i] == '09'){
			html += '<li><i class="iconfont icon-chuangdian"></i>床</li>';
		}else if(strs[i] == '10'){
			html += '<li><i class="iconfont icon-bangongzhuo"></i>书桌</li>';
		}else if(strs[i] == '11'){
			
		}else if(strs[i] == '12'){
			html += '<li><i class="iconfont icon-kongdiao"></i>空调</li>';
		}else if(strs[i] == '13'){
			html += '<li><i class="iconfont icon-yizi"></i>座椅</li>';
		}
	} 
	html += '</ul>';
	$("#configinfo").append(html);
}

//修改收藏显示信息0未收藏 1已收藏
function changeCollect(flag){
	var collect = $('#collect');
	if(flag == 0){
		if(collect.children('i').hasClass("iconfont icon-shoucang1")){
			collect.children('i').removeClass("iconfont icon-shoucang1");
			collect.children('i').addClass("iconfont icon-favorite");
			collect.children('i').css("color","");
			collect.children('p').html("收藏");
		}
	}else if(flag == 1){
		if(collect.children('i').hasClass("iconfont icon-favorite")){
			collect.children('i').removeClass("iconfont icon-favorite");
			collect.children('i').addClass("iconfont icon-shoucang1");
			collect.children('i').css("color","#ffa756");
			collect.children('p').html("已收藏");
		}
	}
}

//变更看房时间
function changeTime(flag){
	var timeId = $("#book-time");
	var time = timeId.val();
	if(flag == 0){
		if(time == 7){
			time = 23;
			timeId.val(time);
			return ;
		}
		time -= 1;
	}else if(flag == 1){
		if(time == 23){
			time = 7;
			timeId.val(time);
			return ;
		}
		time = parseInt(time)+1;
	}
	timeId.val(time);
	return ;
}

//变更看房日期
function changeDate(flag){
	var dateId = $("#book-date");
	var date = dateId.val();
	if(flag == 0){
		if(date == "今天"){
			date = "后天";
		}else if(date == "明天"){
			date = "今天";
		}else if(date == "后天"){
			date = "明天";
		}
	}else if(flag == 1){
		if(date == "今天"){
			date = "明天";
		}else if(date == "明天"){
			date = "后天";
		}else if(date == "后天"){
			date = "今天";
		}
	}
	dateId.val(date);
	return;
}