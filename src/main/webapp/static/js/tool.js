/**
 * 
 */

var tool = function() {
};

/**
 * 提示框
 */
tool.message = function (message){
	var msg = $.globalMessenger().post({
		message: message,
		type: 'info',
		showCloseButton: true
	});
};
