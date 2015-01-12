/**
 * 
 */
var login = function(){};

login.formSub = function(){
	var b = new Base64();  
	var username = b.encode($('#username').val());
	var password = b.encode($('#password').val());
	$('#username').val(username);
	$('#password').val(password);
	$('#loginForm').submit();
}