package com.deliverydrone.controller.exception;

import javax.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotAllowedRequestException.class)
  public ResponseEntity<String> handleNotAllowedRequestException(NotAllowedRequestException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DroneNotFoundException.class)
  public ResponseEntity<String> handleDroneNotFoundException(DroneNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DeliveryNotFoundException.class)
  public ResponseEntity<String> handleEntityNotFoundException(DeliveryNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EntityExistsException.class)
  public ResponseEntity<String> handleEntityExistsException(EntityExistsException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<String> handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
  }

  @ExceptionHandler(MedicationNotFoundException.class)
  public ResponseEntity<String> handleMedicationNotFoundException(MedicationNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

}
