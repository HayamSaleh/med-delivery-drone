package com.deliverydrone.controller;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

  @GetMapping()
  public ResponseEntity<?> getAllDrones() {
    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
  }

}
