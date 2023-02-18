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
import com.deliverydrone.dto.DroneStateDto;
import com.deliverydrone.service.DroneStateService;

@RestController
@RequestMapping("/api/droneState")
public class DroneStateController {

  @Autowired
  private DroneStateService droneStateService;

  @GetMapping()
  public ResponseEntity<List<DroneStateDto>> getAllDroneStates() {
    return new ResponseEntity<>(droneStateService.getAllDroneStates(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DroneStateDto> getDroneStateById(@PathVariable Long id) {
    return new ResponseEntity<>(droneStateService.getDroneStateById(id), HttpStatus.OK);
  }


  @PostMapping()
  public ResponseEntity<DroneStateDto> addDroneState(@RequestBody DroneStateDto droneState) {
    return new ResponseEntity<>(droneStateService.addDroneState(droneState), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DroneStateDto> updateDroneState(@RequestBody DroneStateDto droneState,
      @PathVariable Long id) {
    return new ResponseEntity<>(droneStateService.updateDroneState(droneState, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteDrone(@PathVariable Long id) {
    return new ResponseEntity<>(droneStateService.deleteDroneState(id), HttpStatus.OK);
  }


}
