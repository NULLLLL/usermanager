/**
 * 
 */
var ctx = '/usermanager';
var adminUserList = function() {
};

$(function() {
	
	adminUserList.initDataTable();
	
	$('#searchData').click(function() {
		adminUserList.searchTable();
	});
	$(window).resize(function() {
		$('#tableId').bootstrapTable('resetView');
	});
});

adminUserList.initDataTable = function() {
	$('#tableId').bootstrapTable({
		method : 'get',
		url : ctx + '/admin/userTable',
		cache : false,
		height : 400,
		queryParams : function(params) {
			return {
				params : sysutil.findFormData('#searchDiv :input')
			};
		},
		striped : true,
		pagination : true,
		pageNumber : 1,
		pageSize : 20,
		pageList : [ 10, 25, 50, 100 ],
		search : false,
		showColumns : true,
		showRefresh : false,
		minimumCountColumns : 2,
		clickToSelect : false,
		columns : [ {field : 'id',checkbox : true}, 
		            {field : 'name',title : '姓名',sortable : true,formatter : adminUserList.nameFormatter}, 
		            {field : 'loginName',title : '登录名',sortable : true}, 
		            {field : 'registerDate',title : '注册时间',sortable : true}, 
		            {field : 'id',title : '操作',formatter : adminUserList.operationFormatter} 
		            		]
	});
};

adminUserList.nameFormatter = function(data){
	return '<a style="cursor:pointer" title="点击查看详细信息">' + data + '</a>'
};

adminUserList.searchTable = function() {
	$('#tableId').bootstrapTable('refresh');
};

adminUserList.idformatter = function(data, record) {
	return '<input type="checkbox" value="' + record.id + '"/>';
};

adminUserList.operationFormatter = function(value, row, index) {
	return [
			'<button type="button" class="btn btn-danger" onclick="adminUserList.operationClick(0,'
					+ value + ')" >删除用户</button>',
			'<button type="button" class="btn btn-warning" onclick="adminUserList.operationClick(1,'
					+ value + ')" >重置密码</button>',
			'<button type="button" class="btn btn-info" onclick="adminUserList.operationClick(2,'
					+ value + ')" >修改信息</button>' ].join('&nbsp;');
};

var warning = '<div id="messageDiv" class="alert alert-success" style="display: none;">'
		+ '<button data-dismiss="alert" class="close">×</button>'
		+ '<span id="messagespan"></span>' + '</div>';

adminUserList.operationClick = function(flag, userId) {
	if (flag == 0) {// delete
		if (confirm('是否确认删除该用户？')) {
			userAjax.delUser(userId, function callback(data) {
				$('#warning').append(warning);
				if (data == 1) {
					data = '删除成功';
					adminUserList.refreshTable();
				} else if (data == 2) {
					data = '尝试删除超级管理员用户失败';
					$('#messageDiv').attr('class', 'alert alert-danger');
				} else {
					data = '删除用户失败';
					$('#messageDiv').attr('class', 'alert alert-danger');
				}
				$('#messagespan').html(data);
				$('#messageDiv').show();
			});
		}
	} else if (flag == 1) {

	} else if (flag == 2) {

	}
};
