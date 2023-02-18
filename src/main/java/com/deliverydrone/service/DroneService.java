package com.deliverydrone.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import com.deliverydrone.dto.DroneDto;

public interface DroneService {

  List<DroneDto> getAllDrones();

  List<DroneDto> getAllAvailableDrones();

  DroneDto getDroneById(Long id);

  Double checkDroneBatteryLevelById(Long id);

  DroneDto addDrone(DroneDto drone);

  DroneDto updateDrone(DroneDto drone, Long id);

  DroneDto updateDroneState(String droneState, Long id);

  boolean deleteDrone(Long id) throws EntityNotFoundException;
}
