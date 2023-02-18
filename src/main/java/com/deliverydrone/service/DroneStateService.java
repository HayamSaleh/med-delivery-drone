package com.deliverydrone.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import com.deliverydrone.dto.DroneStateDto;

public interface DroneStateService {

  static final String DRONE_STATE_NOT_FOUND = "Drone state not found";
  static final String DRONE_STATE_NAME_EXISTS = "Drone state name is already exist";

  List<DroneStateDto> getAllDroneStates();

  DroneStateDto getDroneStateById(Long id) throws EntityNotFoundException;

  DroneStateDto addDroneState(DroneStateDto drone);

  DroneStateDto updateDroneState(DroneStateDto drone, Long id);

  boolean deleteDroneState(Long id) throws EntityNotFoundException;

}
