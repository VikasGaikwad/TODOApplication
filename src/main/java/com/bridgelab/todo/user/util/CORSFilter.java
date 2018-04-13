/**
 * 
 */
package com.bridgelab.todo.user.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author bridgeit.
 * setHeader()-Adds a response header with the given name and
 *         value. This method allows response headers to have multiple values.
 */
public class CORSFilter extends OncePerRequestFilter{
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		 response.addHeader("Access-Control-Allow-Origin", "*");
		   response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		   response.addHeader("Access-Control-Allow-Headers", "auth,Content-Type,Accept, X-Requested-With");
		   response.addHeader("Access-Control-Expose-Headers", "auth, Content-Type");
		   response.addHeader("Access-Control-Max-Age", "480000");
		   response.setStatus(HttpServletResponse.SC_OK);
		   
		   chain.doFilter(request, response);
		   System.out.println("CORS filter sending resuest and response");
		
	}
}
