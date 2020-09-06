package com.staxter.uam.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.staxter.uam.ui.model.response.ErrorMessage;

// This class will listen for exceptions in the the Controller class
//  @author : Veen Anil

@ControllerAdvice

public class UAMExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private Environment env;

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleUAllException(Exception ex, WebRequest request) {
		return new ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { UserAccessException.class })
	public ResponseEntity<Object> handleUserAccessException(Exception ex, WebRequest request) {

		String errorCode = ex.getMessage();
		if (errorCode == null)
			errorCode = ex.toString();
		ErrorMessage errorMessage = new ErrorMessage(errorCode, env.getProperty(errorCode));
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.CONFLICT);
	}

}
