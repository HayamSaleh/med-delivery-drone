package com.deliverydrone.controller.exception;

public class MedicationNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public MedicationNotFoundException() {
	super("The required Medications are not found");
  }

  public MedicationNotFoundException(Long id) {
	super("Medication with id " + id + " not found");
  }

}
