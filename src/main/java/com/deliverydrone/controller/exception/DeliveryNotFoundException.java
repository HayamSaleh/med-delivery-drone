package com.deliverydrone.controller.exception;


public class DeliveryNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DeliveryNotFoundException(String message) {
    super(message);
  }

  public DeliveryNotFoundException(Long id) {
    super("Delivery with id " + id + " not found");
  }

}
