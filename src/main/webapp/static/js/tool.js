/**
 * 
 */

var tool = function() {
};

/**
 * 提示框
 */
tool.alert = function (message){
	var msg = $.globalMessenger().post({
		message: message,
		type: 'info',
		showCloseButton: true,
		hideAfter: 3
	});
};