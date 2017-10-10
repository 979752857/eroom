var ROOM_RENT_STATE_APPLING = "00";
var ROOM_RENT_APPLING = "00";
var ROOM_RENT = (function() {
	var APPLY_STATE = {//定义了两个常量
		APPLING	: "00",
		AGREE	: "01",
		REFUSE	: "02",
		FINISH	: "03",
		LOOKING	: "04",
		CANCEL	: "09",
		TIMEOUT	: "08"
	}
	return {
		getApplyState:function(name){
			return APPLY_STATE[name];
		}
	}
})();
var PAY_ORDER = (function() {
	var PAY_ORDER_STATE = {//定义了两个常量
        WAIT	: "01",
        FINISH	: "02",
        CANCEL	: "03",
		FAIL	: "04"
	}
	return {
		getPayOrderState:function(name){
			return PAY_ORDER_STATE[name];
		}
	}
})();
var RENT_ORDER = (function() {
	var RENT_ORDER_STATE = {//定义了两个常量
        WAIT	: "02",
        PAID	: "03",
        FINISH	: "04",
        CANCEL	: "05"
	}
	return {
		getRentOrderState:function(name){
			return RENT_ORDER_STATE[name];
		}
	}
})();
var ROOM_TASK = (function() {
	var TYPE = {//定义了两个常量
        TASK	: "01",
        ASSESS	: "02"
	}
	var STATE = {//定义了两个常量
        WAITING	: "00",
        FINISH	: "01",
        FIAL	: "02"
	}
	return {
		getType:function(name){
			return TYPE[name];
		},
		getState:function(name){
			return STATE[name];
		}
	}
})();