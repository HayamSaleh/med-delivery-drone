package com.deliverydrone.service;

import java.util.List;

import com.deliverydrone.controller.exception.MedicationNotFoundException;
import com.deliverydrone.dto.MedicationDto;

public interface MedicationService {
  static final String MEDICATION_WITH_SAME_CODE_EXISTS = "A medication with same code already exists";
  static final String MEDICATION_WITH_INVALID_CODE = "Invalid medication code [Allowed only upper case letters, underscore and numbers]";
  static final String MEDICATION_COEE_PATTERN = "^[a-zA-Z0-9_-]*$";

  List<MedicationDto> getAllMedications();

  MedicationDto getMedicationById(Long id);

  MedicationDto addMedication(MedicationDto medication);

  MedicationDto updateMedication(MedicationDto medication, Long id);

  List<Long> getValidMedicationByIdIn(List<Long> ids);

  boolean deleteMedication(Long id) throws MedicationNotFoundException;
}
