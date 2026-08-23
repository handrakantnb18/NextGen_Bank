package com.shivdattTrust.bank.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<ApiError> handleEmailEntity(EmailAlreadyExistsException ex){
		return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), null);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ApiError> handleInvalidCredentials(InvalidCredentialsException ex)
	{
		return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), null);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiError> handleUserNotFound(UsernameNotFoundException ex){
		return buildResponse(HttpStatus.UNAUTHORIZED, "Invalid email or password", null);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, String> fieldErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().
		forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
		
		return buildResponse(HttpStatus.BAD_REQUEST, "Validation failed", fieldErrors);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneric(Exception ex) {
		return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again", null);
	}
	
	
	private ResponseEntity<ApiError> buildResponse(HttpStatus status, String message, Map<String, String> fieldErrors) {
		ApiError error = ApiError.builder()
				.status(status.value())
				.message(message)
				.timestamp(LocalDateTime.now())
				.fieldErrors(fieldErrors)
				.build();
		return ResponseEntity.status(status).body(error);
	}
	
}
