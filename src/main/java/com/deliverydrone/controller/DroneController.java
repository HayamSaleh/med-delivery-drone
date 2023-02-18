package com.deliverydrone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.deliverydrone.dto.DroneDto;
import com.deliverydrone.service.DroneService;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

  @Autowired
  private DroneService droneService;

  @GetMapping()
  public ResponseEntity<List<DroneDto>> getAllDrones() {
    return new ResponseEntity<>(droneService.getAllDrones(), HttpStatus.OK);
  }

  @GetMapping("/available")
  public ResponseEntity<List<DroneDto>> getAllAvailableDrones() {
    return new ResponseEntity<>(droneService.getAllAvailableDrones(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DroneDto> getDroneById(@PathVariable Long id) {
    return new ResponseEntity<>(droneService.getDroneById(id), HttpStatus.OK);
  }

  @PostMapping("/batteryLevel/{id}")
  public ResponseEntity<Double> checkDroneBatteryLevelById(@PathVariable Long id) {
    return new ResponseEntity<>(droneService.checkDroneBatteryLevelById(id), HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<DroneDto> registerDrone(@RequestBody DroneDto drone) {
    return new ResponseEntity<>(droneService.addDrone(drone), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DroneDto> updateDrone(@RequestBody DroneDto drone, @PathVariable Long id) {
    return new ResponseEntity<>(droneService.updateDrone(drone, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteDrone(@PathVariable Long id) {
    return new ResponseEntity<>(droneService.deleteDrone(id), HttpStatus.OK);
  }



}
