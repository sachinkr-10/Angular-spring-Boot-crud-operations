package com.exm.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.exm.demo.exception.ResourceNotFoundException;

@RestControllerAdvice
public class EmployeeControllerAdvice {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handleEmployeeException(ResourceNotFoundException excep){
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleOtherExceptions(ResourceNotFoundException excep){
		return new ResponseEntity<>(excep.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
