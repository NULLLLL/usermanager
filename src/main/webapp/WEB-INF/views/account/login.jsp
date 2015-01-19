<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/bootstrap/3.0.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/static/tool/bootstrap-material/css/ripples.min.css" rel="stylesheet">
	<link href="${ctx}/static/tool/bootstrap-material/css/material-wfont.css" rel="stylesheet">
	<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
	<script src="${ctx}/static/bootstrap/3.0.0/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/tool/bootstrap-material/js/ripples.min.js"></script>
	<script src="${ctx}/static/tool/bootstrap-material/js/material.js"></script>
	
	<title>登录页</title>
	
	<style type="text/css">
	body {
		width:100%;
		height:100%;
		background-image:url(${ctx}/static/images/background.jpg);
		background-repeat: no-repeat;
		background-size : 100% 100%;
	}
	form {
		opacity: 0.9;
	}
	</style>
</head>

<body>
<div style="position: absolute;top:20%;left:40%;width:15%">
	<form id="loginForm" action="${ctx}/login" method="post" class="form-horizontal">
		<%
			if(request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME) != null) {
				String error = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME).toString();
			if(error != null){
				System.out.print(error);
		%>
					<div class="alert alert-error input-medium ">
						<button class="close" data-dismiss="alert">×</button>登录失败，请重试.
					</div>
		<%
				}
			}
		%>
		<div class="form-group">
				<div class="input-group">
					<div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
					<input type="text" id="username" name="username" placeholder="用户名" class="form-control floating-label required"/>
				</div>
		</div>
		<div class="form-group">
			<div class="input-group">
				<div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
				<input type="password" id="password" placeholder="密码" class="form-control floating-label  required"/>
				<input type="hidden" name="password" id="pwd"/>
			</div>
		</div>
		
		<div class="form-group">
			<div class="input-group">
				<div class="input-group-addon"><span class="glyphicon glyphicon-picture"></span></div>
				<input type="text" name="captcha" id="captcha" placeholder="验证码" class="form-control floating-label required"/>
			</div>
				<img alt="验证码" title="看不清，下一张" src="kaptcha.jpg" id="captchaimg" onclick="nextCaptcha();" style="cursor: pointer;width: 80px;height: 30px;margin	:5px 0px 0px 45px;">
      		<a onclick="nextCaptcha();" style="cursor:pointer;"><span style="margin-top:9px" class="glyphicon glyphicon-refresh"></a>
		</div> 
				
		<div class="control-group" style="text-align: center;">
				<input id="submit_btn" class="btn btn-primary" type="button" onclick="login.formSub()" value="登录"/> 
				<a class="btn" href="${ctx}/register">注册</a>
		</div>
		
	</form>
</div>
	<script src="${ctx}/static/tool/base64.js"></script>
	<script src="${ctx}/static/js/login.js"></script>
	<script>
	$(document).ready(function() {
			$.material.init();
 	});
	function nextCaptcha(){
		document.getElementById("captchaimg").src="kaptcha.jpg?q="+new Date();
	}
	$(document).ready(function() {
		$("#loginForm").validate();
	});
	</script>
</body>
</html>
