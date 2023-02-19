package com.deliverydrone.controller.exception;

import java.util.Date;

import javax.persistence.EntityExistsException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotAllowedRequestException.class)
  public ResponseEntity<ErrorDetails> handleNotAllowedRequestException(NotAllowedRequestException e,
	  WebRequest request) {
	ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
	return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DroneNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleDroneNotFoundException(DroneNotFoundException e, WebRequest request) {
	ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
	return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DeliveryNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleEntityNotFoundException(DeliveryNotFoundException e, WebRequest request) {
	ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
	return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EntityExistsException.class)
  public ResponseEntity<ErrorDetails> handleEntityExistsException(EntityExistsException e, WebRequest request) {
	ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
	return new ResponseEntity<>(errorDetails, HttpStatus.EXPECTATION_FAILED);
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<ErrorDetails> handleEntityAlreadyExistsException(EntityAlreadyExistsException e,
	  WebRequest request) {
	ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
	return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(MedicationNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleMedicationNotFoundException(MedicationNotFoundException e,
	  WebRequest request) {
	ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), request.getDescription(false));
	return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

}
