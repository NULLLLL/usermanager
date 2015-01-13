package com.usermanager.base.service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springside.modules.utils.Encodes;

import com.util.DateUtil;

@Component
public class LoginRecordFilter extends FormAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(LoginRecordFilter.class);

	public LoginRecordFilter() {
	}

	@Override
	protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = new String(Encodes.decodeBase64(getPassword(request)));
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken(username, password != null ? password.toCharArray() : null, rememberMe, host,
				captcha);
		return token;
	}

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		CaptchaUsernamePasswordToken token = createToken(request, response);
		try {
			/* 图形验证码验证 */
			doCaptchaValidate((HttpServletRequest) request, token);
			Subject subject = getSubject(request, response);
			subject.login(token);// 正常验证
			subject.isPermitted("login");
			logger.info(token.getUsername() + ":" + DateUtil.getNowTimestamp() + "---登录成功");
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			logger.info(token.getUsername() + ":" + DateUtil.getNowTimestamp() + "---登录失败--" + e);
			return onLoginFailure(token, e, request, response);
		}
	}

	// 验证码校验
	protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
		// session中的图形码字符串
		String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		// 比对
		if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
			throw new AuthenticationException("验证码错误！");
		}
	}

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {
		return captchaParam;
	}

	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		request.setAttribute(getFailureKeyAttribute(), ae);
	}

}
