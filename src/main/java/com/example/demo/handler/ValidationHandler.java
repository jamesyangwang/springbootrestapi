package com.example.demo.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.model.ErrorDetails;

//https://dzone.com/articles/implementing-validation-for-restful-services-with
@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ValidationHandler.class);

	//=========================================================================
	// handle Exception
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(ConstraintViolationException ex, WebRequest request) {
		logger.info("handleAllExceptions() starts...");
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), null,
				request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	//=========================================================================
	// triggered by @Valid @RequestBody
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.info("handleMethodArgumentNotValid() starts...");
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        ErrorDetails details = new ErrorDetails(new Date(), null, errors,
				request.getDescription(false));
        return new ResponseEntity<Object>(details, headers, status);
	}

}





























//ResponseEntityExceptionHandler:
//https://blog.jayway.com/2013/02/03/improve-your-spring-rest-api-part-iii/


