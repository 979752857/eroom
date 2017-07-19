(function(window, document) {
	var w = window, doc = document;
	var Kodo = function() {
	}

	// 创建一个loading框
	var createLoadingLayer = (function() {
		var div;
		return function() {
			if (!div) {
				div = document.createElement('div');
				div.style.display = 'none';

				var loadingBg = document.createElement('div');
				// loadingBg.className = 'loading-bg';
				div.appendChild(loadingBg);

				var loadingContent = document.createElement('div');
				loadingContent.className = 'loading-content';
				var img = document.createElement('img');
				img.src = '../images/hourglass.gif';
				loadingContent.appendChild(img);
				div.appendChild(loadingContent);
				document.body.appendChild(div);
			}
			return div;
		}
	})();

	var createAlert = (function() {
		var div;
		return function() {
			if (!div) {
				div = document.createElement('div');
				div.style.display = 'none';
				div.id = "myAlert";

				var loadingBg = document.createElement('div');
				loadingBg.className = 'alert-bg';
				div.appendChild(loadingBg);

				var alert = document.createElement('div');
				alert.className = 'alert';

				var message = document.createElement('p');
				message.id = "alertMessage";
				alert.appendChild(message);

				var button = document.createElement('button');
				button.innerHTML = "确&nbsp;&nbsp;定";
				button.id = "myAlertButton";
				button.className = "alert-button";
				alert.appendChild(button);

				div.appendChild(alert);
				document.body.appendChild(div);
			}
			return div;
		}
	})();

	var createConfirm = (function() {
		var div;
		return function() {
			if (!div) {
				div = document.createElement('div');
				div.style.display = 'none';
				div.id = "myConfirm";

				var loadingBg = document.createElement('div');
				loadingBg.className = 'alert-bg';
				div.appendChild(loadingBg);

				var alert = document.createElement('div');
				alert.className = 'alert';

				var message = document.createElement('p');
				message.id = "confirmMessage";
				alert.appendChild(message);

				var bottom = document.createElement('div');
				bottom.className = "bottom";

				var button1 = document.createElement('button');
				button1.innerHTML = "取消";
				button1.id = "myConfirmCancel";
				button1.className = "cancel";
				bottom.appendChild(button1);

				var button2 = document.createElement('button');
				button2.innerHTML = "确定";
				button2.id = "myConfirmButton";
				bottom.appendChild(button2);

				alert.appendChild(bottom);

				div.appendChild(alert);
				document.body.appendChild(div);
			}
			return div;
		}
	})();

	/** 下面为静态工具方法 */
	// 初始化方法
	Kodo.ready = function(fn) {
		doc.addEventListener('DOMContentLoaded', function() {
			// 页面加载后执行的方法
			fn && fn();
		}, false);
		doc.removeEventListener('DOMContentLoaded', fn, true);
	}

	// 获取url里的参数
	Kodo.getUrlParam = function(name) {
		var str = window.location.search;
		var index = str.indexOf("?" + name + "=");
		if (index == -1) {
			index = str.indexOf("&" + name + "=");
		}
		if (index == -1) {
			return null;
		}
		var end = str.indexOf("&", index + 1);
		if (end == -1) {
			return str.substring(index + 2 + name.length);
		} else {
			return str.substring(index + 2 + name.length, end);
		}
	}

	// 存储openid tenant
	var saveOpenid = (function() {
		var openid = Kodo.getUrlParam("openid");
		if (openid) {
			sessionStorage.setItem("$openid", openid);
		}
	})();
	
	// 判断当前所在浏览器
	Kodo.browser = {
		versions : function() {
			var u = navigator.userAgent, app = navigator.appVersion;
			return { 
				trident : u.indexOf('Trident') > -1, // IE内核
				presto : u.indexOf('Presto') > -1, // opera内核
				webKit : u.indexOf('AppleWebKit') > -1, // 苹果、谷歌内核
				gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,// 火狐内核
				mobile : !!u.match(/AppleWebKit.*Mobile.*/), // 是否为移动终端
				ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), // ios终端
				android : u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, // android终端或者uc浏览器
				iPhone : u.indexOf('iPhone') > -1, // 是否为iPhone或者QQHD浏览器
				iPad : u.indexOf('iPad') > -1, // 是否iPad
				webApp : u.indexOf('Safari') == -1, // 是否web应该程序，没有头部与底部
				weixin : u.indexOf('MicroMessenger') > -1, // 是否微信
				// （2015-01-22新增）
				qq : u.match(/\sQQ/i) == " qq" // 是否QQ
			};
		}(),
		language : (navigator.browserLanguage || navigator.language)
				.toLowerCase()
	};

	// 获取项目跟目录
	Kodo.BASE = (function() {
		// 获取当前网址
		var curWwwPath = window.document.location.href;
		// 获取主机地址之后的目录
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		// 获取主机地址
		var localhostPath = curWwwPath.substring(0, pos);
		// 获取带"/"的项目名
		var projectName = pathName.substring(0,
				pathName.substr(1).indexOf('/') + 1);
		return localhostPath; //(localhostPath + projectName);
	})();

	// 当前页面Url
	Kodo.CURRENTURL = (function() {
		return window.location.href;
	})();

	// url转换成UrlEncode
	Kodo.urlEncode = function(str) {
		return encodeURIComponent((str + '').toString()).replace(/!/g, '%21')
				.replace(/'/g, '%27').replace(/\(/g, '%28').replace(/\)/g,
						'%29').replace(/\*/g, '%2A').replace(/%20/g, '+');
	};

	// 获取二维码
	Kodo.getQrcode = function(ticket) {
		var qrCodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="
				+ Kodo.urlEncode(ticket);
		return qrCodeUrl;
	};

	// 微信Js配置
	Kodo.wxConfig = function() {
		var url = Kodo.BASE + "/config/getWechatConfig";
		var data = {
			userId : Kodo.getUrlParam("userId"),
			url : Kodo.CURRENTURL
		};
		$.ajax({
			type : "POST",
			data : data,
			url : url,
			dataType : "json",
			success : function(result) {
				if (result.success) {
					sessionStorage.setItem('$shareTitle',
							result.datas.systemBaseExt.shareTitle);
					sessionStorage.setItem('$shareDesc',
							result.datas.systemBaseExt.shareDesc);
					sessionStorage.setItem('$shareIconUrl',
							result.datas.systemBaseExt.shareIconUrl);
					wx.config({
						debug : false,
						appId : result.datas.jsApi.appId,
						timestamp : result.datas.jsApi.timestamp,
						nonceStr : result.datas.jsApi.nonceStr,
						signature : result.datas.jsApi.signature,
						jsApiList : [ 'onMenuShareTimeline','onMenuShareAppMessage' ]
					});
				}
			}
		})
	};

	/**
	 * 将一个年月日时分秒的字符串转化为月日显示 示例 2016-06-07 17:22:23 显示 2016年6月7日
	 */
	Kodo.formatShow = function(d) {
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
	Kodo.dateFormat = function(dateStr, fmt) {
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

	// loadingDialog的显示与隐藏
	Kodo.loadingShow = function() {
		var loadingLayer = createLoadingLayer();
		loadingLayer.style.display = 'block';
	}

	Kodo.loadingHidden = function() {
		var loadingLayer = createLoadingLayer();
		loadingLayer.style.display = 'none';
	}

	// 自动消失弹出框
	Kodo.toast = function(txt) {
		var toastTime = 3000;
		if (arguments.length == 2) {
			toastTime = arguments[1];
		}
		var c = "dialog-active";
		var d = document.createElement("div");
		d.classList.add("dialog-toast-container"),
				d.innerHTML = '<div class="dialog-toast-message">' + txt
						+ "</div>", d.addEventListener("webkitTransitionEnd",
						function() {
							d.classList.contains(c)
									|| d.parentNode.removeChild(d)
						}), document.body.appendChild(d), d.offsetHeight,
				d.classList.add(c), setTimeout(function() {
					d.classList.remove(c)
				}, toastTime)
	};

	/**
	 * 弹出框 message提示消息
	 * 
	 * 
	 */
	Kodo.alert = function() {
		var myAlert = createAlert();
		var param = arguments;
		document.getElementById('alertMessage').innerHTML = param[0];

		document.getElementById('myAlertButton').onclick = function() {
			document.getElementById('myAlert').style.display = 'none';
		}
		if (param.length == 2) {
			document.getElementById('myAlertButton').onclick = function() {
				document.getElementById('myAlert').style.display = 'none';
				param[1]();
			}
		}
		myAlert.style.display = 'block';
	};

	Kodo.confirm = function() {
		var confirm = createConfirm();
		var param = arguments;
		document.getElementById('confirmMessage').innerHTML = param[0];

		document.getElementById('myConfirmCancel').onclick = function() {
			document.getElementById('myConfirm').style.display = 'none';
		}

		document.getElementById('myConfirmButton').onclick = function() {
			document.getElementById('myConfirm').style.display = 'none';
			param[1]();
		}
		confirm.style.display = 'block';
	};

	/**
	 * 封装ajax
	 */
	Kodo.ajax = {
		/**
		 * ajax封装 url 发送请求的地址 data 发送到服务器的数据，数组存储，如：{"date": new
		 * Date().getTime(), "state": 1} successfn 成功回调函数
		 */
		axs : function(url, data, successfn) {
			Kodo.loadingShow();
			data = (data == null || data == "" || typeof (data) == "undefined") ? {
				"date" : new Date().getTime()
			}
					: data;
			$.ajax({
				type : "post",
				data : data,
				url : url,
				dataType : "json",
				success : function(result) {
					Kodo.loadingHidden();
					if (result.success) {
						successfn(result);
					} else if (result.code == 4848) {
						Kodo.alert("网络出问题啦~", function() {
							WeixinJSBridge.call('closeWindow')
						});
					} else {
						Kodo.toast(result.message);
					}
				},
				error : function(e) {
					Kodo.loadingHidden();
					Kodo.toast('网络异常，请稍后重试');
				}
			});
		},
		/**
		 * ajax封装 url 发送请求的地址 data 发送到服务器的数据，数组存储，如：{"date": new
		 * Date().getTime(), "state": 1} successfn 成功回调函数 errorfn 失败回调函数
		 */
		axx : function(url, data, successfn, errorfn) {
			Kodo.loadingShow();
			data = (data == null || data == "" || typeof (data) == "undefined") ? {
				"date" : new Date().getTime()
			}
					: data;
			$.ajax({
				type : "post",
				data : data,
				url : url,
				dataType : "json",
				success : function(result) {
					Kodo.loadingHidden();
					if (result.success) {
						successfn(result);
					} else if (result.code == 4848) {
						Kodo.alert("网络出问题啦~", function() {
							WeixinJSBridge.call('closeWindow');
						});
					} else {
						errorfn(result);
					}
				},
				error : function(e) {
					Kodo.loadingHidden();
					Kodo.toast('网络异常，请稍后重试');
				}
			});
		},
		/**
		 * ajax封装 url 发送请求的地址 data 发送到服务器的数据，数组存储，如：{"date": new
		 * Date().getTime(), "state": 1} successfn 成功回调函数
		 */
		axn : function(url, data, successfn) {
			data = (data == null || data == "" || typeof (data) == "undefined") ? {
				"date" : new Date().getTime()
			}
					: data;
			$.ajax({
				type : "post",
				data : data,
				url : url,
				dataType : "json",
				success : function(result) {
					if (result.success) {
						successfn(result);
					} else if (result.code == 4848) {
						Kodo.alert("网络出问题啦~", function() {
							WeixinJSBridge.call('closeWindow')
						});
					} else {
						Kodo.toast(result.message);
					}
				},
				error : function(e) {
					Kodo.toast('网络异常，请稍后重试');
				}
			});
		}
	};

	// 用户信息
	Kodo.CMCUST = (function() {
		var openid = sessionStorage.getItem("$openid");
		if (!openid) {
			return "";
		}
		var cmCust = sessionStorage.getItem(openid);
		if (cmCust) {
			return JSON.parse(cmCust);
		} else {
			var url = Kodo.BASE + "/custWx/getCustInfo";
			$.ajax({
				url : url,
				type : "POST",
				data : {
					"openid" : openid
				},
				async : false,
				dataType : "json",
				success : function(data) {
					if (data.datas) {
						var cust = JSON.stringify(data.datas);
						sessionStorage.setItem(openid, cust);
						cmCust = data.datas;
					} else {
						cmCust = null;
					}
				},
				error : function(e) {
					Kodo.toast('获取客户信息异常，请重新登录');
				}
			});
			return cmCust;
		}
	})();

	/**
	 * 显示指定长度的文字内容
	 */
	Kodo.showText = function(str, len) {
		if (!str || !((typeof str) == 'string')) {
			return '';
		}

		if (!len) {
			return str;
		}

		if (str.length <= len) {
			return str;
		}

		return str.substring(0, len) + '...';
	}
	
	// 存储翻译信息
	var _paramobj;
	var _kvobjstr = sessionStorage.getItem('_paramobj');
	if(!_kvobjstr){
		$.ajax({
			async : false,
			url : Kodo.BASE + '/systemParam/getAllSystemParam',
			type : 'post',
			success : function(obj) {
				if(obj.success && obj.datas){
					_paramobj = obj.datas;
					sessionStorage.setItem('_paramobj',JSON.stringify(_paramobj));
				}
			}
		});
	}else{
		_paramobj = JSON.parse(_kvobjstr);
	}
	
	//获取系统参数翻译
	Kodo.getSystemParamTitle = function(paramType,paramSubType,paramCode) {
		var key = paramType+'.'+paramSubType+'.'+paramCode;
		var rtndesc = _paramobj[key];
		return rtndesc;
	};
	
	//绑定手机号码
	Kodo.registerAuth = function(toUrl) {
		var authFlag = Kodo.CMCUST.authFlag;
		if(authFlag != 1) {
			window.location.href = 'bindphone.html?toUrl=' + toUrl;
		}
		return authFlag;
	}
	//加载loading
	function getInit(){
		//loading加载页面
		var loading =  '<div class="loading"><div class="loader"><div class="loader-inner pacman">'
					+ '<div></div><div></div><div></div><div></div><div></div></div></div></div>';
		$("body").append(loading);
	}
	//显示菜单
	function getMenu() {
		//菜单加载页面
		var menu = '<footer class="page-footer fixed-footer" id="footer"><ul>'
				+ '<li id="menu1" class="active"><a href="'+Kodo.BASE+'/html/index.html"><i class="iconfont icon-shouyev1"></i><p>首页</p></a></li>'
				+ '<li id="menu2"><a href="'+Kodo.BASE+'/html/rent-life.html"><i class="iconfont icon-chuzuwo"></i><p>租住生活</p></a></li>'
				+ '<li id="menu3"><a href="'+Kodo.BASE+'/html/personal-center.html"><i class="iconfont icon-gerenzhongxin"></i><p>个人中心</p></a></li>'
				+ '</ul></footer>';
		$("body").append(menu);
	}
	//显示菜单
	function getMenuOwner() {
		//菜单加载页面
		var menu = '<footer class="page-footer fixed-footer" id="footer"><ul>'
			+ '<li id="menu1" class="active"><a href="'+Kodo.BASE+'/html/rent-order.html"><i class="iconfont icon-chuzuwo"></i><p>订单</p></a></li>'
			+ '<li id="menu2"><a href="'+Kodo.BASE+'/html/rent-date.html"><i class="iconfont icon-shouyev1"></i><p>房源</p></a></li>'
			+ '<li id="menu3"><a href="'+Kodo.BASE+'/html/owner-center.html"><i class="iconfont icon-gerenzhongxin"></i><p>我的</p></a></li>'
			+ '</ul></footer>';
		$("body").append(menu);
	}
	Kodo.menu = function(num) {
		getMenu();
		if (num != 0) {
			$("#menu1").removeClass("active");
			$("#menu2").removeClass("active");
			$("#menu3").removeClass("active");
			$("#menu" + num).addClass("active");
		}
	};
	Kodo.menuOwner = function(num) {
		getMenuOwner();
		if (num != 0) {
			$("#menu1").removeClass("active");
			$("#menu2").removeClass("active");
			$("#menu3").removeClass("active");
			$("#menu" + num).addClass("active");
		}
	};

	// 显示菜单
	getInit();
	// 暴漏到外面的调用关键字
	window.myUtil = Kodo;

})(window, document);
