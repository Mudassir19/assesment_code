package com.uxpsystems.assignment.app.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.uxpsystems.assignment.app.exception.ApplicationError;
import com.uxpsystems.assignment.app.exception.AssignmentCustomException;


@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

	/**
	 * @param exception
	 * @param webRequest
	 * @return
	 */
	@ExceptionHandler(AssignmentCustomException.class)
	public ResponseEntity<ApplicationError> handleCustomerNotFoundException(AssignmentCustomException exception,
			WebRequest webRequest) {
		ApplicationError error = new ApplicationError();
		error.setCode(404);
		error.setMessage(exception.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
}
