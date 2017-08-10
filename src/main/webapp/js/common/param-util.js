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