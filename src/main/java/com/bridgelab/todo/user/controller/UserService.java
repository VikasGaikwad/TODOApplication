/**
 * 
 */
package com.bridgelab.todo.user.controller;

/**
 * @author bridgeit
 *
 */
public class UserService {
	private int statusCode;
	
	private String message;

	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
