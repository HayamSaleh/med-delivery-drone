package com.deliverydrone.controller.exception;

import javax.activity.InvalidActivityException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> defaultExceptionHandler(HttpServletRequest req, Exception e)
      throws Exception {
    // add log or something
    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) == null) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    throw e;
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EntityExistsException.class)
  public ResponseEntity<String> handleEntityExistsException(EntityExistsException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
  }


  @ExceptionHandler(InvalidActivityException.class)
  public ResponseEntity<String> handleInvalidActivityException(InvalidActivityException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
  }

}
