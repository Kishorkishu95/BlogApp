package com.kishor.blog.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kishor.blog.dto.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// Handle specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest request) {
		ErrorDetails details = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(BlogApiException.class)
	public ResponseEntity<ErrorDetails> handleBlogApiException(BlogApiException exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);

	}

	// Handling Global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/*
	 * // 1st approach
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * ResponseEntity<Object>
	 * handleMethodArgumentNotValidException(MethodArgumentNotValidException
	 * exception, WebRequest request) { Map<String, String> errors = new
	 * HashMap<>(); exception.getBindingResult().getAllErrors().forEach((error) -> {
	 * String fieldName = ((FieldError) error).getField(); String message =
	 * error.getDefaultMessage(); errors.put(fieldName, message); }); return new
	 * ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	 * 
	 * }
	 */

	// 2nd approach
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
