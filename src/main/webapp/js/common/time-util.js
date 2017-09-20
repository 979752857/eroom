/**
 * 将一个年月日时分秒的字符串转化为月日显示 示例 2016-06-07 17:22:23 显示 2016年6月7日
 */
formatShow = function(d) {
	var dt = d.substring(0, 10);
	var arr = dt.split('-');
	var m = arr[1];
	if (m < 10) {
		m = arr[1].substring(1, 2);
	}
	var d = arr[2];
	if (d < 10) {
		d = arr[2].substring(1, 2);
	}
	return m + '月' + d + '日';
}

/**
 * 将一个年月日时分秒的字符串转化为月日显示 输入实例： dateFormat("2016-06-07 17:22:23", "yyyy-MM-dd
 * hh:mm"); 输出2016-06-07 17:22
 */
dateFormat = function(dateStr, fmt) {
	var date;
	if(dateStr instanceof Date){
		date = dateStr;
	}else{
		date = new Date(Date.parse(dateStr.replace(/-/g,"/")));
	}
	var o = {
		"M+" : date.getMonth() + 1,
		"d+" : date.getDate(),
		"h+" : date.getHours(),
		"m+" : date.getMinutes(),
		"s+" : date.getSeconds()
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}

dateAdd = function (date,strInterval, Number) {  //参数分别为日期对象，增加的类型，增加的数量
    var dtTmp = date;
    switch (strInterval) {
        case 'second':
        case 's' :
            return new Date(Date.parse(dtTmp) + (1000 * Number));
        case 'minute':
        case 'n' :
            return new Date(Date.parse(dtTmp) + (60000 * Number));
        case 'hour':
        case 'h' :
            return new Date(Date.parse(dtTmp) + (3600000 * Number));
        case 'day':
        case 'd' :
            return new Date(Date.parse(dtTmp) + (86400000 * Number));
        case 'week':
        case 'w' :
            return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
        case 'month':
        case 'm' :
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'year':
        case 'y' :
            return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
    }
}