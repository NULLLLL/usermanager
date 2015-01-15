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
	<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
	<script src="${ctx}/static/bootstrap/3.0.0/js/bootstrap.min.js" type="text/javascript"></script>
	
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
		opacity: 0.6;
	}
	</style>
</head>

<body>
<div style="position: absolute;top:15%;left:40%">
	<form id="loginForm" action="${ctx}/login" method="post" class="form-horizontal">
		<%
			if(request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME) != null) {
				String error = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME).toString();
			if(error != null){
				System.out.print(error);
		%>
					<div class="alert alert-error input-medium controls">
						<button class="close" data-dismiss="alert">×</button>登录失败，请重试.
					</div>
		<%
				}
			}
		%>
		<div class="control-group">
			<label for="username" class="control-label">名称:</label>
			<div class="controls">
				<input type="text" id="username" name="username"  value="${username}" class="input-medium required" data-toggle="tooltip" data-placement="left" title="Tooltip on left"/>
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">密码:</label>
			<div class="controls">
				<input type="password" id="password" class="input-medium required"/>
				<input type="hidden" name="password" id="pwd"/>
			</div>
		</div>
		
		<div class="control-group">
			<label for="captchaimg" class="control-label">验证码:</label>
			<div class="controls">
				<input type="text" name="captcha" id="captcha" class="input-medium required"/>
				<img alt="验证码" title="看不清，下一张" src="kaptcha.jpg" id="captchaimg" onclick="nextCaptcha();" style="cursor: pointer;width: 80px;height: 30px;">
      		<a href="javascript:void(0);" onclick="nextCaptcha();" style="font-size: 12px;color: red;">看不清，下一张</a>
			</div>
		</div> 
				
		<div class="control-group">
			<div class="controls">
				<label class="checkbox" for="rememberMe"><input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我</label>
				<input id="submit_btn" class="btn btn-primary" type="button" onclick="login.formSub()" value="登录"/> <a class="btn" href="${ctx}/register">注册</a>
			 	<span class="help-block">(管理员: <b>admin/admin</b>, 普通用户: <b>user/user</b>)</span>
			</div>
		</div>
		
	</form>
</div>
	<script src="${ctx}/static/tool/base64.js"></script>
	<script src="${ctx}/static/js/login.js"></script>
	<script>
	function nextCaptcha(){
		document.getElementById("captchaimg").src="kaptcha.jpg?q="+new Date();
		}
		$(document).ready(function() {
			$("#loginForm").validate();
		});
	</script>
</body>
</html>
