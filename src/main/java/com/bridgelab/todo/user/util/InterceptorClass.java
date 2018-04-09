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
String tokenSample=(String) request.getAttribute("auth");
JWT_Tokens.verifyToken(tokenSample);
	
	
	return true;
	
}
}


