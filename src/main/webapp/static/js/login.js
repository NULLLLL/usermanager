/**
 * 
 */
var login = function(){};

login.formSub = function(){
	var b = new Base64();  
	var password = b.encode($('#password').val());
	$('#password').val(password);
	$('#loginForm').submit();
}