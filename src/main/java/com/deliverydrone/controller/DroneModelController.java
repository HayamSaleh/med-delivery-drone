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
import com.deliverydrone.dto.DroneModelDto;
import com.deliverydrone.service.DroneModelService;

@RestController
@RequestMapping("/droneModel")
public class DroneModelController {

  @Autowired
  private DroneModelService droneModelService;

  @GetMapping()
  public ResponseEntity<List<DroneModelDto>> getAllDroneModels() {
    return new ResponseEntity<>(droneModelService.getAllDroneModels(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DroneModelDto> getDroneModelById(@PathVariable Long id) {
    return new ResponseEntity<>(droneModelService.getDroneModelById(id), HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<DroneModelDto> addDroneModel(@RequestBody DroneModelDto droneModel) {
    return new ResponseEntity<>(droneModelService.addDroneModel(droneModel), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DroneModelDto> updateDroneModel(@RequestBody DroneModelDto droneModel,
      @PathVariable Long id) {
    return new ResponseEntity<>(droneModelService.updateDroneModel(droneModel, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteDrone(@PathVariable Long id) {
    return new ResponseEntity<>(droneModelService.deleteDroneModel(id), HttpStatus.OK);
  }

}
