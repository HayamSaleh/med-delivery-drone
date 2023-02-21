package com.deliverydrone.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverydrone.dto.MedicationDto;
import com.deliverydrone.service.MedicationService;

@RestController
@RequestMapping("/medications")
public class MedicationController {

  private final MedicationService medicationService;

  public MedicationController(MedicationService medicationService) {
	this.medicationService = medicationService;
  }

  @GetMapping
  public ResponseEntity<List<MedicationDto>> getAllMedications() {
	return new ResponseEntity<>(medicationService.getAllMedications(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MedicationDto> getMedicationById(@PathVariable Long id) {
	return new ResponseEntity<>(medicationService.getMedicationById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<MedicationDto> addMedication(@RequestBody MedicationDto medication) {
	return new ResponseEntity<>(medicationService.addMedication(medication), HttpStatus.CREATED);

  }

  @PutMapping("/{id}")
  public ResponseEntity<MedicationDto> updateMedication(@RequestBody MedicationDto medication, @PathVariable Long id) {
	return new ResponseEntity<>(medicationService.updateMedication(medication, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
	medicationService.deleteMedication(id);
	return new ResponseEntity<>(HttpStatus.OK);
  }
}
