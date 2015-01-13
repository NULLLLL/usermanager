<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>登录页</title>
</head>

<body>
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
				<input type="text" id="username" name="username"  value="${username}" class="input-medium required"/>
			</div>
		</div>
		<div class="control-group">
			<label for="password" class="control-label">密码:</label>
			<div class="controls">
				<input type="password" id="password" name="password" class="input-medium required"/>
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
	<script src="${ctx}/static/js/login.js"></script>
	<script src="${ctx}/static/tool/md5.js"></script>
	<script src="${ctx}/static/tool/base64.js"></script>
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
