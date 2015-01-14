<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>用户管理</title>
</head>
<body>
	<div id="warning">
		<div id="messageDiv" class="alert alert-success" style="display: none;">
			<button data-dismiss="alert" class="close">×</button>
			<span id="messagespan"></span>
		</div>
	</div>
	<div class="btn-group">
	    <button class="btn btn-default" id="load-data" data-method="load">
	        Load Data
	    </button>
	    <button class="btn btn-default" id="update-row" data-method="updateRow">
	        Update Row
	    </button>
	    <button class="btn btn-default" id="refresh" data-method="refresh">
	        Refresh
	    </button>
	</div>
	<table id="tableId">
	</table>
<script type='text/javascript' src='${ctx}/dwr/common/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/common/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/common/interface/userAjax.js'></script>
<script type="text/javascript" src="${ctx}/static/js/adminUserList.js?2015.01.07"></script>
</body>
</html>
