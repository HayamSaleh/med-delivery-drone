package com.deliverydrone.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import com.deliverydrone.dto.DroneDto;

public interface DroneService {

  static final String DRONE_NOT_FOUND = "Drone not found";

  List<DroneDto> getAllDrones();

  List<DroneDto> getAllAvailableDrones();

  DroneDto getDroneById(Long id) throws EntityNotFoundException;

  Double checkDroneBatteryLevelById(Long id) throws EntityNotFoundException;

  DroneDto addDrone(DroneDto drone);

  DroneDto updateDrone(DroneDto drone, Long id) throws EntityNotFoundException;

  boolean deleteDrone(Long id) throws EntityNotFoundException;



}
