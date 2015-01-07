/**
 * 
 */
var adminUserList = function(){};


adminUserList.idformatter = function(data, record){
	return '<input type="checkbox" value="' + record.id +'"/>';
};

adminUserList.operationFormatter = function(value, row, index){
	return ['<a href="javascript:void(0)" onclick="adminUserList.operationClick()" >删除用户</a>',
	        '<a href="javascript:void(0)" onclick="adminUserList.operationClick()" >重置密码</a>',
	        '<a href="javascript:void(0)" onclick="adminUserList.operationClick()" >修改名称</a>'].join('&nbsp;');
};

adminUserList.operationClick = function(){
	alert(1)
}