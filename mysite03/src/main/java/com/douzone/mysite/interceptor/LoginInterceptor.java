package com.douzone.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	protected Log log = LogFactory.getLog(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(log.isDebugEnabled()) {
			log.debug("========= START ========");
			log.debug("Request URI \t:" + request.getRequestURI());
		}
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		if(id == null) {
			String urlPrior = request.getRequestURL().toString() + "?" + request.getQueryString();
			request.getSession().setAttribute("url_prior_login", urlPrior);
			
			response.sendRedirect(request.getContextPath() + "user/login");
		}
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(log.isDebugEnabled()) {
			log.debug("=========== END ===========");
		}
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
		
	}

}
