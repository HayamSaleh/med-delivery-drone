package com.deliverydrone.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.deliverydrone.dto.DroneDto;

public interface DroneService {

  static final String NOT_ALLOWED_DRONE_STATE = "Drone State %d is not allowed";
  static final String DRONE_WITH_SAME_SERIAL_NUMBER_EXISTS = "Drone with same serial number already exists";

  List<DroneDto> getAllDrones();

  List<DroneDto> getAllAvailableDrones();

  DroneDto getDroneById(Long id);

  Float checkDroneBatteryLevelById(Long id);

  DroneDto addDrone(DroneDto drone);

  DroneDto updateDrone(DroneDto drone, Long id);

  DroneDto updateDroneState(String droneState, Long id);

  boolean deleteDrone(Long id) throws EntityNotFoundException;

  boolean existsById(Long droneId);
}
