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
	
		<div class="panel-group" id="accordion_action">
				 <div class="panel panel-info">
				    <div class="panel-heading">
				      <h4 class="panel-title">
				        <a data-toggle="collapse" data-toggle="collapse" data-parent="#accordion_action" href="#collapseOne_action">数据操作</a>
				      </h4>
				    </div>
				    <div id="collapseOne_action" class="panel-collapse collapse in">
				      <div class="panel-body">
					      	<div id="searchDiv" class="row" style="margin-top:10px;margin-bottom:10px;">
									<div class="input-group col-xs-2">
										  <span class="input-group-addon">姓名</span>
										  <input type="text" id="name" name="name" class="form-control"/>
									</div>
									<div class="input-group col-xs-2">
										  <span class="input-group-addon">登录名</span>
										  <input type="text" id="loginName" name="loginName" class="form-control"/>
									</div>
									<div class="input-group col-xs-4">
										  <span class="input-group-addon">注册时间</span>
										  <input type="text" id="startregisterDate" name="startregisterDate" class="form-control" onfocus="WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true})"/>
										  <span class="input-group-addon">到</span>
										  <input type="text" id="endregisterDate" name="endregisterDate" class="form-control" onfocus="WdatePicker({skin:'whyGreen',isShowClear:true,readOnly:true})"/>
									</div>
									<div class="col-xs-2">
										<button type="submit" class="btn btn-primary btn-sm" id="searchData" >搜索</button>
									</div>		
							</div>
				      </div>
				    </div>
				</div>
		</div>
	<table id="tableId">
	</table>
<script type='text/javascript' src='${ctx}/dwr/common/engine.js'></script>
<script type='text/javascript' src='${ctx}/dwr/common/util.js'></script>
<script type='text/javascript' src='${ctx}/dwr/common/interface/userAjax.js'></script>
<script type="text/javascript" src="${ctx}/static/js/adminUserList.js?2015.01.07"></script>
</body>
</html>
