/**
 * 
 */
package com.bridgelab.todo.user.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author bridgeit
 *
 */
public class InterceptorClass extends HandlerInterceptorAdapter{

public boolean preHandle(HttpServletRequest request, HttpServletResponse response) {
	request.setAttribute("token_id", request.getHeader("id"));
	return true;
	
}
}

