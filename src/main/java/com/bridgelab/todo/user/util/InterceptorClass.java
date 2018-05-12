/**
 * 
 */
package com.bridgelab.todo.user.util;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bridgelab.todo.user.model.User;
import com.bridgelab.todo.user.service.IUserService;

/**
 * @author bridgeit
 *
 */
public class InterceptorClass implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		String tokenSample = request.getHeader("Authorization");
		System.out.println("*********token in interceptor*********\n" + tokenSample);
		int userId = JWT_Tokens.verifyToken(tokenSample);
		request.setAttribute("userId", userId);
		return true;
	}
		

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
