var LOCATION_MAP = {
	// "不限":0,
	// "地铁":{
     //    "不限":0,
	// 	"1号线":{
     //        "不限":0,
	// 		"西单":"1",
     //        "天安门":"2",
     //        "东单":"3"
	// 	},
     //    "5号线":{
     //        "不限":0,
     //        "天通苑":"1",
     //        "立水桥":"2",
     //        "北苑南":"3"
     //    }
	// },
	// "区域":{
     //    "不限":0,
     //    "朝阳区":{
     //        "不限":0,
     //        "朝阳商圈1":"1",
     //        "朝阳商圈2":"2",
     //        "朝阳商圈3":"3",
     //        "朝阳商圈4":"4"
     //    },
     //    "海淀区":{
     //        "不限":0,
     //        "海淀商圈1":"1",
     //        "海淀商圈2":"2",
     //        "海淀商圈3":"3",
     //        "海淀商圈4":"4"
     //    }
	// }
}
var DATA = {
    type : "",
    position : "",
    price : "",
    sort : ""
}
var keyArray = new Array("不限","不限","不限")
/**
 * 页面初始化
 */
$(function() {
	myUtil.menu(1);
    getCondition();
    // init();
	getDatasList();
	$("#oe_overlay").hide();
});
$(window).load(function(){
	$(".loading").addClass("loader-chanage")
	$(".loading").fadeOut(300)
})

/**
 * 获取房源信息
 */
function getDatasList(){
	var url = myUtil.BASE + "/room/getRoom";
	myUtil.ajax.axs(url,DATA,getListSuccess);
}

/**
 * 获取房源信息
 */
function getCondition(){
	var url = myUtil.BASE + "/base/getAllCondition";
	var data = {
	    cityId : 1
    }
	myUtil.ajax.axs(url,data,getDataSuccess);
}

function getListSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	var html = "";
	$("#room").html("");
	if(datas&&datas.length>0){
		$.each(datas,function(index,item){
			html += '<div class="list clearfloat fl box-s"><a href="room-detail.html?rentId='+item.id+'"><div class="tu clearfloat">';
			html += '<span></span><img src="'+item.bedroomImageUrl+'"/></div><div class="right clearfloat">';
			html += '<div class="tit clearfloat"><p class="fl">'+item.name+'</p><span class="fr">'+item.price+'<samp>元/月</samp></span></div>';
			html += '<p class="recom-jianjie">'+item.roomType+'   |  '+item.space+'  |  '+item.decorate+'</p><div class="recom-bottom clearfloat">';
			html += '<span><i class="iconfont icon-duihao"></i>随时住</span><span><i class="iconfont icon-duihao"></i>家电齐全</span>';
			html += '</div></div></a></div>';
		});
	}else{
		myUtil.toast('空数据');
	}
	$("#room").append(html);
}

function getDataSuccess(result){
	if(!result){
		myUtil.toast('服务器繁忙请重试');
	}
	var datas = result.datas;
	if(datas){
        LOCATION_MAP = datas;
        initAreaOndition();
	}else{
		myUtil.toast('未获取到位置条件信息');
	}
}

function init(){

    //添加租住类型事件
    $('#rent-type').on('click','li',function (e){
        var content = $(this).children().html();
        $("#rent-type-href").html(content+"<i></i>");
        // $("#rent-type-href").click();
        DATA.type = content;
        console.info("rent-type:"+content);
        changeCondition($("#rent-type-href"));
    });
	//添加区域筛选事件
    $('#condition-area').on('click','li',function (e){
        console.info(e.target);
        var content = $(this).children().html();
        $("#condition-area-href").html(content+"<i></i>");
        keyArray[0] = content;
        keyArray[1] = "不限";
        keyArray[2] = "不限";
        console.info("condition-area:"+content);
        if(content == "不限"){
            // $("#condition-area-href").click();
            changeCondition($("#condition-area-href"));
        }else{
            DATA.position = LOCATION_MAP[keyArray[0]]["不限"];
            initAreaOndition();
            $('#condition-area-second').show();
            $('#condition-area-third').hide();
        }
        return false;
    });
    $('#condition-area-second').on('click','li',function (e){
        var content = $(this).children().html();
        keyArray[1] = content;
        keyArray[2] = "不限";
        console.info("condition-area-second:"+content);
        if(content == "不限"){
            // $("#condition-area-href").click();
            changeCondition($("#condition-area-href"));
        }else{
            $("#condition-area-href").html(content+"<i></i>");
            DATA.position = LOCATION_MAP[keyArray[0]][keyArray[1]]["不限"];
            initAreaOndition();
            $('#condition-area-third').show();
        }
        return false;
    });
    $('#condition-area-third').on('click','li',function (e){
        var content = $(this).children().html();
        keyArray[2] = content;
        console.info("condition-area-third:"+content);
        if(content != "不限"){
            $("#condition-area-href").html(content+"<i></i>");
        }
        // $("#condition-area-href").click();
        DATA.position = LOCATION_MAP[keyArray[0]][keyArray[1]][keyArray[2]];
        changeCondition($("#condition-area-href"));
        return false;
    });

	//添加价格筛选事件
    $('#condition-price').on('click','li',function (e){
        var content = $(this).children().html();
        $("#condition-price-href").html(content+"<i></i>");
        // $("#condition-price-href").click();
        DATA.price = content;
        console.info("condition-price:"+content);
        changeCondition($("#condition-price-href"));
    });
	//添加排序筛选事件
    $('#condition-sort').on('click','li',function (e){
        var content = $(this).children().html();
        $("#condition-sort-href").html(content+"<i></i>");
        // $("#condition-sort-href").click();
        DATA.sort = content;
        console.info("condition-sort:"+content);
        changeCondition($("#condition-sort-href"));
    });
}

function initAreaOndition(){
    $("#condition-area").unbind("click"); //移除click
    $("#condition-area-second").unbind("click"); //移除click
    $("#condition-area-third").unbind("click"); //移除click
    $("#rent-type").unbind("click"); //移除click
    $("#condition-price").unbind("click"); //移除click
    $("#condition-sort").unbind("click"); //移除click
    var html = "";
    for(var key in LOCATION_MAP){
        // console.log("属性：" + key + ",值："+ LOCATION_MAP[key]);
        html += "<li><a href='#'>"+key+"</a></li>";
    }
    html += "<li><ul id='condition-area-second' class='second-ul' style='display: none'></li>";
    $("#condition-area").html(html);
    if(keyArray[0] != "不限"){
        $("#condition-area").show();
        html = "";
        if(LOCATION_MAP[keyArray[0]]){
            for(var key1 in LOCATION_MAP[keyArray[0]]){
                // console.log("       " + key1 + ",值："+ LOCATION_MAP[keyArray[0]][key1]);
                html += "<li><a href='#'>"+key1+"</a></li>";
            }
            html += "<li><ul id='condition-area-third' class='third-ul' style='display: none'></li>";
            $("#condition-area-second").html(html);
            if(keyArray[1] != "不限"){
                $("#condition-area-second").show();
                html = "";
                if(LOCATION_MAP[keyArray[0]][keyArray[1]]){
                    for(var key2 in LOCATION_MAP[keyArray[0]][keyArray[1]]){
                        // console.log("                " + key2 + ",值："+ LOCATION_MAP[keyArray[0]][keyArray[1]][key2]);
                        html += "<li><a href='#'>"+key2+"</a></li>";
                    }
                    $("#condition-area-third").html(html);
                }
            }
        }
    }
    init();
}

function changeCondition(target){
    target.click();
    getDatasList();
    console.info("reload data......")
}




