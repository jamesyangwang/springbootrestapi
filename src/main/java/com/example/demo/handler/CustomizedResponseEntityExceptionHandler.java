package com.example.demo.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.ExceptionDetails;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionDetails> handleAllException(Exception ex, WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ExceptionDetails> handleAllException(UserNotFoundException ex, WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionDetails ed = new ExceptionDetails(new Date(), "Validation Failed", ex.getBindingResult().getAllErrors().toString());
		return new ResponseEntity<Object>(ed, HttpStatus.BAD_REQUEST);
	}
}
