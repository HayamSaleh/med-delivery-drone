package com.deliverydrone.service;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.deliverydrone.controller.exception.MedicationNotFoundException;
import com.deliverydrone.dto.MedicationDto;

public interface MedicationService {
  static final String MEDICATION_WITH_SAME_CODE_EXISTS = "A medication with same code already exists";
  static final String MEDICATION_WITH_INVALID_NAME = "Invalid medication Name [Allowed only letters, numbers, ‘-‘, ‘_’]";
  static final String MEDICATION_WITH_INVALID_CODE = "Invalid medication code [Allowed only upper case letters, underscore and numbers]";
  static final String MEDICATION_NAME_PATTERN = "^[a-zA-Z0-9_-]*$";
  static final String MEDICATION_CODE_PATTERN = "^[A-Z0-9_]*$";

  @Pattern(regexp = "^[A-Z0-9_]*$")

  List<MedicationDto> getAllMedications();

  MedicationDto getMedicationById(Long id);

  MedicationDto addMedication(MedicationDto medication);

  MedicationDto updateMedication(MedicationDto medication, Long id);

  List<Long> getValidMedicationByIdIn(List<Long> ids);

  boolean deleteMedication(Long id) throws MedicationNotFoundException;
}
