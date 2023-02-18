package com.deliverydrone.controller.exception;

public class DroneNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DroneNotFoundException(Long droneId) {
    super("Drone with id " + droneId + " not found");
  }

}
