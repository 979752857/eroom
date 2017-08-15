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