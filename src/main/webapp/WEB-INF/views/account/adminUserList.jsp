<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	
	<table data-toggle="table" data-url="${ctx}/admin/userTable" data-cache="false" data-height="200">
    <thead>
        <tr>
            <th data-align="center" data-field="id" data-formatter="idformatter"><input type="checkbox"/></th>
            <th data-align="center" data-sortable="true" data-field="name">姓名</th>
            <th data-align="center" data-sortable="true" data-field="loginName">登录名</th>
            <th data-align="center" data-sortable="true" data-field="registerDate">注册时间</th>
        </tr>
    </thead>
	</table>
<script type="text/javascript">
var idformatter = function(data, record){
	console.log(data);
	console.log(record);
	return '<input type="checkbox" value="' + record.id +'"/>';
}
</script>
</body>
</html>
