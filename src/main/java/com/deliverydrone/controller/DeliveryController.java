package com.deliverydrone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverydrone.dto.DeliveryDto;
import com.deliverydrone.dto.DeliveryMedicationDto;
import com.deliverydrone.service.DeliveryService;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

  @Autowired
  public DeliveryService deliveryService;

  @GetMapping("/{droneId}")
  public ResponseEntity<List<DeliveryDto>> getDeliveriesByDroneId(@PathVariable Long droneId) {
	return new ResponseEntity<>(deliveryService.getDeliveriesByDroneId(droneId), HttpStatus.OK);
  }

  @GetMapping("/items/{droneId}")
  public ResponseEntity<List<DeliveryMedicationDto>> getDeliveryItemsByDroneId(@PathVariable Long droneId) {
	return new ResponseEntity<>(deliveryService.getDeliveryLoadByDroneId(droneId), HttpStatus.OK);
  }

  @PutMapping("/{deliveryId}/start")
  public ResponseEntity<Boolean> startdeliveryById(@PathVariable Long deliveryId) {
	return new ResponseEntity<>(deliveryService.startDeliveryById(deliveryId), HttpStatus.OK);
  }

  @PutMapping("/{deliveryId}/delivered")
  public ResponseEntity<Boolean> markAsDeliveredById(@PathVariable Long deliveryId) {
	return new ResponseEntity<>(deliveryService.markAsDeliveredById(deliveryId), HttpStatus.OK);
  }

}
