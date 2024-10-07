package com.dpm.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dpm.controllers.DataController;
import com.dpm.modals.SaveStatus;

@ControllerAdvice(assignableTypes = DataController.class)
public class ValidationControllerAdvice extends ResponseEntityExceptionHandler {
	
	@Autowired
	private ValidationErrorBuilder builder;

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		SaveStatus error = builder.fromBindingErrors(exception.getBindingResult());
		return super.handleExceptionInternal(exception, error, headers, status, request);
	}
	
	@ExceptionHandler(value = ArithmeticException.class)
	protected ResponseEntity<SaveStatus> nullPointerException() {
		SaveStatus saveStatus = SaveStatus.builder().message("SOME ERROR").status(HttpStatus.BAD_REQUEST).errors(null).build();
	
		return new ResponseEntity<SaveStatus>(saveStatus,HttpStatus.BAD_REQUEST);
	}

}
