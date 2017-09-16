var LOCATION_MAP = {
	"不限":0,
	"地铁":{
		"1号线":{
			"西单":"1",
            "天安门":"2",
            "东单":"3"
		},
        "5号线":{
            "天通苑":"1",
            "立水桥":"2",
            "北苑南":"3"
        }
	},
	"区域":{
        "朝阳区":{
            "朝阳商圈1":"1",
            "朝阳商圈2":"2",
            "朝阳商圈3":"3",
            "朝阳商圈4":"4"
        },
        "海淀区":{
            "海淀商圈1":"1",
            "海淀商圈2":"2",
            "海淀商圈3":"3",
            "海淀商圈4":"4"
        }
	}
}
/**
 * 页面初始化
 */
$(function() {
	myUtil.menu(1);
    init();
	getDatasList();
	$("#oe_overlay").hide();

    for(var key in LOCATION_MAP){
        console.log("属性：" + key + ",值："+ LOCATION_MAP[key]);
        if(LOCATION_MAP[key]){
            for(var key1 in LOCATION_MAP[key]){
                console.log("       " + key1 + ",值："+ LOCATION_MAP[key][key1]);
                if(LOCATION_MAP[key][key1]){
                    for(var key2 in LOCATION_MAP[key][key1]){
                        console.log("                " + key2 + ",值："+ LOCATION_MAP[key][key1][key2]);
                    }
                }
            }
        }
    }
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
	myUtil.ajax.axs(url,null,getListSuccess);
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

function init(){
    //添加租住类型事件
    $('#rent-type').on('click','li',function (e){
        var content = $(this).children().html();
        $("#rent-type-href").html(content+"<i></i>");
        $("#rent-type-href").click();
    });
	//添加区域筛选事件
    $('#condition-area').on('click','li',function (e){
        var content = $(this).children().html();
        $("#condition-area-href").html(content+"<i></i>");
        $("#condition-area-href").click();
    });
	//添加价格筛选事件
    $('#condition-price').on('click','li',function (e){
        var content = $(this).children().html();
        $("#condition-price-href").html(content+"<i></i>");
        $("#condition-price-href").click();
    });
	//添加排序筛选事件
    $('#condition-sort').on('click','li',function (e){
        var content = $(this).children().html();
        $("#condition-sort-href").html(content+"<i></i>");
        $("#condition-sort-href").click();
    });
}

