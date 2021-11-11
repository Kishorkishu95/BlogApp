/**
 * 
 */
package com.kishor.blog.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Kishu
 *
 */
public class BlogApiException extends RuntimeException{

	private HttpStatus httpStatus;
	private String errorMsg;
	
	public BlogApiException(HttpStatus httpStatus, String errorMsg) {
		super();
		this.httpStatus = httpStatus;
		this.errorMsg = errorMsg;
	}
	
	public BlogApiException(HttpStatus httpStatus, String errorMsg,String errorMsg1) {
		super(errorMsg);
		this.httpStatus = httpStatus;
		this.errorMsg = errorMsg1;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	
	
}
