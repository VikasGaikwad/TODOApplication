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
System.out.println("*******token in interceptor :*******\n"+tokenSample);
JWT_Tokens.verifyToken(tokenSample);
	
System.out.println("*******interceptor passed successfull********");
	
	return true;
	
}
}


