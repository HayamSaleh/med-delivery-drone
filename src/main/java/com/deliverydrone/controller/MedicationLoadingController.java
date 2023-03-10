package com.deliverydrone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverydrone.dto.DeliveryMedicationDto;
import com.deliverydrone.service.MedicationLoadingService;

@RestController
@RequestMapping("/drones/")
public class MedicationLoadingController {

  @Autowired
  private MedicationLoadingService medicationLoadingService;

  @PostMapping("/{droneId}/loading")
  public ResponseEntity<Void> loadMedicationByDroneId(@PathVariable Long droneId,
	  @RequestBody DeliveryMedicationDto deliveryMedicationDto) {
	medicationLoadingService.loadMedicationByDroneId(droneId, deliveryMedicationDto);
	return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/{droneId}/bulkloading")
  public ResponseEntity<Void> bulkLoadMedicationByDroneId(@PathVariable Long droneId,
	  @RequestBody List<DeliveryMedicationDto> deliveryMedicationDtos) {
	medicationLoadingService.bulkLoadMedicationByDroneId(droneId, deliveryMedicationDtos);
	return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/{droneId}/loaded")
  public ResponseEntity<Void> endMedicationsLoadingByDroneId(@PathVariable Long droneId) {
	medicationLoadingService.endLoadingByDroneId(droneId);
	return new ResponseEntity<>(HttpStatus.OK);
  }

}
