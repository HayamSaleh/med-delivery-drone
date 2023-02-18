package com.deliverydrone.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import com.deliverydrone.dto.DroneModelDto;

public interface DroneModelService {

  static final String DRONE_MODEL_NOT_FOUND = "Drone model not found";
  static final String DRONE_MODEL_NAME_EXISTS = "Drone model name is already exist";

  List<DroneModelDto> getAllDroneModels();

  DroneModelDto getDroneModelById(Long id) throws EntityNotFoundException;

  DroneModelDto addDroneModel(DroneModelDto drone);

  DroneModelDto updateDroneModel(DroneModelDto drone, Long id);

  boolean deleteDroneModel(Long id) throws EntityNotFoundException;

}
